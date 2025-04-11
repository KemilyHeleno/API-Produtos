package com.exemplo.produtos.controller;

import com.exemplo.produtos.model.Categoria;
import com.exemplo.produtos.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*") // Em produção, evite o "*" e defina as origens permitidas
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Listar todas as categorias
    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodas() {
        List<Categoria> categorias = categoriaService.listarTodas();
        if (categorias.isEmpty()) {
            return ResponseEntity.noContent().build();  // Retorna 204 se não houver categorias
        }
        return ResponseEntity.ok(categorias);  // Retorna 200 com a lista de categorias
    }

    // Buscar uma categoria pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar uma nova categoria
    @PostMapping
    public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria) {
        Categoria categoriaSalva = categoriaService.salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva); // Retorna com status 201
    }

    // Atualizar categoria
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
        return categoriaService.buscarPorId(id)
                .map(catExistente -> {
                    categoria.setId(id); // Atualizando o ID para manter a consistência
                    return ResponseEntity.status(HttpStatus.OK).body(categoriaService.salvar(categoria)); // Retorna com status 200
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (categoriaService.buscarPorId(id).isPresent()) {
            categoriaService.deletar(id);
            return ResponseEntity.noContent().build(); // Retorna status 204 para exclusão bem-sucedida
        }
        return ResponseEntity.notFound().build(); // Retorna status 404 se a categoria não for encontrada
    }
}
