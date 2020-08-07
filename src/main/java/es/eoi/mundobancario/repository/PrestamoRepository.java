package es.eoi.mundobancario.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.*;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository <Prestamo, Integer>{
    List<Prestamo> findAllByCuenta(Cuenta cuenta);
}
