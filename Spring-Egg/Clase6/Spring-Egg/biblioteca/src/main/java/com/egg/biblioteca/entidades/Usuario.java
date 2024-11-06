package com.egg.biblioteca.entidades;

import java.util.UUID;

import com.egg.biblioteca.enumeraciones.Rol;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nombre;
    private String email;
    private String password;

@Enumerated(EnumType.STRING)
    private Rol rol;

    public Usuario() {}

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Rol getRol() {
        return rol;
    }
}