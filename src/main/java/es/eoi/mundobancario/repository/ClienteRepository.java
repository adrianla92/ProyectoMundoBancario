package es.eoi.mundobancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.*;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente,Integer>{
    Cliente findByUsuario(String usuario);
    Cliente findByUsuarioAndPass(String usuario, String pass);
}
