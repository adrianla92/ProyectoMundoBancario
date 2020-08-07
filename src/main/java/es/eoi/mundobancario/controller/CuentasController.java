package es.eoi.mundobancario.controller;

import es.eoi.mundobancario.dto.*;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.service.CuentaService;
import es.eoi.mundobancario.service.MovimientoService;
import es.eoi.mundobancario.service.PrestamoService;
import es.eoi.mundobancario.service.TipoMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CuentasController {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private PrestamoService prestamoService;

    @Autowired
    private TipoMovimientoService tipoMovimientoService;

    @GetMapping("/cuentas")
    public ResponseEntity<List<CuentaClienteDto>> findAll() {
        return ResponseEntity.ok(cuentaService.findAll());
    }

    @PostMapping("/cuentas")
    public ResponseEntity<CuentaDto> save(@RequestBody Cuenta cuenta) {
        if (cuenta == null) {
            return new ResponseEntity<CuentaDto>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(cuentaService.save(cuenta));
    }

    @GetMapping("/cuentas/{id}")
    public ResponseEntity<CuentaClienteDto> findById(@PathVariable Integer id) {
        if (id == null) {
            return new ResponseEntity<CuentaClienteDto>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(cuentaService.findById(id));
    }

    @PutMapping("/cuentas/{id}")
    public ResponseEntity<CuentaDto> update(@RequestBody Cuenta cuenta, @PathVariable Integer id) {
        if (id == null) {
            return new ResponseEntity<CuentaDto>(HttpStatus.BAD_REQUEST);
        }

        if (cuenta.getAlias() == null) {
            return new ResponseEntity<CuentaDto>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(cuentaService.update(cuenta, id));
    }

    @GetMapping("/cuentas/deudoras")
    public ResponseEntity<List<CuentaClienteDto>> findDeudoras() {
        return ResponseEntity.ok(cuentaService.findDeudoras());
    }

    @PostMapping("/cuentas/{id}/prestamos")
    public ResponseEntity<PrestamoDto> savePrestamo(@RequestBody Prestamo prestamo, @PathVariable Integer id) {

        if (id == null) {
            return new ResponseEntity<PrestamoDto>(HttpStatus.BAD_REQUEST);
        }

        if (prestamo == null) {
            return new ResponseEntity<PrestamoDto>(HttpStatus.BAD_REQUEST);
        }

        try {
            return ResponseEntity.ok(prestamoService.save(prestamo, id));
        } catch (Exception ex) {
            return new ResponseEntity<PrestamoDto>(HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/cuentas/{id}/ingresos")
    public ResponseEntity<MovimientoDto> saveIngreso(@RequestBody Movimiento movimiento, @PathVariable Integer id) {
        // TODO
        return null;
    }

    @PostMapping("/cuentas/{id}/pagos")
    public ResponseEntity<MovimientoDto> savePagos(@RequestBody Movimiento movimiento, @PathVariable Integer id) {
        // TODO
        return null;
    }

    @GetMapping("/cuentas/{id}/movimientos")
    public ResponseEntity<List<MovimientoDto>> getMovimientos(@PathVariable Integer id) {
        // TODO
        return null;
    }

    @GetMapping("/cuentas/{id}/prestamos")
    public ResponseEntity<List<PrestamoDto>> getPrestamos(@PathVariable Integer id) {
        // TODO
        return null;
    }

    @GetMapping("/cuentas/{id}/prestamosVivos")
    public ResponseEntity<List<PrestamoDto>> getPrestamosVivos(@PathVariable Integer id) {
        // TODO
        return null;
    }

    @GetMapping("/cuentas/{id}/prestamosAmortizados")
    public ResponseEntity<List<PrestamoDto>> getPrestamosAmortizados(@PathVariable Integer id) {
        // TODO
        return null;
    }

}
