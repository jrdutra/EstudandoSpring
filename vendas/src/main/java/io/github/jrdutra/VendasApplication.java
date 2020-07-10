package io.github.jrdutra;

import ch.qos.logback.core.net.server.Client;
import io.github.jrdutra.domain.entity.Cliente;
import io.github.jrdutra.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            clientes.salvar(new Cliente("Joao"));
            clientes.salvar(new Cliente("Joao Ricardo"));

            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            todosClientes.forEach(c->{
                c.setNome(c.getNome()+" Atualizado");
                clientes.atualizar(c);
            });

            clientes.buscarPorNome("Ricar").forEach(System.out::println);

            System.out.println("Deletando Clientes");
            clientes.obterTodos().forEach(c->{
                clientes.deletar(c);
            });

            todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
