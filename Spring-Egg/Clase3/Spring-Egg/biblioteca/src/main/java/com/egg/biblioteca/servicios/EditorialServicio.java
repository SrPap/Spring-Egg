package com.egg.biblioteca.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorios.EditorialRepositorio;

@Service
public class EditorialServicio {

    private void validar(String nombre) throws MiException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new MiException("El nombre de la editorial no puede ser nulo o estar vacío");
        }
    }
    
@Autowired
private EditorialRepositorio editorialRepositorio;

@Transactional
public void crearEditorial(String nombre)throws MiException{
    validar(nombre);

    Editorial editorial = new Editorial();
    editorial.setNombre(nombre);

    editorialRepositorio.save(editorial); 
    }

    @Transactional(readOnly = true)
    public List<Editorial> listarEditoriales() {

        List<Editorial> editoriales = new ArrayList();

        editoriales = editorialRepositorio.findAll();
        return editoriales;
    }


     public void modificarEditorial(UUID id, String nombre) throws MiException {
        validar(nombre);
        
        Editorial editorial = editorialRepositorio.findById(id)
                .orElseThrow(() -> new MiException("No se encontró la editorial con el ID proporcionado"));

        editorial.setNombre(nombre);
        editorialRepositorio.save(editorial);
    }

}
