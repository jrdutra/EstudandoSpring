package com.jrdutra.clientes.rest;

import com.jrdutra.clientes.exception.UsuarioCadastradoException;
import com.jrdutra.clientes.model.entity.Usuario;
import com.jrdutra.clientes.model.repository.UsuarioRepository;
import com.jrdutra.clientes.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {


    private final UsuarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Usuario usuario){
        try{
            service.salvar(usuario);
        }catch (UsuarioCadastradoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

}
