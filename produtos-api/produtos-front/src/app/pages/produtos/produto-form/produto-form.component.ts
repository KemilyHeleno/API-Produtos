import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, RouterModule, Router } from '@angular/router';
import { ProdutoService, Produto } from '../../../services/produto.service';

@Component({
  selector: 'app-produto-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './produto-form.component.html'
})
export class ProdutoFormComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private produtoService = inject(ProdutoService);

  produto: Produto = {
    id: 0,
    nome: '',
    preco: 0,
    quantidadeEstoque: 0,
    categoria: { id: 0, nome: '' }
  };

  categorias: any[] = [];

  salvarProduto() {
    console.log('Produto salvo!');
  }

  ngOnInit() {
    this.produtoService.listarCategorias().subscribe(categorias => {
      this.categorias = categorias;
    });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.produtoService.buscarPorId(+id).subscribe(produto => {
        this.produto = produto;
      });
    }
  }

  salvar() {
    if (this.produto.id) {
      this.produtoService.atualizar(this.produto.id, this.produto).subscribe(() => {
        this.router.navigate(['/produtos']);
      });
    } else {
      this.produtoService.criar(this.produto).subscribe(() => {
        this.router.navigate(['/produtos']);
      });
    }
  }
}
