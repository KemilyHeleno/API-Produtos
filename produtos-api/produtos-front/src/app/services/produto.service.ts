import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Produto {
  id: number;
  nome: string;
  preco: number;
  quantidadeEstoque: number;
  categoria: {
    id: number;
    nome: string;
  };
}

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {
  private readonly api = 'http://localhost:8080/api/produtos';
  private readonly categoriaApi = 'http://localhost:8080/api/categorias';

  constructor(private http: HttpClient) {}

  listar(): Observable<Produto[]> {
    return this.http.get<Produto[]>(this.api);
  }

  buscarPorId(id: number): Observable<Produto> {
    return this.http.get<Produto>(`${this.api}/${id}`);
  }

  criar(produto: Produto): Observable<Produto> {
    return this.http.post<Produto>(this.api, produto);
  }

  atualizar(id: number, produto: Produto): Observable<Produto> {
    return this.http.put<Produto>(`${this.api}/${id}`, produto);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }

  listarCategorias(): Observable<any[]> {
    return this.http.get<any[]>(this.categoriaApi);
  }
}
