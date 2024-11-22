package com.egg.desafio_spring.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egg.desafio_spring.entidades.Articulo;
import com.egg.desafio_spring.entidades.Fabrica;
import com.egg.desafio_spring.repositorios.ArticuloRepositorio;

@Service
public class ArticuloServicio {
    
    @Autowired
    private ArticuloRepositorio articuloRepositorio;

    public Articulo crearArticulo(int stock, String nombre, String descripcion, Fabrica fabrica) {
        Articulo articulo = new Articulo();
        articulo.setNroArticulo(stock);
        articulo.setNombre(nombre);
        articulo.setDescripcion(descripcion);
        articulo.setFabrica(fabrica);
        
        return articuloRepositorio.save(articulo);
    }

    public Articulo crearArticulo(String nombre, String descripcion, Fabrica fabrica) {
        Articulo articulo = new Articulo();
        articulo.setNombre(nombre);
        articulo.setDescripcion(descripcion);
        articulo.setFabrica(fabrica);
        
        return articuloRepositorio.save(articulo);
    }
    
}