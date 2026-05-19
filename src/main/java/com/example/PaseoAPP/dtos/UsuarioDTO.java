package com.example.PaseoAPP.dtos;

import java.util.UUID;

public class UsuarioDTO {

    private UUID id;
    private String nombres;
    private String correo;
    private String rol;

    public UsuarioDTO() {
    }

    public UsuarioDTO(UUID id, String nombres, String correo, String rol) {
        this.id = id;
        this.nombres = nombres;
        this.correo = correo;
        this.rol = rol;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
