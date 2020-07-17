package io.github.jrdutra.exeption;

public class PedidoNaoEncontradoExceptio extends RuntimeException {

    public PedidoNaoEncontradoExceptio(){
        super("Pedido não encontrado");
    }

}
