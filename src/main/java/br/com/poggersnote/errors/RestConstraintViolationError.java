package br.com.poggersnote.errors;

public record RestConstraintViolationError(
        int code,
        Object field,
        String message
) {}
