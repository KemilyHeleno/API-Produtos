import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-produtos',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.css']
})
export class ProdutosComponent {
  produtos = [
    {
      id: 1,
      nome: 'Camiseta',
      preco: 59.9,
      quantidadeEstoque: 10,
      categoria: { nome: 'Roupas' }
    },
    {
      id: 2,
      nome: 'Mouse',
      preco: 99.9,
      quantidadeEstoque: 5,
      categoria: { nome: 'Eletrônicos' }
    }
  ];
}
