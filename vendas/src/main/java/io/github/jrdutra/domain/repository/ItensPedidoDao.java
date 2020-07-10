package io.github.jrdutra.domain.repository;

import io.github.jrdutra.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensPedidoDao extends JpaRepository<ItemPedido, Integer> {
}
