package com.egg.desafio_integrador.entidades;

import java.util.concurrent.atomic.AtomicInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private static final AtomicInteger contador = new AtomicInteger(0);
    private Integer nroArticulo = contador.incrementAndGet();

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "La descripcion es obligatorio")
    @Column(nullable = false)
    private String descripcion;

    @ManyToOne
    private Fabrica fabrica;
}