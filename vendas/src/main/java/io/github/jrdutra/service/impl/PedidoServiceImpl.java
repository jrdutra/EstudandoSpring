package io.github.jrdutra.service.impl;

import io.github.jrdutra.domain.entity.Cliente;
import io.github.jrdutra.domain.entity.ItemPedido;
import io.github.jrdutra.domain.entity.Pedido;
import io.github.jrdutra.domain.entity.Produto;
import io.github.jrdutra.domain.repository.ClienteDao;
import io.github.jrdutra.domain.repository.ItensPedidoDao;
import io.github.jrdutra.domain.repository.PedidoDao;
import io.github.jrdutra.domain.repository.ProdutoDao;
import io.github.jrdutra.exeption.RegraNegocioException;
import io.github.jrdutra.rest.dto.ItemPedidoDTO;
import io.github.jrdutra.rest.dto.PedidoDTO;
import io.github.jrdutra.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoDao daoPedido;
    private final ClienteDao daoCliente;
    private final ProdutoDao daoProduto;
    private final ItensPedidoDao daoItensPedido;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());

        Integer idCliente = dto.getCliente();

        Cliente cliente = daoCliente.findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente não encontrado"));

        pedido.setCliente(cliente);

        List<ItemPedido> itemPedidos = converterItens(pedido, dto.getItensPedido());
        daoPedido.save(pedido);
        daoItensPedido.saveAll(itemPedidos);
        pedido.setItensPedido(itemPedidos);
        return pedido;
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens){
        if(itens.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens");
        }
        return itens
                .stream()
                .map(
                        dto->{
                            Integer idProduto = dto.getProduto();
                            Produto produto = daoProduto
                                    .findById(idProduto)
                                    .orElseThrow(()->new RegraNegocioException("Código de produto inválido: "+idProduto));
                            ItemPedido itemPedido = new ItemPedido();
                            itemPedido.setQuantidade(dto.getQuantidade());
                            itemPedido.setPedido(pedido);
                            itemPedido.setProduto(produto);
                            return itemPedido;
                        }
                ).collect(Collectors.toList());
    }

}
