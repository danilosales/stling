import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AuthGuardService } from './guards/auth.guard.service';
import { ClientesComponent } from './clientes/clientes.component';
import { ProdutosComponent } from './produtos/produtos.component';
import { PedidosComponent } from './pedidos/pedidos.component';
import { EditClienteComponent } from './clientes/edit-cliente/edit-cliente.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuardService] },
  { path: 'clientes', component: ClientesComponent, canActivate: [AuthGuardService] },
  { path: 'produtos', component: ProdutosComponent, canActivate: [AuthGuardService] },
  { path: 'pedidos', component: PedidosComponent, canActivate: [AuthGuardService] },
  { path: 'clientes/novo', component: EditClienteComponent, canActivate: [AuthGuardService] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
