package com.egg.desafio_integrador.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egg.desafio_integrador.entidades.Articulo;

@Repository
public interface ArticuloRepositorio extends JpaRepository<Articulo, String>{
    
}
