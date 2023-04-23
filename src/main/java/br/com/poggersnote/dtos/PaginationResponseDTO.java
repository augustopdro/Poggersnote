package br.com.poggersnote.dtos;

import br.com.poggersnote.models.Registro;
import java.util.List;

public record PaginationResponseDTO(
    List<Registro> content,
    int number,
    long totalElements,
    int totalPages,
    boolean first,
    boolean last
) {}
