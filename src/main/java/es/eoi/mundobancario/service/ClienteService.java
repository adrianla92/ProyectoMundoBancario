package es.eoi.mundobancario.service;

import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.entity.Cliente;
import java.util.List;

public interface ClienteService {
	ClienteDto save(Cliente cliente) throws Exception;
	ClienteDto findById(Integer id);
	List<CuentaDto> findCuentasClienteById(Integer id);
	List<ClienteDto> findAll();
	ClienteDto update(Cliente cliente, Integer id);
	ClienteDto login(Cliente cliente);
}
