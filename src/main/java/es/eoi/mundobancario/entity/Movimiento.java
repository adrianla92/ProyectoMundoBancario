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
@Table(name = "movimientos")

public class Movimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String descripcion;
	@Column
	private Date fecha;
	@Column
	private Double importe;


	@ManyToOne
	@JoinColumn(name="fk_id_tipos_movimientos", nullable = false)
	private TipoMovimiento tipoMovimiento;

	@ManyToOne
	@JoinColumn(name="fk_num_cuenta", nullable = false, referencedColumnName = "numCuenta")
	private Cuenta cuenta;

}