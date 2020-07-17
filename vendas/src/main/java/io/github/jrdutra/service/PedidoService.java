package io.github.jrdutra.service;


import io.github.jrdutra.domain.entity.Pedido;
import io.github.jrdutra.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

}
