package br.com.poggersnote.services;

import br.com.poggersnote.exceptions.RestNotFoundException;
import br.com.poggersnote.models.Registro;
import br.com.poggersnote.models.Usuario;
import br.com.poggersnote.repositories.RegistroRepository;
import br.com.poggersnote.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegistroService {

    private RegistroRepository repository;
    private UsuarioRepository usuarioRepository;

    Logger log = LoggerFactory.getLogger(RegistroService.class);

    @Autowired
    public RegistroService(RegistroRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public Map<Boolean, Registro> registrarNota(Registro registro, long userId) {
        var retornoMetodo = new HashMap<Boolean, Registro>();

        var usuario = recuperarUsuario(userId);

        var registroExistente = registroExiste(usuario.getRegistro(), registro);

        if(registroExistente == null)
        {
            registro.setUsuario(usuario);
            registro.setDataCriacao();
            usuario.getRegistro().add(registro);

            var registroSalvo = salvarRegistro(registro);

            retornoMetodo.put(false, registroSalvo);
        }else
        {
            registroExistente.setData(registro.getData());

            var registroSalvo = atualizarRegistro(registroExistente);

            retornoMetodo.put(true, registroSalvo);;
        }

        return retornoMetodo;
    }

    public Registro atualizarNota(Registro registro,long id)
    {
        var registroExistente = recuperarRegistro(id);
        registroExistente.setTitulo(registro.getTitulo());
        registroExistente.setDescricao(registro.getDescricao());
        return atualizarRegistro(registroExistente);
    }

    @Transactional
    public void deletarRegistro(long userId, long registroId) {
        var usuario = recuperarUsuario(userId);

        var registro = recuperarRegistro(registroId);

        if (!registro.getUsuario().equals(usuario)) {
            log.info("getid: " + registro.getUsuario().getId());
            throw new RestNotFoundException("Registro informado não pertence ao Usuário informado");
        }

        usuario.getRegistro().remove(registro);
        usuarioRepository.save(usuario);
        repository.delete(registro);
    }

    private Registro salvarRegistro(Registro registro){
        log.info("Registrando uma anotação: " + registro);
        return repository.save(registro);
    }

    private Registro registroExiste(List<Registro> registrosPersistidos, Registro registroAComparar){
        log.info("Verificando se já existe registro no dia");

        for (Registro reg : registrosPersistidos) {
            if (reg.getData().equals(registroAComparar.getData()))
                return reg;
        }

        return null;
    }

    private Registro atualizarRegistro(Registro registro) {
        log.info("Atualizando registro do dia: " + registro);

        return repository.save(registro);
    }

    private Usuario recuperarUsuario(long userId) {
        log.info("Recuperando usuario com id: " + userId);

        Usuario usuario = usuarioRepository
                .findById(userId)
                .orElseThrow(() -> new RestNotFoundException("Usuario não encontrado"));

        return usuario;
    }

    private Registro recuperarRegistro(long registroId) {
        log.info("Recuperando registro com id: " + registroId);

        Registro registro = repository
                .findById(registroId)
                .orElseThrow(() -> new RestNotFoundException("Registro não encontrado"));

        return registro;
    }
}
