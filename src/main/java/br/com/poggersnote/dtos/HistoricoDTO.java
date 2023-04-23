package br.com.poggersnote.dtos;

import br.com.poggersnote.models.Registro;
import java.util.List;

public record HistoricoDTO(List<Registro> registros) {}

