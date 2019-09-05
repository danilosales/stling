import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/index';
import { ApiResponse } from '../model/api.response';
import { Cliente } from '../model/Cliente';

@Injectable()
export class ApiService {

  baseUrl = 'http://localhost:8080/api';
  constructor(private http: HttpClient, private router: Router) { }

  getClientes() {
    return this.http.get(`${this.baseUrl}/clientes`);
  }

  excluirCliente(id: number) {
    return this.http.delete(`${this.baseUrl}/clientes/${id}`);
  }

  salvarCliente(cliente: Cliente) {
    return this.http.post(`${this.baseUrl}/clientes`, cliente);
  }

  getClientePorId(id: number): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.baseUrl}/clientes/${id}`);
  }

}
