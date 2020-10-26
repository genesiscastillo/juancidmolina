package cl.genesiscastillo.falabella.pedidos.bo;

import cl.genesiscastillo.falabella.pedidos.entity.Articulo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ActualizarStockArticuloRequestBO {
	
	private Articulo articulo;
	

}
