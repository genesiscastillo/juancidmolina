package cl.genesiscastillo.falabella.pedidos;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gcp.pubsub.core.PubSubOperations;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.cloud.gcp.pubsub.support.BasicAcknowledgeablePubsubMessage;
import org.springframework.cloud.gcp.pubsub.support.GcpPubSubHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class SolicitudCompraArticuloConfiguration {
	
	private static final Logger LOGGER = Logger.getLogger(SolicitudCompraArticuloConfiguration.class);
	
	@Autowired
	SolicitudCompraArticuloProperties properties;

	@Bean
	public MessageChannel pubsubInputChannel() {
		return new DirectChannel();
	}

	@Bean
	public PubSubInboundChannelAdapter messageChannelAdapter(
			@Qualifier("pubsubInputChannel") MessageChannel inputChannel, PubSubTemplate pubSubTemplate) {
		PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, properties.getSubscription());
		adapter.setOutputChannel(inputChannel);

		return adapter;
	}

	@Bean
	@ServiceActivator(inputChannel = "pubsubInputChannel")
	public MessageHandler messageReceiver() {
		return message -> {
			BasicAcknowledgeablePubsubMessage  basicAcknowledgeablePubsubMessage =  message.getHeaders().get(GcpPubSubHeaders.ORIGINAL_MESSAGE, BasicAcknowledgeablePubsubMessage.class);
			LOGGER.info("\n--------\nRECIBIENDO MENSAJE desde PubSub\n"+basicAcknowledgeablePubsubMessage+"\n--------\n");
			basicAcknowledgeablePubsubMessage.ack();
		};
	}

	@Bean
	@ServiceActivator(inputChannel = "pubsubOutputChannel")
	public MessageHandler messageSender(PubSubOperations pubSubTemplate) {
		return new PubSubMessageHandler(pubSubTemplate, properties.getTopic());
	}

}
