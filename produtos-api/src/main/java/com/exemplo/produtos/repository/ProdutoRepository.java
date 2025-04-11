package com.exemplo.produtos.repository;

import com.exemplo.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    List<Produto> findByCategoriaId(Long categoriaId);
    boolean existsByNomeAndCategoriaId(String nome, Long categoriaId);
}
