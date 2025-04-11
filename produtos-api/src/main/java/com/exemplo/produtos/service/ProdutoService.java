package com.exemplo.produtos.service;

import com.exemplo.produtos.model.Produto;
import com.exemplo.produtos.repository.ProdutoRepository;
import com.exemplo.produtos.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // ... outros métodos como salvar, listar, deletar, etc.

    public Integer calcularEstoqueTotalPorCategoria(Long categoriaId) {
        return produtoRepository.calcularEstoqueTotalPorCategoria(categoriaId);
    }


    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto salvarProduto(Produto produto) {
        if (produto.getCategoria() == null ||
            !categoriaRepository.existsById(produto.getCategoria().getId())) {
            return null; // categoria inexistente
        }

        if (produtoRepository.existsByNomeAndCategoriaId(produto.getNome(), produto.getCategoria().getId())) {
            return null; // produto duplicado
        }

        return produtoRepository.save(produto);
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Optional<Produto> existente = produtoRepository.findById(id);
        if (existente.isEmpty()) {
            return null;
        }

        Produto produto = existente.get();
        produto.setNome(produtoAtualizado.getNome());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setQuantidade(produtoAtualizado.getQuantidade());
        produto.setCategoria(produtoAtualizado.getCategoria());

        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Produto> buscarPorCategoria(Long categoriaId) {
        return produtoRepository.findByCategoriaId(categoriaId);
    }

    public void deletarProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (produto.getQuantidade() > 0) {
            throw new IllegalStateException("Não é possível excluir um produto com estoque maior que zero.");
        }

        produtoRepository.delete(produto);
    }

    // 🔢 Novo método: calcular estoque total por categoria
    public Integer calcularEstoquePorCategoria(Long categoriaId) {
        return produtoRepository.calcularEstoqueTotalPorCategoria(categoriaId);
    }
}
