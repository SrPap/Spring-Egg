package com.egg.biblioteca.servicios;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.biblioteca.entidades.Usuario;
import com.egg.biblioteca.enumeraciones.Rol;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio implements UserDetailsService{
    
    private void validar(String nombre, String email, String password, String password2) throws MiException {
        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("El nombre no puede ser nulo o estar vacío");
        }
        if (email == null || email.isEmpty()) {
            throw new MiException("El email no puede ser nulo o estar vacío");
        }
        if (password == null || password.isEmpty() || password.length() <= 5) {
            throw new MiException("La contraseña no puede estar vacía, y debe tener más de 5 dígitos");
        }
        if (!password.equals(password2)) {
            throw new MiException("Las contraseñas ingresadas deben ser iguales");
        }
    }
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

     @Override
     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
         if (usuario != null) {
             List<GrantedAuthority> permisos = new ArrayList<>();
             GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
             permisos.add(p);
             return new User(usuario.getEmail(), usuario.getPassword(), permisos);
         } else {
             throw new UsernameNotFoundException("Usuario no encontrado con el email: " + email);
         }
     }

    @Transactional
    public void registrar(String nombre, String email, String password) throws MiException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new MiException("El nombre no puede estar vacío");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new MiException("El email no puede estar vacío");
        }
        if (password == null || password.length() < 6) {
            throw new MiException("La contraseña debe tener al menos 6 caracteres");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setRol(Rol.USER);

        usuarioRepositorio.save(usuario);
    }

    public void registrar(String nombre, String email, String password, String password2) throws MiException {
        validar(nombre, email, password, password2);
    
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setRol(Rol.USER);
    
        usuarioRepositorio.save(usuario);
    }
}
