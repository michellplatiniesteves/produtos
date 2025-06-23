package br.com.produtos.entidade.DTO;

public record CriarUsuarioDto(    String nome,
                                  String email,
                                  String senha,
                                  Roles roles) {

}
