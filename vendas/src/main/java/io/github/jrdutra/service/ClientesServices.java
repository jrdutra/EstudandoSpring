package io.github.jrdutra.service;

import io.github.jrdutra.model.Cliente;
import io.github.jrdutra.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesServices {

    private ClientesRepository repository;

    @Autowired
    public ClientesServices(ClientesRepository repository){
        this.repository = repository;
    }

    public void salvarCLiente(Cliente cliente){
        validarCliente(cliente);
        this.repository.persistir(cliente);
    }


    public void validarCliente(Cliente cliente){
        //aplica validacoes
    }

}
