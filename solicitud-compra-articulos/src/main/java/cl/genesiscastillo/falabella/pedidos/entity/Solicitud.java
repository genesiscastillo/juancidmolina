package cl.genesiscastillo.falabella.pedidos.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Solicitud {

	@Id
	private String idSolicitud;
	
	private Long idArticulo;
	
	private Long cantidad;
	
	private Long precioUnitario;
	
	private LocalDate fecha;
	
}
