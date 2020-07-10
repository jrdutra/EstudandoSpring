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
            clientes.save(new Cliente("Joao"));
            clientes.save(new Cliente("Joao Ricardo"));

            List<Cliente> listaCliente = clientes.encontrarPorNome("Joao");
            listaCliente.forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
