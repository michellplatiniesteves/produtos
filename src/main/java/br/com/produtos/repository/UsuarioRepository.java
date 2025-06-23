package br.com.produtos.repository;

import br.com.produtos.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByEmail(String email);

    @Query(value = "select u from Usuario u where u.nome like %:nome%")
    List<Usuario> buscarPorNome(@Param("nome")String nome);
}
