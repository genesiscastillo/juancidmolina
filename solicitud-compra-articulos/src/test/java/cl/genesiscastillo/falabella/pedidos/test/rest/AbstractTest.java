package cl.genesiscastillo.falabella.pedidos.test.rest;

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.genesiscastillo.falabella.pedidos.SolicitudCompraArticulosApplication;
import cl.genesiscastillo.falabella.pedidos.dao.ArticuloRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SolicitudCompraArticulosApplication.class)
@WebAppConfiguration
public abstract class AbstractTest {
	
	protected MockMvc mvc;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
    @Autowired
    ArticuloRepository articuloRepository; 

	@BeforeEach
	protected void setUp() throws Exception	{
		this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	protected String objectToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
	
	protected String mapToJson(Map<String,Object> map) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.writerWithDefaultPrettyPrinter()
		  .writeValueAsString(map);
		return jsonResult;
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
}