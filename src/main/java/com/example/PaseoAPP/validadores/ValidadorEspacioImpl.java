package com.example.PaseoAPP.validadores;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.PaseoAPP.modelos.Espacio;
import com.example.PaseoAPP.repositorios.IRepositorioEspacio;

@Component
public class ValidadorEspacioImpl implements IValidadorEspacio {

  private final IRepositorioEspacio repositorioEspacio;

  public ValidadorEspacioImpl(IRepositorioEspacio repositorioEspacio) {
    this.repositorioEspacio = repositorioEspacio;
  }

  @Override
  public void validarNuevoEspacio(Espacio datos) {
    validarNombreNoVacio(datos.getNombre());
    validarDescripcionNoVacia(datos.getDescripcion());
    validarAforoPositivo(datos.getAforo());
    validarNombreNoRepetido(datos.getNombre());
  }

  @Override
  public void validarDatosModificacion(Espacio datos) {
    validarNombreNoVacio(datos.getNombre());
    validarDescripcionNoVacia(datos.getDescripcion());
    validarAforoPositivo(datos.getAforo());
  }

  public void validarNombreNoRepetido(String nombre) {
    if (repositorioEspacio.findByNombre(nombre).isPresent()) {
      throw new ResponseStatusException(
          HttpStatus.CONFLICT,
          "Ya existe un espacio con el nombre ingresado"
      );
    }
  }

  public void validarNombreNoVacio(String nombre) {
    if (nombre == null || nombre.isEmpty() || nombre.isBlank()) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "El nombre del espacio es obligatorio"
      );
    }
  }

  public void validarDescripcionNoVacia(String descripcion) {
    if (descripcion == null || descripcion.isEmpty() || descripcion.isBlank()) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "La descripción del espacio es obligatoria"
      );
    }
  }

  public void validarAforoPositivo(int aforo) {
    if (aforo <= 0) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "El aforo debe ser mayor a cero"
      );
    }
  }

}
