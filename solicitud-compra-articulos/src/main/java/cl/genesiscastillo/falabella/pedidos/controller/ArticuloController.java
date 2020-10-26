package cl.genesiscastillo.falabella.pedidos.controller;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.genesiscastillo.falabella.pedidos.bo.ActualizarStockArticuloRequestBO;
import cl.genesiscastillo.falabella.pedidos.bo.ActualizarStockArticuloResponseBO;
import cl.genesiscastillo.falabella.pedidos.bo.SolicitudCompraArticuloRequestBO;
import cl.genesiscastillo.falabella.pedidos.bo.SolicitudCompraArticuloResponseBO;
import cl.genesiscastillo.falabella.pedidos.entity.Articulo;
import cl.genesiscastillo.falabella.pedidos.entity.Solicitud;
import cl.genesiscastillo.falabella.pedidos.service.ComprarArticuloService;

@RestController
public class ArticuloController {
	
	private static final Logger LOGGER = Logger.getLogger(ArticuloController.class);

	@Autowired
	ComprarArticuloService comprarArticuloService;
	
	@RequestMapping(value="/solicitudCompra", method=RequestMethod.POST)
	public ResponseEntity<SolicitudCompraArticuloResponseBO> solicitudCompraArticulo(@RequestBody SolicitudCompraArticuloRequestBO solicitudCompraArticuloRequestBO ){
		LOGGER.info(solicitudCompraArticuloRequestBO);
		SolicitudCompraArticuloResponseBO solicitudCompraArticuloResponseBO = comprarArticuloService.solicitudCompra(solicitudCompraArticuloRequestBO);
		return ResponseEntity.ok( solicitudCompraArticuloResponseBO );
	}

	@RequestMapping(value="/actualizarStock", method=RequestMethod.POST)
	public ResponseEntity<ActualizarStockArticuloResponseBO> actualizarStock(@RequestBody ActualizarStockArticuloRequestBO actualizarStockArticuloRequestBO ){
		LOGGER.info(actualizarStockArticuloRequestBO);
		ActualizarStockArticuloResponseBO actualizarStockArticuloResponseBO = comprarArticuloService.actualizarStock(actualizarStockArticuloRequestBO);
		return ResponseEntity.ok(actualizarStockArticuloResponseBO);
	}
	
	@RequestMapping(value="/articulos", method=RequestMethod.GET)
	public ResponseEntity<List<Articulo>> getArticulos(){
		return ResponseEntity.ok(comprarArticuloService.obtenerArticulos());
	}

	@RequestMapping(value="/articulo/{id}", method = RequestMethod.GET)
	public ResponseEntity<Articulo> getArticulo(@PathVariable("id") Long idArticulo )	{
		LOGGER.info(String.format("getArticulo->[ id = %s ]" , idArticulo));
		Optional<Articulo> optional = comprarArticuloService.obtenerArticulo(idArticulo);
		return optional.isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.notFound().build();
	}
	
	@RequestMapping(value="/solicitudes", method=RequestMethod.GET)
	public ResponseEntity<List<Solicitud>> getSolicitudes(){
		return ResponseEntity.ok(comprarArticuloService.obtenerSolicitudes());
	}

	@RequestMapping(value="/solicitud/{uuid}", method = RequestMethod.GET)
	public ResponseEntity<Solicitud> getSolicitud(@PathVariable("uuid") String uuid )	{
		LOGGER.info(String.format("getSolicitud->[ uuid = %s ]" , uuid));
		Optional<Solicitud> optional = comprarArticuloService.obtenerSolicitud( uuid );
		return optional.isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.notFound().build();
	}
	
}
