import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/model/Cliente';
import { FormControl } from '@angular/forms';
import { ApiService } from 'src/app/service/api.service';
import { Pedido } from 'src/app/model/Pedido';
import { ItemPedido } from 'src/app/model/ItemPedido';
import { Produto } from 'src/app/model/Produto';
import { Router } from '@angular/router';
import { debounceTime, tap, switchMap, finalize, filter, distinctUntilChanged } from 'rxjs/operators';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-edit-pedido',
  templateUrl: './edit-pedido.component.html',
  styleUrls: ['./edit-pedido.component.css'],
})
export class EditPedidoComponent implements OnInit {

  clientes: Cliente[];
  isNovo = true;
  erroSalvar: string;
  submitted = false;
  pedido: Pedido = new Pedido();
  inputSearch = new FormControl();
  totalPedido = 0.0;
  produtos = [];
  isLoading = false;
  constructor(
    private apiService: ApiService,
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit() {
    const pedidoId = localStorage.getItem('editPedidoId');
    localStorage.removeItem('editPedidoId');
    this.carregarComboClientes();
    this.adicionarEventos();

    if (pedidoId) {
      this.carregarPedido(pedidoId);
    }
  }

  carregarComboClientes() {
    this.apiService.getClientes().subscribe((data: Cliente[]) => this.clientes = data);
  }

  salvarPedido() {
    if (!this.pedido.vendedorId) {
      this.pedido.vendedorId = this.authService.getUsuarioId();
    }
    if (this.pedido.id) {
      console.log(this.pedido);
      this.apiService.atualizarPedido(this.pedido).subscribe(data => {
        this.router.navigate(['pedidos']);
      },
      error => console.log(error));
    } else {
      this.apiService.salvarPedido(this.pedido).subscribe(data => {
        this.router.navigate(['pedidos']);
      },
      error => console.log(error));
    }
  }

  finalizarPedido() {
    console.log(this.pedido);
    this.pedido.pedidoEmitido = true;
    this.salvarPedido();
  }

  excluirItem(item: ItemPedido) {
    this.pedido.itens = this.pedido.itens.filter(i => i !== item);
    this.calcularTotalPedido();
  }

  atualizarValor(item: ItemPedido) {
    if (item.quantidade < 1) {
      item.quantidade = 1;
    }
    this.calcularTotalPedido();
  }

  selecionaProduto(prod: Produto) {
    this.inputSearch.setValue('');
    if (this.produtoExisteNaLista(prod)) {
      return;
    }
    const item = new ItemPedido();
    item.quantidade = 1;
    item.produtoValor = prod.valor;
    item.produtoId = prod.id;
    item.produtoDescricao = prod.descricao;
    this.pedido.itens.unshift(item);
    this.calcularTotalPedido();
  }

  private calcularTotalPedido() {
    this.totalPedido = this.pedido.itens.reduce((total, item) => total += item.totalItem , 0);
  }

  private produtoExisteNaLista(prod: Produto): boolean {
    return this.pedido.itens.some(item => item.produtoId === prod.id);
  }

  private adicionarEventos() {
    this.inputSearch.valueChanges
    .pipe(
      debounceTime(500),
      filter(query => query.length >= 1),
      distinctUntilChanged(),
      tap(() => {
        this.produtos = [];
        this.isLoading = true;
      }),
      switchMap(value => this.apiService.buscarProdutos(value)
        .pipe(
          finalize(() => {
            this.isLoading = false;
          }),
        )
      )
    )
    .subscribe((data: Produto[]) => {
      this.produtos = data;
    });
  }

  private carregarPedido(pedidoId) {
    this.apiService.buscarPedidoPorId(pedidoId)
      .subscribe((data) => {
        this.pedido = Object.assign(new Pedido(), data);
        this.pedido.itens = this.pedido.itens.map(item => Object.assign(new ItemPedido(), item));
        this.calcularTotalPedido();
      });
  }
}
