package com.jrdutra.easytask.api.controller;

import com.jrdutra.easytask.api.model.Tarefa;
import com.jrdutra.easytask.api.service.TarefasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class TarefasController {

    @Autowired
    private TarefasService service;

    @GetMapping
    public String hello(){
        return "Bem vindo ao microserviço easyTask\n";
    }

    @PostMapping("/tarefas")
    public Tarefa create(@RequestBody Tarefa tarefa){
        return service.save(tarefa);
    }

    @GetMapping("/tarefas")
    public List<Tarefa> getAll(){
        return service.findAll();
    }

    @GetMapping("/tarefas/{id}")
    public Tarefa findById(@PathVariable String id){
        return service.findById(id);
    }

    @GetMapping("/tarefas/descricao/{descricao}")
    public List<Tarefa> findByDescricao(@PathVariable String descricao){
        return service.findByDescricao(descricao);
    }

    @PutMapping("/tarefas/{id}")
    public void update(@PathVariable String id, @RequestBody Tarefa tarefa){
        service.update(id, tarefa);
    }

    @DeleteMapping("/tarefas/{id}")
    public void delete(@PathVariable String id){
        service.deleteById(id);
    }

}
