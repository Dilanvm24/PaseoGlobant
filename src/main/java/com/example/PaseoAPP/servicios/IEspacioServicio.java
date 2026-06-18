package com.example.PaseoAPP.servicios;

import java.util.List;
import java.util.UUID;

import com.example.PaseoAPP.dtos.EspacioDTO;
import com.example.PaseoAPP.modelos.Espacio;

public interface IEspacioServicio {

  EspacioDTO guardarEspacioEnBD(Espacio datos);

  EspacioDTO modificarEspacioEnBD(Espacio datos, UUID id);

  List<EspacioDTO> buscarEspaciosEnBD();

  void eliminarEspacioEnBD(UUID id);
}
