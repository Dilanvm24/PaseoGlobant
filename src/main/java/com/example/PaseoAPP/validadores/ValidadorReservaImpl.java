package com.example.PaseoAPP.validadores;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.PaseoAPP.modelos.Reserva;
import com.example.PaseoAPP.repositorios.IRepositorioReserva;

@Component
public class ValidadorReservaImpl implements IValidadorReserva {

  private final IRepositorioReserva repositorioReserva;

  public ValidadorReservaImpl(IRepositorioReserva repositorioReserva) {
    this.repositorioReserva = repositorioReserva;
  }

  @Override
  public void validarNuevaReserva(Reserva datos) {
    validarFechaNoVacia(datos.getFecha());
    validarTiempoNoVacio(datos.getTiempo());
    validarFechaNoRepetida(datos.getFecha());
  }

  @Override
  public void validarDatosModificacion(Reserva datos) {
    validarFechaNoVacia(datos.getFecha());
    validarTiempoNoVacio(datos.getTiempo());
  }

  public void validarFechaNoRepetida(String fecha) {
    if (repositorioReserva.findByFecha(fecha).isPresent()) {
      throw new ResponseStatusException(
          HttpStatus.CONFLICT,
          "Ya existe una reserva para la fecha ingresada"
      );
    }
  }

  public void validarFechaNoVacia(String fecha) {
    if (fecha == null || fecha.isEmpty() || fecha.isBlank()) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "La fecha de la reserva no puede ser vacía"
      );
    }
  }

  public void validarTiempoNoVacio(String tiempo) {
    if (tiempo == null || tiempo.isEmpty() || tiempo.isBlank()) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "El tiempo de la reserva no puede ser vacío"
      );
    }
  }
}
