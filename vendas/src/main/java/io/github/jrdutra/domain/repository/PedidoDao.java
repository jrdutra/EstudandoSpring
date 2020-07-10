package io.github.jrdutra.domain.repository;

import io.github.jrdutra.domain.entity.Cliente;
import io.github.jrdutra.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoDao extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

}
