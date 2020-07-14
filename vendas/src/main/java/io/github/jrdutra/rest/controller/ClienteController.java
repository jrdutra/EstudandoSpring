package io.github.jrdutra.rest.controller;


import io.github.jrdutra.domain.entity.Cliente;
import io.github.jrdutra.domain.repository.ClienteDao;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ClienteController {

    private ClienteDao daoCliente;

    public ClienteController(ClienteDao daoCliente){
        this.daoCliente = daoCliente;
    }

    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable Integer id){
        Optional<Cliente> cliente = daoCliente.findById(id);
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/clientes")
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente){
        Cliente clienteSalvo = daoCliente.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id){
        Optional<Cliente> cliente = daoCliente.findById(id);
        if(cliente.isPresent()){
            daoCliente.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable Integer id,
                                 @RequestBody Cliente cliente){
        return daoCliente
                .findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    daoCliente.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/api/clientes")
    public ResponseEntity find(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING
                );
        Example exemple = Example.of(filtro, matcher);
        List<Cliente> lista = daoCliente.findAll(exemple);
        return ResponseEntity.ok(lista);
    }


}
