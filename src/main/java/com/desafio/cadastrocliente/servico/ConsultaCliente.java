package com.desafio.cadastrocliente.servico;

import javax.persistence.Column;
import java.util.List;

public class ConsultaCliente {
    private Long codigo;
    private String cpf;
    private String email;
    private String dataNascimento;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<ConsultaEndereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<ConsultaEndereco> enderecos) {
        this.enderecos = enderecos;
    }

    private List<ConsultaEndereco> enderecos;
}
