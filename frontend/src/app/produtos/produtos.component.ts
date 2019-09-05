import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Produto } from './../model/Produto';
import { Component, OnInit } from '@angular/core';
import { ApiService } from '../service/api.service';

@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.css']
})
export class ProdutosComponent implements OnInit {

  produtos: Produto[];
  produtosForm: FormGroup;
  submitted = false;
  buscouServico = false;
  constructor(private apiService: ApiService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.produtosForm = this.formBuilder.group({
      campoBusca: ['', Validators.required]
    });
  }

  buscar() {
    if (this.produtosForm.invalid) {
      this.submitted = true;
      return;
    }

    this.apiService.buscarProdutos(this.produtosForm.get('campoBusca').value)
      .subscribe((data: Produto[]) => {
        this.produtos = data;
    });
    this.buscouServico = true;
  }

}
