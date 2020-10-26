package cl.genesiscastillo.falabella.pedidos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.genesiscastillo.falabella.pedidos.entity.Articulo;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long>{

}
