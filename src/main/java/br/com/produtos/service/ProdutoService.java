package br.com.produtos.service;

import br.com.produtos.config.ProdutoRabbitmq;
import br.com.produtos.controller.ProdutoController;
import br.com.produtos.entidade.Produto;
import br.com.produtos.fila.PedidoFila;
import br.com.produtos.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

@Service
public class ProdutoService {
    private final Logger logger = LoggerFactory.getLogger(ProdutoService.class);
    private final ProdutoRepository produtoRepository;
    private final PedidoFila pedidoFila;

    public ProdutoService(ProdutoRepository produtoRepository,  PedidoFila pedidoFila) {
        this.produtoRepository = produtoRepository;
        this.pedidoFila = pedidoFila;
    }

    public List<Produto> buscarTodos() {
        logger.info("Buscar Realizado com sucesso");
        return produtoRepository.findAll();
    }

    public Produto buscarProdutoPorID(Long id) {
        logger.info("Buscar Realizado com sucesso");
        return produtoRepository.findById(id).orElseThrow(()-> new HttpStatusCodeException(HttpStatus.NOT_FOUND) {
        });
    }

    public Produto salvarProduto(@Valid Produto produto) {
        logger.info("Salvar com sucesso");
        produto =produtoRepository.save(produto);
        pedidoFila.AdicionarFila(produto);
        return produto;
    }

    public String deletarProdutoPorID(Long id) {
        var produto = this.buscarProdutoPorID(id);
        produtoRepository.delete(produto);
        logger.info("Deletado com sucesso");
        return "Deletado com sucesso";
    }
}
