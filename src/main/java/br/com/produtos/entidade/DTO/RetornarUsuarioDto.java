package br.com.produtos.entidade.DTO;

import java.util.List;

public record RetornarUsuarioDto(Long id,
                                 String email,
                                 List<Roles> roles) {
}
