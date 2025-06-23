package br.com.produtos.controller;

import br.com.produtos.entidade.DTO.CriarUsuarioDto;
import br.com.produtos.entidade.DTO.LoginUsuarioDto;
import br.com.produtos.entidade.DTO.RetornarJwtTokenDto;
import br.com.produtos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/test")
    public ResponseEntity<String> getAuthenticationTest() {
        return new ResponseEntity<>("Autenticado com sucesso", HttpStatus.OK);
    }
}
