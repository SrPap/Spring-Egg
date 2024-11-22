package com.egg.desafio_spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

import com.egg.desafio_spring.entidades.Articulo;

@Repository
public interface ArticuloRepositorio extends JpaRepository<Articulo, UUID>{
    @Query("SELECT a FROM Articulo a WHERE a.nombre = :nombre")
    public Articulo buscarPorNombre(@Param("nombre")String nombre);

    @Query("SELECT COUNT(a) > 0 FROM Articulo a WHERE a.nombre = :nombre")
    public Boolean existePorNombre(@Param("nombre") String nombre);
}
