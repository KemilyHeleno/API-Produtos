package com.exemplo.produtos.repository;

import com.exemplo.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Buscar produtos por nome (case insensitive)
    List<Produto> findByNomeContainingIgnoreCase(String nome);

    // Buscar produtos por ID da categoria
    List<Produto> findByCategoriaId(Long categoriaId);

    // Verificar duplicação de nome de produto na mesma categoria
    boolean existsByNomeAndCategoriaId(String nome, Long categoriaId);

    // Calcular estoque total de uma categoria (soma das quantidades dos produtos)
    @Query("SELECT SUM(p.quantidade) FROM Produto p WHERE p.categoria.id = :categoriaId")
    Integer calcularEstoqueTotalPorCategoria(@Param("categoriaId") Long categoriaId);
}
