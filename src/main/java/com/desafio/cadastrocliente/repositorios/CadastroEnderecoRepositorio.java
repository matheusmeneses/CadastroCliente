package com.desafio.cadastrocliente.repositorios;

import com.desafio.cadastrocliente.dominios.CadastroEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroEnderecoRepositorio extends JpaRepository<CadastroEndereco, Long> {
}
