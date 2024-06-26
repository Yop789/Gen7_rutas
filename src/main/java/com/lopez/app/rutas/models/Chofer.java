package com.lopez.app.rutas.models;

import java.time.LocalDate;

public class Chofer {

    private Long id;
    private String nombre;
    private String apPaternos;
    private String apMaternos;
    private String licencias;
    private String telefono;
    private LocalDate fechaNacimiento;
    private Boolean diponivilidad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaternos() {
        return apPaternos;
    }

    public void setApPaternos(String apPaternos) {
        this.apPaternos = apPaternos;
    }

    public String getApMaternos() {
        return apMaternos;
    }

    public void setApMaternos(String apMaternos) {
        this.apMaternos = apMaternos;
    }

    public String getLicencias() {
        return licencias;
    }

    public void setLicencias(String licencias) {
        this.licencias = licencias;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Boolean getDiponivilidad() {
        return diponivilidad;
    }

    public void setDiponivilidad(Boolean diponivilidad) {
        this.diponivilidad = diponivilidad;
    }

}
