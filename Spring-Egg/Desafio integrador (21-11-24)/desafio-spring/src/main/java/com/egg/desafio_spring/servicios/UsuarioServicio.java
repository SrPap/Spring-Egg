package com.egg.desafio_spring.servicios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.desafio_spring.entidades.Usuario;
import com.egg.desafio_spring.enums.Rol;
import com.egg.desafio_spring.excepciones.MiException;
import com.egg.desafio_spring.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public Usuario registrarUsuario(String username, String nombre, String apellido, String contrasena) throws MiException {
        validarDatos(username, nombre, apellido, contrasena);

        if (usuarioRepositorio.existePorUsername(username)) {
            throw new MiException("El usuario ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setContrasena(new BCryptPasswordEncoder().encode(contrasena));
        usuario.setRol(Rol.USER);

        return usuarioRepositorio.save(usuario);
    }

    @Transactional
    public Usuario actualizarUsuario(UUID idUsuario, String nombre, String apellido, String contrasena) throws MiException {
        Usuario usuario = obtenerUsuarioPorId(idUsuario);

        validarDatos(usuario.getUsername(), nombre, apellido, contrasena);

        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setContrasena(new BCryptPasswordEncoder().encode(contrasena));

        return usuarioRepositorio.save(usuario);
    }

    @Transactional
    public void eliminarUsuario(UUID idUsuario) throws MiException {
        if (!usuarioRepositorio.existsById(idUsuario)) {
            throw new MiException("El usuario no existe");
        }
        usuarioRepositorio.deleteById(idUsuario);
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario obtenerUsuarioPorId(UUID idUsuario) throws MiException {
        return usuarioRepositorio.findById(idUsuario)
                .orElseThrow(() -> new MiException("Usuario no encontrado"));
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorUsername(String username) {
        return Optional.ofNullable(usuarioRepositorio.buscarPorUsername(username));
    }

    private void validarDatos(String username, String nombre, String apellido, String contrasena) throws MiException {
        if (username == null || username.isEmpty()) {
            throw new MiException("El usuario no puede estar vacío");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("El nombre no puede estar vacío");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new MiException("El apellido no puede estar vacío");
        }
        if (contrasena == null || contrasena.length() < 6) {
            throw new MiException("La contraseña debe tener al menos 6 caracteres");
        }
    }
}