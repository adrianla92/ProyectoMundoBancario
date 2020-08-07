package es.eoi.mundobancario.service;

import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Prestamo;

public interface PrestamoService {
	PrestamoDto save(Prestamo prestamo, Integer id) throws Exception;
	Boolean isAlive (Prestamo prestamo);
	void realizarIngresoPrestamo(Prestamo prestamo, Integer numCuenta);
	void insertarAmortizaciones(Prestamo prestamo);
}
