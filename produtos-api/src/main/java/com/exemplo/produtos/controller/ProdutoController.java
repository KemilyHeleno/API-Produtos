package com.exemplo.produtos.controller;

import com.exemplo.produtos.model.Produto;
import com.exemplo.produtos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<?> criarProduto(@RequestBody Produto produto) {
        Produto salvo = produtoService.salvarProduto(produto);
        if (salvo != null) {
            return ResponseEntity.ok(salvo);
        } else {
            return ResponseEntity.badRequest()
                    .body("Categoria inexistente ou produto já existente na mesma categoria.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long id, @Valid @RequestBody Produto produtoAtualizado) {
        Produto atualizado = produtoService.atualizarProduto(id, produtoAtualizado);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProdutoPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(produtoService.buscarPorNome(nome));
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<Produto>> buscarPorCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorCategoria(id));
    }

    @GetMapping("/categoria/{id}/estoque")
    public ResponseEntity<?> calcularEstoquePorCategoria(@PathVariable Long id) {
        Integer totalEstoque = produtoService.calcularEstoqueTotalPorCategoria(id);
        return ResponseEntity.ok()
                .body("Estoque total da categoria " + id + ": " + (totalEstoque != null ? totalEstoque : 0));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            produtoService.deletarProduto(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
