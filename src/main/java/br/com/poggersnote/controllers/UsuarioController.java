package br.com.poggersnote.controllers;

import br.com.poggersnote.dtos.LoginDTO;
import br.com.poggersnote.dtos.LoginResponseDTO;
import br.com.poggersnote.dtos.UsuarioResponseDTO;
import br.com.poggersnote.models.Usuario;
import br.com.poggersnote.services.UsuarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @PostMapping("cadastrar")
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@Valid @RequestBody Usuario usuario)
    {
        var usuarioCadastrado = usuarioService.cadastrar(usuario);

        var responseDTO = new UsuarioResponseDTO(usuarioCadastrado.getId(), usuarioCadastrado.getNome(), usuarioCadastrado.getEmail(), usuarioCadastrado.getSenha());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> logar(@Valid @RequestBody LoginDTO credenciais)
    {
        log.info("solicitando validação das credenciais informadas");

        LoginResponseDTO responseService = usuarioService.logar(credenciais);

        return ResponseEntity.status(HttpStatus.OK).body(responseService);
    }
}
