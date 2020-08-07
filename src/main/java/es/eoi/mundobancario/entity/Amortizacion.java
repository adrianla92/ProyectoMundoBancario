package es.eoi.mundobancario.entity;

import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "amortizaciones")

public class Amortizacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private Date fecha;

	@Column
	private Double importe;

	@ManyToOne
	@JoinColumn(name="fk_id_prestamos", nullable = false)
	private Prestamo prestamo;

}
