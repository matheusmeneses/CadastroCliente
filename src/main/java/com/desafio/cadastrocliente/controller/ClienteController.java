package com.desafio.cadastrocliente.controller;

import com.desafio.cadastrocliente.dominios.Cliente;
import com.desafio.cadastrocliente.servico.ClienteServicoAplicacao;
import com.desafio.cadastrocliente.servico.ComandoAlterarCliente;
import com.desafio.cadastrocliente.servico.ComandoCriarCliente;
import com.desafio.cadastrocliente.servico.ComandoCriarEndereco;
import com.desafio.cadastrocliente.servico.ConsultaCliente;
import com.desafio.cadastrocliente.servico.ConsultaEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class ClienteController {
    @Autowired
    private ClienteServicoAplicacao servico;

    @GetMapping("/clientes/{codigo}")
    public ResponseEntity<ConsultaCliente> obter (@PathVariable Long codigo) {
        ConsultaCliente cliente = servico.consultar(codigo);
        if (cliente == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/clientes/{codigo}")
    public ResponseEntity excluir (@PathVariable(name = "codigo") Long codigo){
        servico.excluir(codigo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/clientes")
    public ResponseEntity<String> criar(@RequestBody  ComandoCriarCliente comando) {
        Long codigo = servico.criar(comando);
        return ResponseEntity.created(URI.create("/clientes/"+codigo)).build();
    }

    @PutMapping("/clientes/{codigo}")
    public ResponseEntity<Cliente> alterar(@PathVariable(name = "codigo") Long codigo, @RequestBody ComandoAlterarCliente comando){
        Cliente cliente = servico.alterar(codigo, comando);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/clientes/{codigo}/enderecos")
    public ResponseEntity<ConsultaCliente> adicionarEndereco(@PathVariable(name = "codigo") Long  codigoCliente, @RequestBody ComandoCriarEndereco comando){
        ConsultaEndereco endereco = servico.adicionarEndereco(codigoCliente, comando);
        return ResponseEntity.created(URI.create("/clientes/"+codigoCliente+"/enderecos")).build();
    }
    @DeleteMapping("/clientes/{codigo}/enderecos/{codigoEndereco}")
    public ResponseEntity excluir (@PathVariable (name = "codigoEndereco")Long codigoEndereco, @PathVariable (name = "codigo") Long codigo){
    servico.excluirEndereco(codigoEndereco);
    return ResponseEntity.ok().build();
    }

}
