package com.example.PaseoAPP.mapeadores;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.PaseoAPP.dtos.ReservaDTO;
import com.example.PaseoAPP.dtos.UsuarioDTO;
import com.example.PaseoAPP.modelos.Reserva;
import com.example.PaseoAPP.modelos.Usuario;

@Mapper(componentModel = "spring")
public interface IMapaReserva {
    IMapaReserva INSTANCIA = Mappers.getMapper(IMapaReserva.class);

    ReservaDTO convertir_modelo_a_dto(Reserva reserva);
  
   List<ReservaDTO> convertir_lista_de_modelo_a_lista_de_dto(List<Reserva>Lista);
}
