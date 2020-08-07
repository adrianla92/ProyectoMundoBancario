package es.eoi.mundobancario.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PrestamoDto {

	private String descripcion;
	
	private Date fecha;
	
	private Double importe;
	
	private Integer plazos;
}
