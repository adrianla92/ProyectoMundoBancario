package es.eoi.mundobancario.service;

import es.eoi.mundobancario.dto.CuentaClienteDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.entity.Cuenta;

import java.util.List;

public interface CuentaService {
    List<CuentaClienteDto> findAll();
    List<CuentaClienteDto> findDeudoras();
    CuentaClienteDto findById(Integer id);
    CuentaDto save(Cuenta cuenta);
    CuentaDto update(Cuenta cuenta, Integer id);

}
