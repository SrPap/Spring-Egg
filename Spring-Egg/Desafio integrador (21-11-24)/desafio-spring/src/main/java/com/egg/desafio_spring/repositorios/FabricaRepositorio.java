package com.egg.desafio_spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

import com.egg.desafio_spring.entidades.Fabrica;

@Repository
public interface FabricaRepositorio extends JpaRepository<Fabrica, UUID>{
    @Query("SELECT f FROM Fabrica f WHERE f.nombre = :nombre")
    public Fabrica buscarPorNombre(@Param("nombre")String nombre);

    @Query("SELECT COUNT(f) > 0 FROM Fabrica f WHERE f.nombre = :nombre")
    public Boolean existePorNombre(@Param("nombre") String nombre);
}
