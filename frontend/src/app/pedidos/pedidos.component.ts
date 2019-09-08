import { Component, OnInit } from '@angular/core';
import { PedidoResumo } from '../model/PedidoResumo';
import { ConfirmDialogService } from '../components/confirm-dialog/confirm-dialog.service';
import { Router } from '@angular/router';
import { ApiService } from '../service/api.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-pedidos',
  templateUrl: './pedidos.component.html',
  styleUrls: ['./pedidos.component.css']
})
export class PedidosComponent implements OnInit {

  pedidos: PedidoResumo[] = [];
  pedidosForm: FormGroup;
  submitted = false;
  buscouServico = false;
  constructor(
    private router: Router,
    private apiService: ApiService,
    private formBuilder: FormBuilder,
    private confirmDialogService: ConfirmDialogService
  ) { }

  ngOnInit() {
    this.pedidosForm = this.formBuilder.group({
      campoBusca: ['', Validators.required]
    });
  }

  buscar() {
    if (this.pedidosForm.invalid) {
      this.submitted = true;
      return;
    }

    this.apiService.buscarPedidos(this.pedidosForm.get('campoBusca').value)
      .subscribe((data: PedidoResumo[]) => {
        this.pedidos = data;
    });
    this.buscouServico = true;
  }

  excluirPedido(pedido: PedidoResumo): void {
    this.confirmDialogService.confirmThis('Você tem certeza que deseja excluir este pedido ?', () => {
      this.apiService.excluirPedido(pedido.id).subscribe(data => {
        this.pedidos = this.pedidos.filter(p => p !== pedido);
      });
    }, () => {}
    );
  }

  editPedido(pedido: PedidoResumo): void {
    localStorage.removeItem('editPedidoId');
    localStorage.setItem('editPedidoId', pedido.id.toString());
    this.router.navigate(['pedidos/novo']);
  }

  novoPedido(): void {
    this.router.navigate(['pedidos/novo']);
  }

}
