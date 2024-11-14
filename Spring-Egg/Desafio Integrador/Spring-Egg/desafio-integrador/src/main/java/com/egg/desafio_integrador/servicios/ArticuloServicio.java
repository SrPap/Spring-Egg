package com.egg.desafio_integrador.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egg.desafio_integrador.entidades.Articulo;
import com.egg.desafio_integrador.entidades.Fabrica;
import com.egg.desafio_integrador.repositorios.ArticuloRepositorio;

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
    
}