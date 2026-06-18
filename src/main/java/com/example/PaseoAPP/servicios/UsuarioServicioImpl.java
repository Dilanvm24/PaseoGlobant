package com.example.PaseoAPP.servicios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.PaseoAPP.dtos.UsuarioDTO;
import com.example.PaseoAPP.mapeadores.IMapaUsuario;
import com.example.PaseoAPP.modelos.Usuario;
import com.example.PaseoAPP.repositorios.IRepositorioUsuario;
import com.example.PaseoAPP.validadores.IValidadorUsuario;

@Service
public class UsuarioServicioImpl implements IUsuarioServicio {

  //1.Llamar a los helpers o dependencias que necesite
  private final  IMapaUsuario mapaUsuario;

  private final IRepositorioUsuario repositorioUsuario;

  private final IValidadorUsuario validadorUsuario;
  
  

  public UsuarioServicioImpl(IMapaUsuario mapaUsuario, IRepositorioUsuario repositorioUsuario, IValidadorUsuario validadorUsuario) {
    this.mapaUsuario = mapaUsuario;
    this.repositorioUsuario = repositorioUsuario;
    this.validadorUsuario = validadorUsuario;
  }

  @Override
  public UsuarioDTO guardarUsuarioEnBD(Usuario datos) {
    // INCLUIR VALIDACIONES (proximamente) y validar 

    this.validadorUsuario.validarNuevoUsuario(datos);

    //guardar los datos y convertir la respuesta en un DTO
    Usuario usuarioGuardado = this.repositorioUsuario.save(datos);
    return this.mapaUsuario.convertir_modelo_a_dto(usuarioGuardado);

  }

  @Override
  public UsuarioDTO modificarUsuarioEnBD(Usuario datos, UUID id) {
   
    //Buscando que el usuario existan en BD
    Optional<Usuario> usuarioQueEstoyBuscando = this.repositorioUsuario.findById(id);
    if (usuarioQueEstoyBuscando.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
    }
    //encontré el usuario
    Usuario usuarioQueEncontre = usuarioQueEstoyBuscando.get();

    this.validadorUsuario.validarDatosModificacion(datos);

    //Aplicar los cambios 
    usuarioQueEncontre.setNombres(datos.getNombres());
    Usuario usuarioModificado = this.repositorioUsuario.save(usuarioQueEncontre);

    //convierto la respuesta a DTO
    return this.mapaUsuario.convertir_modelo_a_dto(usuarioModificado);
  }

  @Override
  public List<UsuarioDTO> buscarUsuariosEnBD() {
    List<Usuario> usuariosEncontrados = this.repositorioUsuario.findAll();
    return this.mapaUsuario.convertir_lista_de_modelo_a_lista_de_dto(usuariosEncontrados);
  }

  @Override
  public void eliminarUsuarioEnBD(UUID id) {
    Optional<Usuario> usuarioQueEstoyBuscando = this.repositorioUsuario.findById(id);
    if (usuarioQueEstoyBuscando.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
    }
    this.repositorioUsuario.deleteById(id);
  }

}
