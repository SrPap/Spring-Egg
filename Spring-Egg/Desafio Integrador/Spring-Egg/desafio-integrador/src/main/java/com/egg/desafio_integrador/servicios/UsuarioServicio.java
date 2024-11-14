package com.egg.desafio_integrador.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egg.desafio_integrador.entidades.Usuario;
import com.egg.desafio_integrador.enums.Rol;
import com.egg.desafio_integrador.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Usuario crearUsuario(String username, String nombre, String apellido, Rol rol) {
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setRol(rol);
        
        return usuarioRepositorio.save(usuario);
    }
    
    public Optional<Usuario> buscarPorUsername(String username) {
        return Optional.ofNullable(usuarioRepositorio.buscarPorUsername(username));
    }
    
}