package com.egg.desafio_spring.entidades;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idArticulo;

    private static final AtomicInteger atomicInteger = new AtomicInteger(0);
    private Integer nroArticulo;

    @NotNull(message = "El nombre del artículo no puede ser nulo.")
    @Column(nullable = false)
    private String nombreArticulo;

    private String descripcionArticulo;

    @ManyToOne
    @JoinColumn(name = "idFabrica", nullable = false)
    private Fabrica fabrica;

    @PrePersist
    protected void onPrePersist() {
        this.nroArticulo = atomicInteger.getAndIncrement();
    }

    @Override
    public String toString() {
        return "Articulo [idArticulo=" + idArticulo + ", nombreArticulo=" + nombreArticulo + ", nroArticulo=" + nroArticulo + ", descripcionArticulo=" + descripcionArticulo
                + ", Fabrica=" + fabrica + "]";
    }
}
