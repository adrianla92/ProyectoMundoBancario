package es.eoi.mundobancario.entity;

import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipos_movimiento")
@Getter
@Setter

public class TipoMovimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String Tipo;


	@OneToMany(mappedBy = "tipoMovimiento", cascade = CascadeType.ALL)
	List<Movimiento> movimientos;
}