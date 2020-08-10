package com.jrdutra.clientes.rest;

import com.jrdutra.clientes.model.entity.Cliente;
import com.jrdutra.clientes.model.entity.ServicoPrestado;
import com.jrdutra.clientes.model.repository.ClienteRepository;
import com.jrdutra.clientes.model.repository.ServicoPrestadoRepository;
import com.jrdutra.clientes.rest.dto.ServicoPrestadoDTO;
import com.jrdutra.clientes.util.BigDecimalConverter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/servicos-prestados")
public class ServicoPrestadoController {

    private final ClienteRepository clienteRepository;
    private final ServicoPrestadoRepository servicoPrestadoRepository;
    private final BigDecimalConverter bigDecimalConverter;

    public ServicoPrestadoController(ClienteRepository clienteRepository,
                                     ServicoPrestadoRepository servicoPrestadoRepository,
                                     BigDecimalConverter bigDecimalConverter){
        this.clienteRepository = clienteRepository;
        this.servicoPrestadoRepository = servicoPrestadoRepository;
        this.bigDecimalConverter = bigDecimalConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody ServicoPrestadoDTO dto){
        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Integer idCliente = dto.getIdCliente();
        Cliente cliente = clienteRepository
                                .findById(idCliente)
                                .orElseThrow(()->new ResponseStatusException(
                                                                HttpStatus.BAD_REQUEST,
                                                                "Cliente Inexistente"));
        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData(data);
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor( bigDecimalConverter.converter(dto.getPreco()));
        return servicoPrestadoRepository.save(servicoPrestado);
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes
    ){
        return servicoPrestadoRepository.findByNomeClienteAndMes("%"+nome+"%", mes);
    }

}
