package com.egg.desafio_spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

import com.egg.desafio_spring.entidades.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, UUID> {
    @Query("SELECT u FROM Usuario u WHERE u.username = :username")
    public Usuario buscarPorUsername(@Param("username")String username);

    @Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.username = :username")
    public Boolean existePorUsername(@Param("username") String username);
}
