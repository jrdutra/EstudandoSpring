package io.github.jrdutra.domain.repository;

import io.github.jrdutra.domain.entity.Cliente;
import io.github.jrdutra.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoDao extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

    @Query(" select p from Pedido p left join fetch p.itensPedido where p.id = :id ")
    Optional<Pedido> findByIdFetchItensPedido(@Param("id") Integer id);

}
