import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/index';
import { ApiResponse } from '../model/api.response';

@Injectable()
export class ApiService {

  baseUrl = 'http://localhost:8080/api';
  constructor(private http: HttpClient, private router: Router) { }

  login(loginData): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.baseUrl}/auth/login`, loginData);
  }

  logout(): void {
    this.clear();
    this.router.navigate(['/login']);
  }

  clear(): void {
    localStorage.clear();
  }

  isAuthenticated(): boolean {
    return localStorage.getItem('token') != null;
  }

}
