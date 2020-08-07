package es.eoi.mundobancario.service;

import es.eoi.mundobancario.entity.*;
import es.eoi.mundobancario.enums.TipoMovimientoEnum;
import es.eoi.mundobancario.repository.AmortizacionRepository;
import es.eoi.mundobancario.repository.MovimientoRepository;
import io.swagger.models.Model;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.repository.PrestamoRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


@Service
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    PrestamoRepository prestamoRepository;

    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    AmortizacionRepository amortizacionRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public PrestamoDto save(Prestamo prestamo, Integer numCuenta) throws Exception {
        Cuenta cuenta = new Cuenta();
        Boolean prestamoAlive = false;

        cuenta.setNumCuenta(numCuenta);
        List<Prestamo> prestamos = prestamoRepository.findAllByCuenta(cuenta);

        for (Prestamo prest : prestamos) {
            if (isAlive(prest)) {
                prestamoAlive = true;
            }
        }

        if (!prestamoAlive) {
            prestamo.setCuenta(cuenta);
            Prestamo prestamoSaved = prestamoRepository.save(prestamo);

            /*REALIZAMOS INGRESO DEL PRESTAMO*/
            realizarIngresoPrestamo(prestamo, numCuenta);

            /*INSERTAMOS LAS AMORTIZACIONES*/
            insertarAmortizaciones(prestamo);

            PrestamoDto prestamoDto = modelMapper.map(prestamoSaved, PrestamoDto.class);
            return prestamoDto;
        } else {
            throw new Exception("Ya existe un prestamo vivo.");
        }

    }

    @Override
    public Boolean isAlive(Prestamo prestamo) {
        Date fecha = new Date();
        for(Amortizacion amortizacion: prestamo.getAmortizaciones()){
            if(amortizacion.getFecha().after(fecha)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void realizarIngresoPrestamo(Prestamo prestamo, Integer numCuenta){
        Movimiento ingreso = new Movimiento();
        Cuenta cuenta = new Cuenta();
        Date date = new Date();
        TipoMovimiento tipoMovimiento = new TipoMovimiento();
        tipoMovimiento.setId(TipoMovimientoEnum.PRESTAMO.getValue());
        tipoMovimiento.setTipo("PRESTAMO");

        cuenta.setNumCuenta(numCuenta);

        /*REALIZAR INGRESO*/
        ingreso.setFecha(date);
        ingreso.setCuenta(cuenta);
        ingreso.setDescripcion("Prestamo");
        ingreso.setImporte(prestamo.getImporte());
        ingreso.setTipoMovimiento(tipoMovimiento);
        movimientoRepository.save(ingreso);
    }

    @Override
    public void insertarAmortizaciones(Prestamo prestamo){
        Date fechaAmortizacion = prestamo.getFecha();
        double cantidadAmortizacion = prestamo.getImporte() / prestamo.getPlazos();

        for (int i = 1; i <= prestamo.getPlazos(); i++) {
            /* SUMAR CONTADOR A LA FECHA */
            fechaAmortizacion.setMonth(fechaAmortizacion.getMonth() + i);

            Amortizacion amortizacion = new Amortizacion();
            amortizacion.setImporte(cantidadAmortizacion);
            amortizacion.setPrestamo(prestamo);
            amortizacion.setFecha(fechaAmortizacion);

            amortizacionRepository.save(amortizacion);
        }
    }
}
