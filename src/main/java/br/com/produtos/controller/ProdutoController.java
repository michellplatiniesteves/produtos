package br.com.produtos.controller;

import br.com.produtos.entidade.Produto;
import br.com.produtos.service.ProdutoService;
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
@RequestMapping("/produto")
public class ProdutoController {
    private final Logger logger = LoggerFactory.getLogger(ProdutoController.class);
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/")
    @Operation(summary = "Buscar Todos", description = "Buscar Todos os Produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A Requisição foi bem sucedida"),
            @ApiResponse(responseCode = "202", description = "A requisição foi recebida pelo servidor, mas ainda não foi completamente processada."),
            @ApiResponse(responseCode = "400", description = "O servidor não vai processar a requisição por um erro nas informações enviadas pelo cliente"),
            @ApiResponse(responseCode = "404 ", description = "O servidor não encontrou uma representação atual do recurso solicitado")
    })
    public ResponseEntity<List<Produto>> buscarTodos() {
        return ResponseEntity.ok(produtoService.buscarTodos());
    }
    @GetMapping("/{id}")
    @Operation(summary = "Buscar pelo ID", description = "Buscar produto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A Requisição foi bem sucedida"),
            @ApiResponse(responseCode = "202", description = "A requisição foi recebida pelo servidor, mas ainda não foi completamente processada."),
            @ApiResponse(responseCode = "400", description = "O servidor não vai processar a requisição por um erro nas informações enviadas pelo cliente"),
            @ApiResponse(responseCode = "404 ", description = "O servidor não encontrou uma representação atual do recurso solicitado")
    })
    public ResponseEntity<Produto> buscarProdutoPorID(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorID(id));
    }
    @PostMapping("/")
    @Operation(summary = "Salvar Produto", description = "Adicionar o Produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A Requisição foi bem sucedida"),
            @ApiResponse(responseCode = "202", description = "A requisição foi recebida pelo servidor, mas ainda não foi completamente processada."),
            @ApiResponse(responseCode = "400", description = "O servidor não vai processar a requisição por um erro nas informações enviadas pelo cliente"),
            @ApiResponse(responseCode = "404 ", description = "O servidor não encontrou uma representação atual do recurso solicitado")
    })
    public ResponseEntity<Produto> salvarProduto(@Valid @RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.salvarProduto(produto));
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
        return ResponseEntity.ok(produtoService.deletarProdutoPorID(id));
    }
}
