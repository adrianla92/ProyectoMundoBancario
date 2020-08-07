package es.eoi.mundobancario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CuentaClienteDto {

    private Integer numCuenta;

    private String alias;

    private Double saldo;

    private ClienteDto clienteDto;


}
