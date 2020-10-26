package cl.genesiscastillo.falabella.pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties({
    SolicitudCompraArticuloProperties.class
})
public class SolicitudCompraArticulosApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SolicitudCompraArticulosApplication.class, args);
	}

}
