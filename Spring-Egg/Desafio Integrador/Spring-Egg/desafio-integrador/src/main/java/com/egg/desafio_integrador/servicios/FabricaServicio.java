package com.egg.desafio_integrador.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egg.desafio_integrador.entidades.Fabrica;
import com.egg.desafio_integrador.repositorios.FabricaRepositorio;

@Service
public class FabricaServicio {
    
    @Autowired
    private FabricaRepositorio fabricaRepositorio;

    public Fabrica crearFabrica(String nombre) {
        Fabrica fabrica = new Fabrica();
        fabrica.setNombre(nombre);
        
        return fabricaRepositorio.save(fabrica);
    }
}