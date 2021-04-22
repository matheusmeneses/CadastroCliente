package com.desafio.cadastrocliente.controller;

import com.desafio.cadastrocliente.dominios.Cliente;
import com.desafio.cadastrocliente.servico.ClienteServicoAplicacao;
import com.desafio.cadastrocliente.servico.ComandoAlterarCliente;
import com.desafio.cadastrocliente.servico.ComandoCriarCliente;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
    @Autowired
    private ClienteServicoAplicacao servico;

    @DeleteMapping("/clientes/{codigo}")
    public ResponseEntity excluir (@PathVariable(name = "codigo") Long codigo){
        servico.excluir(codigo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/clientes")
    public ResponseEntity<Long> criar(@RequestBody  ComandoCriarCliente comando) {
        Long codigo = servico.criar(comando);
        return ResponseEntity.ok(codigo);
    }

    @PutMapping("/clientes/{codigo}")
    public ResponseEntity<Cliente> alterar(@PathVariable(name = "codigo") Long codigo, @RequestBody ComandoAlterarCliente comando){
        Cliente cliente = servico.alterar(codigo, comando);
        return ResponseEntity.ok(cliente);
    }
}
