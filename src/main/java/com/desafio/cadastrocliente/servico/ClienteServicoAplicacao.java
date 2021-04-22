package com.desafio.cadastrocliente.servico;

import com.desafio.cadastrocliente.dominios.CadastroEndereco;
import com.desafio.cadastrocliente.dominios.Cliente;
import com.desafio.cadastrocliente.excecoes.ViolacaoRegraException;
import com.desafio.cadastrocliente.repositorios.CadastroEnderecoRepositorio;
import com.desafio.cadastrocliente.repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicoAplicacao {
    @Autowired
    private ClienteRepositorio repositorio;

    @Autowired
    private CadastroEnderecoRepositorio enderecoRepositorio;

    public void excluirEndereco (Long codigoEndereco){
        Optional<CadastroEndereco> optional = enderecoRepositorio.findById(codigoEndereco);
        if (optional.isPresent()){
            enderecoRepositorio.delete(optional.get());
        }
    }

    private CadastroEndereco criarEndereco(ComandoCriarEndereco comando){
        CadastroEndereco endereco = new CadastroEndereco();
        endereco.setBairro(comando.getBairro());
        endereco.setCep(comando.getCep());
        endereco.setCidade(comando.getCidade());
        endereco.setComplemento(comando.getComplemento());
        endereco.setLogradouro(comando.getLogradouro());
        endereco.setEstado(comando.getEstado());
        endereco.setNumero(comando.getNumero());
        return endereco;

    }

    public ConsultaEndereco adicionarEndereco(long codigoCliente, ComandoCriarEndereco comando){
        Cliente cliente = obter(codigoCliente);
        if (cliente == null){
            throw new ViolacaoRegraException("Cliente Inexistente");
        }
        CadastroEndereco endereco = criarEndereco(comando);
        endereco.setCliente(cliente);
        cliente.getEnderecos().add(endereco);
        enderecoRepositorio.save(endereco);
        repositorio.save(cliente);

        return converterEndereco(endereco);
    }

    private Cliente obter(Long codigo){
        Optional<Cliente> optional = repositorio.findById(codigo);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public  ConsultaCliente consultar(Long codigo){
        Cliente cliente = obter(codigo);
        return converter(cliente);
    }

    private ConsultaCliente converter(Cliente cliente) {
        ConsultaCliente retorno = new ConsultaCliente();
        retorno.setCodigo(cliente.getCodigo());
        retorno.setCpf(cliente.getCpf());
        retorno.setDataNascimento(cliente.getDataNascimento());
        retorno.setEnderecos(convertListaEndereco(cliente.getEnderecos()));
        retorno.setEmail(cliente.getEmail());
        retorno.setNome(cliente.getNome());
        return retorno;
    }

    private List<ConsultaEndereco> convertListaEndereco(List<CadastroEndereco> enderecos) {
        List<ConsultaEndereco> retorno = new ArrayList<>();
        for (CadastroEndereco endereco: enderecos) {
            ConsultaEndereco novo = converterEndereco(endereco);
            retorno.add(novo);
        }

        return retorno;
    }

    private ConsultaEndereco converterEndereco(CadastroEndereco endereco) {
        ConsultaEndereco novo = new ConsultaEndereco();
        novo.setCodigoCliente(endereco.getCliente().getCodigo());
        novo.setLogradouro(endereco.getLogradouro());
        novo.setBairro(endereco.getBairro());
        novo.setCidade(endereco.getCidade());
        novo.setComplemento(endereco.getComplemento());
        novo.setEstado(endereco.getEstado());
        novo.setCep(endereco.getCep());
        novo.setNumero(endereco.getNumero());
        novo.setCodigo(endereco.getCodigo());
        novo.setNomeCliente(endereco.getCliente().getNome());
        return novo;
    }

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
            throw new ViolacaoRegraException("Cliente não localizado.");
        }

    }

    @Transactional
    public Long criar(ComandoCriarCliente comando) {
        Cliente cliente = repositorio.findClienteByNomeAndCpf(comando.getNome(), comando.getCpf());

        if(cliente != null) {
            throw new ViolacaoRegraException("Cliente com nome e CPF já existe na base");
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
