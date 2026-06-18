package com.example.PaseoAPP.servicios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.PaseoAPP.dtos.ReservaDTO;
import com.example.PaseoAPP.mapeadores.IMapaReserva;
import com.example.PaseoAPP.modelos.Reserva;
import com.example.PaseoAPP.repositorios.IRepositorioReserva;
import com.example.PaseoAPP.validadores.IValidadorReserva;

@Service
public class ReservaServicioImpl implements IReservaServicio {

  private final IMapaReserva mapaReserva;
  private final IRepositorioReserva repositorioReserva;
  private final IValidadorReserva validadorReserva;

  public ReservaServicioImpl(IMapaReserva mapaReserva, IRepositorioReserva repositorioReserva, IValidadorReserva validadorReserva) {
    this.mapaReserva = mapaReserva;
    this.repositorioReserva = repositorioReserva;
    this.validadorReserva = validadorReserva;
  }

  @Override
  public ReservaDTO guardarReservaEnBD(Reserva datos) {
    this.validadorReserva.validarNuevaReserva(datos);

    Reserva reservaGuardada = repositorioReserva.save(datos);
    return mapaReserva.convertir_modelo_a_dto(reservaGuardada);
  }

  @Override
  public ReservaDTO modificarReservaEnBD(Reserva datos, UUID id) {
    Optional<Reserva> reservaBuscada = repositorioReserva.findById(id);
    if (reservaBuscada.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La reserva que quieres editar no existe en la base de datos");
    }

    Reserva reservaEncontrada = reservaBuscada.get();
    this.validadorReserva.validarDatosModificacion(datos);

    reservaEncontrada.setFecha(datos.getFecha());
    reservaEncontrada.setTiempo(datos.getTiempo());

    Reserva reservaModificada = repositorioReserva.save(reservaEncontrada);
    return mapaReserva.convertir_modelo_a_dto(reservaModificada);
  }

  @Override
  public List<ReservaDTO> buscarReservasEnBD() {
    List<Reserva> reservasEncontradas = repositorioReserva.findAll();
    return mapaReserva.convertir_lista_de_modelo_a_lista_de_dto(reservasEncontradas);
  }

  @Override
  public void eliminarReservaEnBD(UUID id) {
    Optional<Reserva> reservaBuscada = repositorioReserva.findById(id);
    if (reservaBuscada.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La reserva que quieres eliminar no existe en la base de datos");
    }
    repositorioReserva.deleteById(id);
  }
}
