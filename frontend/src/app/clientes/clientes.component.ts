import { Component, OnInit } from '@angular/core';
import { Cliente } from '../model/Cliente';
import { Router } from '@angular/router';
import { ApiService } from '../service/api.service';
import { ConfirmDialogService } from '../components/confirm-dialog/confirm-dialog.service';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent implements OnInit {

  clientes: Cliente[];
  constructor(
    private router: Router,
    private apiService: ApiService,
    private confirmDialogService: ConfirmDialogService
  ) { }

  ngOnInit() {
    this.apiService.getClientes().subscribe((data: Cliente[]) => {
      this.clientes = data;
    });
  }

  deleteCliente(cliente: Cliente): void {
    this.confirmDialogService.confirmThis('Você tem certeza que deseja excluir este cliente ?', () => {
      this.apiService.excluirCliente(cliente.id).subscribe(data => {
        this.clientes = this.clientes.filter(c => c !== cliente);
      });
    }, () => {}
    );
  }

  editCliente(cliente: Cliente): void {
    localStorage.removeItem('editClienteId');
    localStorage.setItem('editClienteId', cliente.id.toString());
    this.router.navigate(['clientes/novo']);
  }

  addCliente(): void {
    this.router.navigate(['clientes/novo']);
  }

}
