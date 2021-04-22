package com.desafio.cadastrocliente.servico;

import com.desafio.cadastrocliente.dominios.Cliente;
import com.desafio.cadastrocliente.repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Optional;

@Service
public class ClienteServicoAplicacao {
    @Autowired
    private ClienteRepositorio repositorio;

    @Transactional
    public Cliente alterar(Long codigo, ComandoAlterarCliente comando){
        Optional<Cliente> optional  = repositorio.findById(codigo);

        if(optional.isPresent()){
              Cliente cliente = optional.get();
              cliente.setNome(comando.getNome());
              cliente.setCpf(comando.getCpf());
              cliente.setEmail(comando.getEmail());
              cliente.setDataNascimento(comando.getDataNascimento());
              repositorio.save(cliente);
              return cliente;
        } else {
            throw new RuntimeException("Cliente não localizado.");
        }

    }

    @Transactional
    public Long criar(ComandoCriarCliente comando) {
        Cliente cliente = repositorio.findClienteByNomeAndCpf(comando.getNome(), comando.getCpf());

        if(cliente != null) {
            throw new RuntimeException("Cliente com nome e CPF já existe na base");
        }

        cliente = new Cliente();
        cliente.setNome(comando.getNome());
        cliente.setCpf(comando.getCpf());
        cliente.setEmail(comando.getEmail());
        cliente.setDataNascimento(comando.getDataNascimento());

        Cliente clienteSalvo = repositorio.save(cliente);
        return clienteSalvo.getCodigo();


    }
    public void excluir (Long codigo){
        Optional<Cliente> optional = repositorio.findById(codigo);

        if (optional.isPresent()) {
            repositorio.delete(optional.get());
        }
    }
}
