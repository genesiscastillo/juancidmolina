package cl.genesiscastillo.falabella.pedidos.bo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TopicBO {

	private String data;
	private String topic = "solicitudes_de_compra";
	
}
