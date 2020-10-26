package cl.genesiscastillo.falabella.pedidos.test.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import cl.genesiscastillo.falabella.pedidos.dao.ArticuloRepository;
import cl.genesiscastillo.falabella.pedidos.entity.Articulo;

//@RunWith(SpringRunner.class)
//@DataJpaTest
public class JpaApplicationTest {
 
//    @Autowired
//    ArticuloRepository articuloRepository; 
// 
//    @Test
//    public void testSaveAndGetEntity() {
// 
//        Articulo articulo = new Articulo();
//        articulo.setId(1l);
//        articulo.setStock(100l);
//        
//        // save entity
//        articuloRepository.save(articulo);
// 
//        // assert entity
//        List<Articulo> articulos = articuloRepository.findAll();
//        assertEquals(1, articulos.size());
//        Articulo savedArticulo = articulos.get(0);
//        assertTrue(articulo.getId() > 0);
//        assertEquals(articulo.getStock() , savedArticulo.getStock() );
//       
//    }
}