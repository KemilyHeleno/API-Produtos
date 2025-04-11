package com.exemplo.produtos.service;

import com.exemplo.produtos.model.Produto;
import com.exemplo.produtos.model.Categoria;
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

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto salvarProduto(Produto produto) {
        Categoria categoria = categoriaRepository.findById(produto.getCategoria().getId())
                .orElse(null);

        if (categoria == null) return null;

        // Verificar duplicidade de nome na mesma categoria
        boolean existe = produtoRepository.existsByNomeAndCategoriaId(produto.getNome(), categoria.getId());
        if (existe) return null;

        produto.setCategoria(categoria);
        return produtoRepository.save(produto);
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

    public Produto atualizarProduto(Long id, Produto novoProduto) {
        return produtoRepository.findById(id).map(produto -> {
            produto.setNome(novoProduto.getNome());
            produto.setPreco(novoProduto.getPreco());
            produto.setQuantidade(novoProduto.getQuantidade());
            produto.setCategoria(novoProduto.getCategoria());
            return produtoRepository.save(produto);
        }).orElse(null);
    }

    public boolean excluirProduto(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            if (produto.get().getQuantidade() > 0) return false;
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
