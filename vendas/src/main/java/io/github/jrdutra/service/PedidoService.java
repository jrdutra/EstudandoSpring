package io.github.jrdutra.service;


import io.github.jrdutra.domain.entity.Pedido;
import io.github.jrdutra.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

}
