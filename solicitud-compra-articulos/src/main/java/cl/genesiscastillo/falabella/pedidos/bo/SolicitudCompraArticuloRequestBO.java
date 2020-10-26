package cl.genesiscastillo.falabella.pedidos.bo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import cl.genesiscastillo.falabella.pedidos.util.MyDateDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SolicitudCompraArticuloRequestBO {

	@JsonProperty("id_articulo")
	private Long idArticulo;
	
	@JsonProperty("cantidad")
	private Long cantidad;
	
	@JsonProperty("precio_unitario")
	private Long precioUnitario;
	
	@JsonProperty("fecha")
	@JsonDeserialize(using = MyDateDeserializer.class)	
	private LocalDate fecha;
	
}
