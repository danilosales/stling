import { AuthService } from './../../service/auth.service';
import { Component } from '@angular/core';
import { NavbarService } from './../../service/navbar.service';

@Component({
  selector: 'app-navbar',
  templateUrl: 'navbar.component.html'
})

export class NavbarComponent {

  constructor( public nav: NavbarService, private authService: AuthService) {}

  logout() {
    this.authService.logout();
  }
}
