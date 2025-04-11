import { Routes } from '@angular/router';
import { ProdutoListComponent } from './pages/produtos/produto-list/produto-list.component';
import { ProdutoFormComponent } from './pages/produtos/produto-form/produto-form.component';
import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
// import { routes } from './app.routes';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'produtos',
    pathMatch: 'full'
  },
  {
    path: 'produtos',
    component: ProdutoListComponent
  },
  {
    path: 'produtos/novo',
    component: ProdutoFormComponent
  }
];

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes)],
};
