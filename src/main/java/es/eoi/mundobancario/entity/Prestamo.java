package es.eoi.mundobancario.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prestamos")
@Getter
@Setter

public class Prestamo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String descripcion;
	
	@Column
	private Date fecha;
	
	@Column
	private Double importe;
	
	@Column
	private Integer plazos;

	@OneToMany(mappedBy = "prestamo", cascade = CascadeType.ALL)
	List<Amortizacion> amortizaciones;

	@ManyToOne
	@JoinColumn(name="fk_numcuenta", nullable = false)
	private Cuenta cuenta;
}