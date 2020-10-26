package cl.genesiscastillo.falabella.pedidos.service;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.genesiscastillo.falabella.pedidos.PubsubOutboundGateway;
import cl.genesiscastillo.falabella.pedidos.bo.ActualizarStockArticuloRequestBO;
import cl.genesiscastillo.falabella.pedidos.bo.ActualizarStockArticuloResponseBO;
import cl.genesiscastillo.falabella.pedidos.bo.SolicitudCompraArticuloRequestBO;
import cl.genesiscastillo.falabella.pedidos.bo.SolicitudCompraArticuloResponseBO;
import cl.genesiscastillo.falabella.pedidos.bo.TopicBO;
import cl.genesiscastillo.falabella.pedidos.dao.ArticuloRepository;
import cl.genesiscastillo.falabella.pedidos.dao.SolicitudRepository;
import cl.genesiscastillo.falabella.pedidos.entity.Articulo;
import cl.genesiscastillo.falabella.pedidos.entity.Solicitud;
import cl.genesiscastillo.falabella.pedidos.util.JsonUtil;

@Service
public class ComprarArticuloService {
	
	private static final Logger LOGGER = Logger.getLogger(ComprarArticuloService.class);
	
	@Autowired
	PubsubOutboundGateway pubsubOutboundGateway;
	
	@Autowired
	ArticuloRepository articuloRepository;
	
	@Autowired
	SolicitudRepository solicitudRepository;
	
	public SolicitudCompraArticuloResponseBO solicitudCompra(SolicitudCompraArticuloRequestBO solicitudCompraArticuloRequestBO) {
		LOGGER.info(solicitudCompraArticuloRequestBO);
		String mensaje = ""; 
		Long idArticulo = solicitudCompraArticuloRequestBO.getIdArticulo();
		Optional<Articulo> optional = articuloRepository.findById(idArticulo);
		if(optional.isPresent()) {
			Articulo articulo = optional.get(); 
			if( solicitudCompraArticuloRequestBO.getCantidad() <= articulo.getStock() ) {
				Long stockActualizado = articulo.getStock() - solicitudCompraArticuloRequestBO.getCantidad();
				articulo.setStock(stockActualizado);
				articuloRepository.save(articulo);
				String idSolicitud = UUID.randomUUID().toString();
				Long cantidad = solicitudCompraArticuloRequestBO.getCantidad();
				Long precioUnitario = solicitudCompraArticuloRequestBO.getPrecioUnitario();
				LocalDate fecha = solicitudCompraArticuloRequestBO.getFecha();
				Solicitud solicitud = new Solicitud(idSolicitud, idArticulo, cantidad, precioUnitario, fecha);
				solicitudRepository.save(solicitud);
				
				String base64 = Base64.getEncoder().encodeToString(JsonUtil.toJson(solicitud).getBytes());
				
				TopicBO topicBO = new TopicBO();
				topicBO.setData(  base64 );
				
				LOGGER.info( topicBO );
				
				pubsubOutboundGateway.sendToPubSub( JsonUtil.toJson(topicBO) );
				
				mensaje = String.format("Su pedido se ha realizado exitosamente [id_solicitud = %s]",  solicitud.getIdSolicitud());
			}
			else {
				mensaje = String.format( "no existe stock para el articulo id = %d ingresado : [ stock= %d ] < [ cantidad = %d]" , articulo.getId() , articulo.getStock() , solicitudCompraArticuloRequestBO.getCantidad());
				LOGGER.warn( mensaje );
			}
		}
		else {
			mensaje = String.format( "no existe el articulo ingresado : [ id = %d ]" , solicitudCompraArticuloRequestBO.getIdArticulo());
			LOGGER.warn( mensaje );
		}
		return new SolicitudCompraArticuloResponseBO( mensaje );
	}
	
	public ActualizarStockArticuloResponseBO actualizarStock(ActualizarStockArticuloRequestBO actualizarStockArticuloRequestBO) {
		LOGGER.info( actualizarStockArticuloRequestBO );
		Articulo articulo = actualizarStockArticuloRequestBO.getArticulo();
		articuloRepository.save(articulo);
		return new ActualizarStockArticuloResponseBO(articulo);
	}
	
	public List<Articulo> obtenerArticulos()	{
		return articuloRepository.findAll();
	}
	
	public Optional<Articulo> obtenerArticulo(Long idArticulo){
		return articuloRepository.findById(idArticulo);
	}

	public List<Solicitud> obtenerSolicitudes(){
		return solicitudRepository.findAll();
	}
	
	public Optional<Solicitud> obtenerSolicitud(String uuid){
		return solicitudRepository.findById(uuid);
	}

}
