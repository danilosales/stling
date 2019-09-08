import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/index';
import { ApiResponse } from '../model/api.response';
import { Cliente } from '../model/Cliente';
import { Pedido } from '../model/Pedido';

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

  buscarProdutos(descricao: string) {
    return this.http.get(`${this.baseUrl}/produtos?q=${descricao}`);
  }

  buscarPedidos(nome: string) {
    return this.http.get(`${this.baseUrl}/pedidos?q=${nome}`);
  }

  buscarPedidoPorId(id: number) {
    return this.http.get(`${this.baseUrl}/pedidos/${id}`);
  }

  excluirPedido(id: number) {
    return this.http.delete(`${this.baseUrl}/pedidos/${id}`);
  }

  salvarPedido(pedido: Pedido) {
    return this.http.post(`${this.baseUrl}/pedidos`, pedido);
  }

  atualizarPedido(pedido: Pedido) {
    return this.http.put(`${this.baseUrl}/pedidos/${pedido.id}`, pedido);
  }

}
