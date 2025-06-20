package br.com.produtos.controller;

import br.com.produtos.entidade.Categoria;
import br.com.produtos.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    private final Logger logger = LoggerFactory.getLogger(CategoriaController.class);
    private final CategoriaService categoriaService ;

    public CategoriaController( CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/")
    @Operation(summary = "Buscar Todos", description = "Buscar Todos as categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A Requisição foi bem sucedida"),
            @ApiResponse(responseCode = "202", description = "A requisição foi recebida pelo servidor, mas ainda não foi completamente processada."),
            @ApiResponse(responseCode = "400", description = "O servidor não vai processar a requisição por um erro nas informações enviadas pelo cliente"),
            @ApiResponse(responseCode = "404 ", description = "O servidor não encontrou uma representação atual do recurso solicitado")
    })
    public ResponseEntity<List<Categoria>> buscarTodos() {
        return ResponseEntity.ok(categoriaService.buscarTodos());
    }
    @GetMapping("/{id}")
    @Operation(summary = "Buscar pelo ID", description = "Buscar Categoria por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A Requisição foi bem sucedida"),
            @ApiResponse(responseCode = "202", description = "A requisição foi recebida pelo servidor, mas ainda não foi completamente processada."),
            @ApiResponse(responseCode = "400", description = "O servidor não vai processar a requisição por um erro nas informações enviadas pelo cliente"),
            @ApiResponse(responseCode = "404 ", description = "O servidor não encontrou uma representação atual do recurso solicitado")
    })
    public ResponseEntity<Categoria> buscarProdutoPorID(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarCategoriaPorID(id));
    }
    @PostMapping("/")
    @Operation(summary = "Salvar Categoria", description = "Adicionar a Categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A Requisição foi bem sucedida"),
            @ApiResponse(responseCode = "202", description = "A requisição foi recebida pelo servidor, mas ainda não foi completamente processada."),
            @ApiResponse(responseCode = "400", description = "O servidor não vai processar a requisição por um erro nas informações enviadas pelo cliente"),
            @ApiResponse(responseCode = "404 ", description = "O servidor não encontrou uma representação atual do recurso solicitado")
    })
    public ResponseEntity<Categoria> salvarCategoria(@Valid @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.salvarCategoria(categoria));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar pelo ID", description = "Deletar produto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A Requisição foi bem sucedida"),
            @ApiResponse(responseCode = "202", description = "A requisição foi recebida pelo servidor, mas ainda não foi completamente processada."),
            @ApiResponse(responseCode = "400", description = "O servidor não vai processar a requisição por um erro nas informações enviadas pelo cliente"),
            @ApiResponse(responseCode = "404 ", description = "O servidor não encontrou uma representação atual do recurso solicitado")
    })
    public ResponseEntity<String> deletarProdutoPorID(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.deletarCategoriaPorID(id));
    }
}
