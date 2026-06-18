package com.example.PaseoAPP.validadores;

import com.example.PaseoAPP.modelos.Reserva;

public interface IValidadorReserva {
  void validarNuevaReserva(Reserva datos);
  void validarDatosModificacion(Reserva datos);
}
