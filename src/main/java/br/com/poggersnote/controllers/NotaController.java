package br.com.poggersnote.controllers;

import br.com.poggersnote.dtos.PaginationResponseDTO;
import br.com.poggersnote.exceptions.RestBadRequestException;
import br.com.poggersnote.models.Registro;
import br.com.poggersnote.services.HistoricoService;
import br.com.poggersnote.services.RegistroService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nota")
public class NotaController {

    private RegistroService registroService;
    private HistoricoService historicoService;
    private Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    public NotaController(RegistroService registroService, HistoricoService historicoService) {
        this.registroService = registroService;
        this.historicoService = historicoService;
    }


    @PostMapping("{userId}/registrar")
    public ResponseEntity<Registro> registrarSono(@Valid @RequestBody Registro registro, @PathVariable long userId)
    {
        log.info("Cadastrando registro de anotação");
        var retornoService = registroService.registrarNota(registro, userId);

        if(retornoService.containsKey(true))
            return ResponseEntity.status(HttpStatus.OK).body(retornoService.get(true));

        return ResponseEntity.status(HttpStatus.CREATED).body(retornoService.get(false));
    }

    @PutMapping("{id}")
    public ResponseEntity<Registro> atualizarNota(@RequestBody Registro registro, @PathVariable long id)
    {
        log.info("Atualizando anotação de usuario pelo id: " + id);

        var retornoService = registroService.atualizarNota(registro, id);
        if(retornoService == null)
            throw new RestBadRequestException("Atualização não efetuada. Tente novamente com dados diferentes.");

        return ResponseEntity.status(HttpStatus.OK).body(retornoService);
    }

    @DeleteMapping("{userId}/deletar/{registroId}")
    public ResponseEntity<Registro> deletarRegistro(@PathVariable long userId, @PathVariable long registroId)
    {
        log.info("Deletando anotação");

        registroService.deletarRegistro(userId, registroId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{userId}/historico")
    public PaginationResponseDTO recuperarHistorico(@PathVariable long userId, @PageableDefault(size = 3) Pageable pageable)
    {
        log.info("Buscando historico");

        return historicoService.recuperarHistorico(userId, pageable);
    }
}
