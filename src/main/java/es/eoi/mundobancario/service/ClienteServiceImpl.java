package es.eoi.mundobancario.service;

import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public ClienteDto save(Cliente cliente) throws Exception {
        if (clienteRepository.findByUsuario(cliente.getUsuario()) != null) {
            throw new Exception("El usuario existe");
        }
        Cliente clienteInserted = clienteRepository.save(cliente);
        ClienteDto clienteDto = modelMapper.map(clienteInserted, ClienteDto.class);

        return clienteDto;
    }

    @Override
    public ClienteDto findById(Integer id) {
        Cliente cliente = clienteRepository.findById(id).get();
        ClienteDto clienteDto = modelMapper.map(cliente, ClienteDto.class);

        return clienteDto;
    }

    @Override
    public List<CuentaDto> findCuentasClienteById(Integer id) {
        List<CuentaDto> dtos = new ArrayList<CuentaDto>();

        for (Cuenta cuenta : clienteRepository.findById(id).get().getCuentas()) {
            CuentaDto cuentaDto = modelMapper.map(cuenta, CuentaDto.class);
            dtos.add(cuentaDto);
        }

        return dtos;
    }

    @Override
    public List<ClienteDto> findAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDto> clientesDto = new ArrayList<>();

        for (Cliente cliente : clientes) {
            ClienteDto clienteDto = new ClienteDto();
            clienteDto = modelMapper.map(cliente, ClienteDto.class);
            clientesDto.add(clienteDto);
        }

        return clientesDto;
    }

    @Override
    public ClienteDto update(Cliente cliente, Integer id) {
        Cliente clienteToUpdate = clienteRepository.findById(id).get();
        clienteToUpdate.setEmail(cliente.getEmail());
        Cliente clienteUpdated = clienteRepository.save(clienteToUpdate);

        ClienteDto clienteDto = modelMapper.map(clienteUpdated, ClienteDto.class);

        return clienteDto;
    }

    @Override
    public ClienteDto login(Cliente cliente) {
        Cliente clienteFound = clienteRepository.findByUsuarioAndPass(cliente.getUsuario(), cliente.getPass());
        ClienteDto clienteDto = modelMapper.map(clienteFound, ClienteDto.class);
        return clienteDto;
    }
}


