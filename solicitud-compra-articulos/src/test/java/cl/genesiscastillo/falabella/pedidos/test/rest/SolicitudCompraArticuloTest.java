package cl.genesiscastillo.falabella.pedidos.test.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import cl.genesiscastillo.falabella.pedidos.bo.ActualizarStockArticuloRequestBO;
import cl.genesiscastillo.falabella.pedidos.entity.Articulo;

public class SolicitudCompraArticuloTest extends AbstractTest {

    @Test
    public void testSaveAndGetArticulo() {
        Articulo articulo = new Articulo();
        articulo.setId(1l);
        articulo.setStock(10l);
        
        articuloRepository.save(articulo);
 
        List<Articulo> articulos = articuloRepository.findAll();
        assertEquals(1, articulos.size());
        Articulo savedArticulo = articulos.get(0);
        assertTrue(articulo.getId() > 0);
        assertEquals(articulo.getStock() , savedArticulo.getStock() );
    }

	@Test
	public void actualizarStockArticulo() throws Exception {
		String uri = "/actualizarStock";
		Articulo articulo = new Articulo(1l, 100l);
		ActualizarStockArticuloRequestBO actualizarStockArticuloRequestBO = new ActualizarStockArticuloRequestBO();
		actualizarStockArticuloRequestBO.setArticulo(articulo);

		String inputJson = super.objectToJson(actualizarStockArticuloRequestBO);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		Optional<Articulo> optional =	articuloRepository.findById(articulo.getId());

		assertTrue(optional.isPresent());
	}

	@Test
	public void obtenerArticulos() throws Exception {
		testSaveAndGetArticulo();
		
		String uri = "/articulos";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		 String content = mvcResult.getResponse().getContentAsString();
		 Articulo[] articulos = super.mapFromJson(content, Articulo[].class);
		 
		 assertTrue(articulos.length > 0);
	}
	
	@Test
	public void solicitudCompraArticuloNoExiste() throws Exception {
		Map<String,Object> map = new HashMap<>();
		map.put("id_articulo", 99);
		map.put("cantidad", 10);
		map.put("precio_unitario", 9990);
		map.put("fecha", "20-10-2020");
		
		String uri = "/solicitudCompra";
		String inputJson = super.mapToJson(map);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "{\"mensaje\":\"no existe el articulo ingresado : [ id = 99 ]\"}");
	}

	@Test
	public void solicitudCompraArticuloNoStock() throws Exception	{
		
		testSaveAndGetArticulo();
		
		Map<String,Object> map = new HashMap<>();
		map.put("id_articulo", 1);
		map.put("cantidad", 100);
		map.put("precio_unitario", 9990);
		map.put("fecha", "20-10-2020");
		
		String uri = "/solicitudCompra";
		String inputJson = super.mapToJson(map);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "{\"mensaje\":\"no existe stock para el articulo id = 1 ingresado : [ stock= 10 ] < [ cantidad = 100]\"}");
	}

	@Test
	public void solicitudCompraArticuloAceptada() throws Exception	{
		testSaveAndGetArticulo();
		
		Map<String,Object> map = new HashMap<>();
		map.put("id_articulo", 1);
		map.put("cantidad", 8);
		map.put("precio_unitario", 9990);
		map.put("fecha", "20-10-2020");
		
		String uri = "/solicitudCompra";
		String inputJson = super.mapToJson(map);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
		assertTrue(content.startsWith("{\"mensaje\":\"Su pedido se ha realizado exitosamente [id_solicitud ="));
	}

}
