package br.com.zupedu.olucas.proposta.advices;

import java.util.List;

class ApiError {
    private final String message;
    private final List<Error> errors;

    ApiError(String message, List<Error> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() { return message; }

    public List<Error> getErrors() { return errors; }
}
