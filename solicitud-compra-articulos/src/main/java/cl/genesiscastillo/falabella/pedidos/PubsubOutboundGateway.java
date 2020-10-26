package cl.genesiscastillo.falabella.pedidos;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Service;

@Service
@MessagingGateway(defaultRequestChannel = "pubsubOutputChannel")
public interface PubsubOutboundGateway {
	void sendToPubSub(String text);
}
