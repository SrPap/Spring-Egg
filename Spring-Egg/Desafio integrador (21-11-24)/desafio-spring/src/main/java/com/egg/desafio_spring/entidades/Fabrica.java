package com.egg.desafio_spring.entidades;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fabrica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idFabrica;

    @NotNull(message = "El nombre de la fabrica no puede estar vacio.")
    @Column(nullable = false)
    private String nombreFabrica;

    @Override
    public String toString() {
        return "Fabrica [id=" + idFabrica + ", nombreFabrica=" + nombreFabrica + "]";
    }

}