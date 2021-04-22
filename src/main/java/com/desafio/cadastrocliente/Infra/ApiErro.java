package com.desafio.cadastrocliente.Infra;

public class ApiErro {
    private String status;
    private String mensagem;

    public ApiErro(String status, String mensagem){
        this.mensagem = mensagem;
        this.status = status;
    }
}
