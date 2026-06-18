package com.example.PaseoAPP.servicios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.PaseoAPP.dtos.EspacioDTO;
import com.example.PaseoAPP.mapeadores.IMapaEspacio;
import com.example.PaseoAPP.modelos.Espacio;
import com.example.PaseoAPP.repositorios.IRepositorioEspacio;
import com.example.PaseoAPP.validadores.IValidadorEspacio;

@Service
public class EspacioServicioImpl implements IEspacioServicio {

  private final IMapaEspacio mapaEspacio;
  private final IRepositorioEspacio repositorioEspacio;
  private final IValidadorEspacio validadorEspacio;

  public EspacioServicioImpl(IMapaEspacio mapaEspacio, IRepositorioEspacio repositorioEspacio, IValidadorEspacio validadorEspacio) {
    this.mapaEspacio = mapaEspacio;
    this.repositorioEspacio = repositorioEspacio;
    this.validadorEspacio = validadorEspacio;
  }

  @Override
  public EspacioDTO guardarEspacioEnBD(Espacio datos) {
    this.validadorEspacio.validarNuevoEspacio(datos);

    Espacio espacioGuardado = repositorioEspacio.save(datos);
    return mapaEspacio.convertir_modelo_a_dto(espacioGuardado);
  }

  @Override
  public EspacioDTO modificarEspacioEnBD(Espacio datos, UUID id) {
    Optional<Espacio> espacioBuscado = repositorioEspacio.findById(id);
    if (espacioBuscado.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El espacio no existe en la base de datos");
    }

    Espacio espacioEncontrado = espacioBuscado.get();
    this.validadorEspacio.validarDatosModificacion(datos);

    espacioEncontrado.setNombre(datos.getNombre());
    espacioEncontrado.setDescripcion(datos.getDescripcion());
    espacioEncontrado.setFoto(datos.getFoto());
    espacioEncontrado.setAforo(datos.getAforo());

    Espacio espacioModificado = repositorioEspacio.save(espacioEncontrado);
    return mapaEspacio.convertir_modelo_a_dto(espacioModificado);
  }

  @Override
  public List<EspacioDTO> buscarEspaciosEnBD() {
    List<Espacio> espaciosEncontrados = repositorioEspacio.findAll();
    return mapaEspacio.convertir_lista_a_modelo_a_lista_de_dto(espaciosEncontrados);
  }

  @Override
  public void eliminarEspacioEnBD(UUID id) {
    Optional<Espacio> espacioBuscado = repositorioEspacio.findById(id);
    if (espacioBuscado.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El espacio que quieres eliminar no existe");
    }
    repositorioEspacio.deleteById(id);
  }
}
