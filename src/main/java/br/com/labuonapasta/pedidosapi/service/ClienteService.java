package br.com.labuonapasta.pedidosapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.labuonapasta.pedidosapi.model.Cliente;
import br.com.labuonapasta.pedidosapi.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscarPorCodigo(Long codigo) {
        Optional<Cliente> cliente = clienteRepository.findById(codigo);

        if (!cliente.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return cliente.get();
    }
}
