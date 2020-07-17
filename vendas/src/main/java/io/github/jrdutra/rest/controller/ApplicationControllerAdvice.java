package io.github.jrdutra.rest.controller;

import io.github.jrdutra.exeption.RegraNegocioException;
import io.github.jrdutra.rest.ApiErrors;
import io.github.jrdutra.rest.dto.InformacaoItemPedidoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }


}
