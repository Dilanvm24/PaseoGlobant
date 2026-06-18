package com.example.PaseoAPP.servicios;

import java.util.List;
import java.util.UUID;

import com.example.PaseoAPP.dtos.UsuarioDTO;
import com.example.PaseoAPP.modelos.Usuario;

public interface IUsuarioServicio {

  //contrato que describe que se puede hacer en el servicio 
  UsuarioDTO guardarUsuarioEnBD(Usuario datos);
  UsuarioDTO modificarUsuarioEnBD(Usuario datos, UUID id);
  List<UsuarioDTO> buscarUsuariosEnBD();

  void eliminarUsuarioEnBD(UUID id);
}
