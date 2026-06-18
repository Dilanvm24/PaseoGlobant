package com.example.PaseoAPP.repositorios;

import com.example.PaseoAPP.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IRepositorioUsuario extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByCorreo(String correo);

}
