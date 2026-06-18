package com.example.PaseoAPP.repositorios;

import com.example.PaseoAPP.modelos.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IRepositorioReserva extends JpaRepository<Reserva, UUID> {

    Optional<Reserva> findByFecha(String fecha);

}
