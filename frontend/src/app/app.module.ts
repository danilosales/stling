import { AutocompleteModule } from './components/autocomplete/autocomplete.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { TokenInterceptor } from './core/interceptor';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NavbarService } from './service/navbar.service';
import { ApiService } from './service/api.service';
import { AuthGuardService } from './guards/auth.guard.service';
import { ClientesComponent } from './clientes/clientes.component';
import { ProdutosComponent } from './produtos/produtos.component';
import { PedidosComponent } from './pedidos/pedidos.component';
import { ConfirmDialogModule } from './components/confirm-dialog/confirm-dialog.module';
import { EditClienteComponent } from './clientes/edit-cliente/edit-cliente.component';
import { EditPedidoComponent } from './pedidos/edit-pedido/edit-pedido.component';
import { OverlayModule } from '@angular/cdk/overlay';
import { AutocompleteComponent } from './components/autocomplete/autocomplete.component';
import { OptionComponent } from './components/autocomplete/option/option.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    NavbarComponent,
    ClientesComponent,
    ProdutosComponent,
    PedidosComponent,
    EditClienteComponent,
    EditPedidoComponent,
    OptionComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    OverlayModule,
    BrowserAnimationsModule,
    AutocompleteModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ConfirmDialogModule,
  ],
  providers: [ApiService, {provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true},
    NavbarService,
    AuthGuardService,
    ApiService
  ],
  bootstrap: [AppComponent],
  entryComponents: [AutocompleteComponent]
})
export class AppModule { }
