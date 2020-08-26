package com.jrdutra.easytask.api.repository;

import com.jrdutra.easytask.api.model.Tarefa;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TarefasRepository extends MongoRepository<Tarefa, String> {

    List<Tarefa> findByDescricaoLikeIgnoreCase(String descricao);

}
