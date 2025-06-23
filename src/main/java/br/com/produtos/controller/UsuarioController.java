package br.com.produtos.controller;

import br.com.produtos.entidade.DTO.CriarUsuarioDto;
import br.com.produtos.entidade.DTO.LoginUsuarioDto;
import br.com.produtos.entidade.DTO.RetornarJwtTokenDto;
import br.com.produtos.entidade.Usuario;
import br.com.produtos.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService userService;

    @PostMapping("/login")
    public ResponseEntity<RetornarJwtTokenDto> authenticateUser(@RequestBody LoginUsuarioDto loginUserDto) {
        RetornarJwtTokenDto token = userService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CriarUsuarioDto createUserDto) {
        userService.CriarUsuario(createUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    @Operation(summary = "Buscar Usuario",description = "Buscar Usuario Por nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A Requisição foi bem sucedida"),
            @ApiResponse(responseCode = "202", description = "A requisição foi recebida pelo servidor, mas ainda não foi completamente processada."),
            @ApiResponse(responseCode = "400", description = "O servidor não vai processar a requisição por um erro nas informações enviadas pelo cliente"),
            @ApiResponse(responseCode = "404 ", description = "O servidor não encontrou uma representação atual do recurso solicitado")
    })
    public ResponseEntity<List<Usuario>> buscarUsuarioPorNome(@RequestParam String nome){
        return ResponseEntity.ok(userService.buscarUsuarioPorNome(nome));
    }
}
