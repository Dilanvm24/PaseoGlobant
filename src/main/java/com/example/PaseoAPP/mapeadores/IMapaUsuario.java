package com.example.PaseoAPP.mapeadores;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.PaseoAPP.dtos.ReservaDTO;
import com.example.PaseoAPP.dtos.UsuarioDTO;
import com.example.PaseoAPP.modelos.Reserva;
import com.example.PaseoAPP.modelos.Usuario;

@Mapper(componentModel = "spring")
public interface IMapaUsuario {
   IMapaUsuario INSTANCIA = Mappers.getMapper(IMapaUsuario.class);

    UsuarioDTO convertir_modelo_a_dto(Usuario usuario);
  
   List<UsuarioDTO> convertir_lista_de_modelo_a_lista_de_dto(List<Usuario>Lista);
}
