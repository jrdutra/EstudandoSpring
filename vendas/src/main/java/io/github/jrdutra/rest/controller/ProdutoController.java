package io.github.jrdutra.rest.controller;

import io.github.jrdutra.domain.entity.Produto;
import io.github.jrdutra.domain.repository.ProdutoDao;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ProdutoController {

    private ProdutoDao daoProduto;

    public ProdutoController(ProdutoDao daoProduto){
        this.daoProduto = daoProduto;
    }

    @GetMapping("{id}")
    public Produto getProdutoById(@PathVariable Integer id){
        return daoProduto.findById(id).orElseThrow(()->{
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        });
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody Produto produto){
        return daoProduto.save(produto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        daoProduto
                .findById(id)
                .map(p -> {
                   daoProduto.delete(p);
                   return p;
                })
                .orElseThrow(
                        ()->{
                            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
                        }
                );
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Produto produto){

        daoProduto
                .findById(id)
                .map(p->{
                    produto.setId(p.getId());
                    daoProduto.save(produto);
                    return p;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrdo") );


    }

    @GetMapping
    public List<Produto> find(Produto filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING
                );
        Example example = Example.of(filtro, matcher);
        return daoProduto.findAll(example);
    }


}
