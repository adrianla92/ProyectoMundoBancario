package es.eoi.mundobancario.entity;

import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cuentas")
@Getter
@Setter
public class Cuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numCuenta;
	
	@Column
	private String alias;
	
	@Column
	private Double saldo;

	@OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
	List<Movimiento> movimientos;

	@OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
	List<Prestamo> prestamos;
	
	@ManyToOne
	@JoinColumn(name="fk_id_clientes", nullable = false)
	private Cliente cliente;
}