package io.github.jrdutra;

import io.github.jrdutra.domain.entity.Cliente;
import io.github.jrdutra.domain.entity.Pedido;
import io.github.jrdutra.domain.repository.ClienteDao;
import io.github.jrdutra.domain.repository.PedidoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired ClienteDao daoCliente,
            @Autowired PedidoDao daoPedido
            ){
        return args -> {
            System.out.println("Salvando cliente");
            Cliente fulano = new Cliente("Fulano");
            daoCliente.save(fulano);

            Pedido p = new Pedido();
            p.setCliente(fulano);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(10));

            daoPedido.save(p);

//            Cliente cliente = daoCliente.findClienteFetchPedidos(fulano.getId());
//            System.out.println(cliente);
//            System.out.println(cliente.getPedidos());

            List<Pedido> listaPedidos = daoPedido.findByCliente(fulano);
            listaPedidos.forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
