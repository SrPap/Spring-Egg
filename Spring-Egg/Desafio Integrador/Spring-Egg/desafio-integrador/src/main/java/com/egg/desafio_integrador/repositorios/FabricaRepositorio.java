package com.egg.desafio_integrador.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egg.desafio_integrador.entidades.Fabrica;

@Repository
public interface FabricaRepositorio extends JpaRepository<Fabrica, String>{
    
}
