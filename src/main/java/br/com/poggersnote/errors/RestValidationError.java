package br.com.poggersnote.errors;

public record RestValidationError(
        Integer code,
        String field,
        String message
) {}
