package br.com.produtos.service;

import br.com.produtos.controller.ProdutoController;
import br.com.produtos.entidade.Categoria;
import br.com.produtos.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final Logger logger = LoggerFactory.getLogger(CategoriaService.class);

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> buscarTodos() {
        logger.info("Buscar Realizado com sucesso");
        return categoriaRepository.findAll();
    }

    public Categoria buscarCategoriaPorID(Long id) {
        logger.info("Buscar Realizado com sucesso");
        return categoriaRepository.findById(id).orElseThrow(()-> new HttpStatusCodeException(HttpStatus.NOT_FOUND){});
    }

    public Categoria salvarCategoria(@Valid Categoria categoria) {
        logger.info("Salvo com sucesso");
        return categoriaRepository.save(categoria);
    }

    public String deletarCategoriaPorID(Long id) {
        var categoria = this.buscarCategoriaPorID(id);
        categoriaRepository.delete(categoria);
        logger.info("Deletado com sucesso");
        return "Deletado com sucesso";
    }
}
