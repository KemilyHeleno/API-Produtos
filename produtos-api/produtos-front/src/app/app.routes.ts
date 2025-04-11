import { Routes } from '@angular/router';
import { ProdutoListComponent } from './pages/produtos/produto-list/produto-list.component';
import { ProdutoFormComponent } from './pages/produtos/produto-form/produto-form.component';


export const routes: Routes = [

  { path: '', component: ProdutoListComponent },
  { path: 'novo', component: ProdutoFormComponent },
  { path: 'editar/:id', component: ProdutoFormComponent },
  {
    path: '',
    redirectTo: 'produtos',
    pathMatch: 'full',
  },
  {
    path: 'produtos',
    component: ProdutoListComponent,
  },
  {
    path: 'produtos/novo',
    component: ProdutoFormComponent,
  },
  {
    path: 'produtos/:id',
    component: ProdutoFormComponent,
  },
];
