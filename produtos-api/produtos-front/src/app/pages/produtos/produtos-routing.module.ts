import { Routes } from '@angular/router';
import { ProdutoListComponent } from './produto-list/produto-list.component';

export const produtosRoutes: Routes = [
  {
    path: '',
    component: ProdutoListComponent,
  }
];
