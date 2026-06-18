package com.example.PaseoAPP.mapeadores;

import java.util.List;

import org.mapstruct.factory.Mappers;

import com.example.PaseoAPP.dtos.EspacioDTO;
import com.example.PaseoAPP.modelos.Espacio;
import com.example.PaseoAPP.modelos.Usuario;

public interface IMapaEspacio {
  IMapaEspacio INSTANCIA = Mappers.getMapper(IMapaEspacio.class);

  EspacioDTO convertir_modelo_a_dto(Espacio espacio);

  List<EspacioDTO> convertir_lista_a_modelo_a_lista_de_dto(List<Espacio>Lista);
}
