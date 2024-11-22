package com.egg.desafio_spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.desafio_spring.entidades.Fabrica;
import com.egg.desafio_spring.repositorios.FabricaRepositorio;

@Service
public class FabricaServicio {
    
    @Autowired
    private FabricaRepositorio fabricaRepositorio;

    public Fabrica crearFabrica(String nombre) {
        Fabrica fabrica = new Fabrica();
        fabrica.setNombre(nombre);
        
        return fabricaRepositorio.save(fabrica);
    }

    @Transactional(readOnly = true)
    public List<Fabrica> listarFabricas() {
        return fabricaRepositorio.findAll();
    }
}