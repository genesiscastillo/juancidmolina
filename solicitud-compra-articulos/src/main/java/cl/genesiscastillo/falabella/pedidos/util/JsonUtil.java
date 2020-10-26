package cl.genesiscastillo.falabella.pedidos.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.genesiscastillo.falabella.pedidos.bo.TopicBO;
import cl.genesiscastillo.falabella.pedidos.entity.Solicitud;

public class JsonUtil {
	
	
	public static Solicitud fromJson(String jsonStr) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Solicitud solicitud = mapper.readValue(jsonStr, Solicitud.class);
			return solicitud;
		} catch (IOException ioException) {
			return new Solicitud();
		}
	}

	public static String toJson(Solicitud solicitud) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonStr = mapper.writeValueAsString( solicitud );
			return jsonStr;
		} catch (IOException ioException) {
			return "";
		}
	}
	
	public static String toJson(TopicBO topicBO) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonStr = mapper.writeValueAsString( topicBO );
			return jsonStr;
		} catch (IOException ioException) {
			return "";
		}
	}

}
