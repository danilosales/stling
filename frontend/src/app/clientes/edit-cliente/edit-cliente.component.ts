import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { Cliente } from 'src/app/model/Cliente';
import {first} from 'rxjs/operators';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { ApiService } from 'src/app/service/api.service';

@Component({
  selector: 'app-edit-cliente',
  templateUrl: './edit-cliente.component.html',
  styleUrls: ['./edit-cliente.component.css']
})
export class EditClienteComponent implements OnInit {
  state$: Observable<any>;
  isNovo = true;
  submitted = false;
  cliente: Cliente;
  formCliente: FormGroup;
  erroSalvar: string;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private apiService: ApiService
  ) { }

  ngOnInit() {
    const clienteId = localStorage.getItem('editClienteId');
    localStorage.removeItem('editClienteId');
    this.formCliente = this.formBuilder.group({
      id: [''],
      nome: ['', Validators.required],
      cpfCnpj: ['', Validators.required],
      endereco: ['', Validators.required]
    });
    if (clienteId) {
      this.apiService.getClientePorId(+clienteId).subscribe(data => {
        this.formCliente.setValue(data);
        this.isNovo = false;
      });
    }
  }

  salvar(): void {
    if (this.formCliente.invalid) {
      this.submitted = true;
      return;
    }

    this.apiService.salvarCliente(this.formCliente.value)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate(['clientes']);
        },
        error => {
          this.erroSalvar = error.error.message;
        }
      );

  }

}
