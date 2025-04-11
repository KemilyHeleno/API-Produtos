package com.exemplo.produtos.controller;

import com.exemplo.produtos.model.Categoria;
import com.exemplo.produtos.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@Valid @RequestBody Categoria categoria) {
        Categoria salva = categoriaService.salvarCategoria(categoria);
        return ResponseEntity.ok(salva);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCategoria(@PathVariable Long id, @Valid @RequestBody Categoria categoriaAtualizada) {
        Categoria atualizada = categoriaService.atualizarCategoria(id, categoriaAtualizada);
        if (atualizada != null) {
            return ResponseEntity.ok(atualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirCategoria(@PathVariable Long id) {
        boolean deletada = categoriaService.excluirCategoria(id);
        if (deletada) {
            return ResponseEntity.ok("Categoria excluída com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Categoria não encontrada.");
        }
    }
}
