package es.eoi.mundobancario.controller;

import java.util.List;

import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.service.ClienteService;

@RestController
public class ClientesController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/clientes")
	public ResponseEntity<List<ClienteDto>> findAll() {
		return ResponseEntity.ok(clienteService.findAll());
	}

	@PostMapping("/clientes")
	public ResponseEntity<ClienteDto> save(@RequestBody Cliente cliente){
		if(cliente == null){
			return new ResponseEntity<ClienteDto>(HttpStatus.BAD_REQUEST);
		}

		try{
			ClienteDto clienteDto = clienteService.save(cliente);
			return ResponseEntity.ok(clienteDto);
		}catch (Exception ex){
			return new ResponseEntity<ClienteDto>(HttpStatus.CONFLICT);
		}
	}

	@PutMapping("/clientes/{id}")
	public ResponseEntity<ClienteDto> update(@RequestBody Cliente cliente, @PathVariable Integer id){
		if(cliente == null){
			return new ResponseEntity<ClienteDto>(HttpStatus.BAD_REQUEST);
		}

		if(cliente.getEmail() == null){
			return new ResponseEntity<ClienteDto>(HttpStatus.BAD_REQUEST);
		}

		if(id == null){
			return new ResponseEntity<ClienteDto>(HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(clienteService.update(cliente, id));
	}

	@GetMapping("/clientes/{id}")
	public ResponseEntity<ClienteDto> findById(@PathVariable Integer id){
		if(id == null){
			return new ResponseEntity<ClienteDto>(HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(clienteService.findById(id));
	}

	@GetMapping("/clientes/{id}/cuentas")
	public ResponseEntity<List<CuentaDto>> findCuentasClienteById(@PathVariable Integer id){
		if(id == null){
			return new ResponseEntity<List<CuentaDto>>(HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(clienteService.findCuentasClienteById(id));
	}

	@PostMapping("/clientes/login")
	public ResponseEntity<ClienteDto> login(@RequestBody Cliente cliente){
		if(cliente == null){
			return new ResponseEntity<ClienteDto>(HttpStatus.BAD_REQUEST);
		}

		return  ResponseEntity.ok(clienteService.login(cliente));
	}
}
