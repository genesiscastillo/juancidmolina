package cl.genesiscastillo.falabella.pedidos.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class MyDateDeserializer extends JsonDeserializer<LocalDate> {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	@Override
	public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws DateTimeParseException, IOException , JsonProcessingException {
		
		String date = jsonParser.getText();

		try {
			return LocalDate.parse(date, FORMATTER);
		} catch (DateTimeParseException e) {
			throw new IOException("Fecha en formato inválido", e);
		}
	}
}