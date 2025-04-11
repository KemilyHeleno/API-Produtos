import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-produto-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './produto-form.component.html',
})
export class ProdutoFormComponent {
  produto = {
    nome: '',
    preco: 0,
    quantidadeEstoque: 0,
    categoriaId: null,
  };

  salvar() {
    console.log('Produto a ser salvo:', this.produto);
    // Aqui você pode chamar o ProdutoService para salvar
  }
}
