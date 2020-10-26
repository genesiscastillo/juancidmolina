package cl.genesiscastillo.falabella.pedidos;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "fb.gcp.pubsub.configuration")
public class SolicitudCompraArticuloProperties {
	
	private String topic;
	private String subscription;
	
}