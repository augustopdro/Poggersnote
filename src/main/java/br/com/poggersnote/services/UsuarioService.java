package br.com.poggersnote.services;

import br.com.poggersnote.dtos.LoginDTO;
import br.com.poggersnote.dtos.LoginResponseDTO;
import br.com.poggersnote.exceptions.RestNotFoundException;
import br.com.poggersnote.exceptions.RestUnauthorizedException;
import br.com.poggersnote.models.Usuario;
import br.com.poggersnote.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    Logger log = LoggerFactory.getLogger(UsuarioService.class);

    private UsuarioRepository repository;

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario cadastrar(Usuario usuario)
    {
        log.info("Cadastrando usuario: " + usuario);

        return repository.save(usuario);
    }


    public Usuario recuperar(long id)
    {
        log.info("Recuperando cadastro de usuario pelo id: " + id);

        Usuario usuario = repository
                .findById(id)
                .orElseThrow(() -> new RestNotFoundException("Usuario não encontrado"));

        return usuario;
    }

    public LoginResponseDTO logar(LoginDTO credenciais)
    {
        log.info("Validando credenciais informadas");

        Usuario usuario = repository
                .buscarCredenciais(credenciais.email(), credenciais.senha())
                .orElseThrow(() -> new RestUnauthorizedException("Usuário ou Senha incorretos"));

        long acesso = usuario.getId();
        return new LoginResponseDTO(acesso);
    }
}
