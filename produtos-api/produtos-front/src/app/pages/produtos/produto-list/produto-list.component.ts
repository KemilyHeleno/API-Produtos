import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ProdutoService, Produto } from '../../../services/produto.service';

@Component({
  selector: 'app-produto-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './produto-list.component.html',
})
export class ProdutoListComponent implements OnInit {
  produtos: Produto[] = [];
  private produtoService = inject(ProdutoService);

  ngOnInit() {
    this.produtoService.listar().subscribe((data: Produto[]) => {
      this.produtos = data;
    });
  }
}
