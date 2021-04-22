package com.desafio.cadastrocliente.excecoes;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ViolacaoRegraException extends ResponseStatusException {

    public ViolacaoRegraException(String mensagem) {
        super(HttpStatus.BAD_REQUEST,mensagem);

    }
}
