package com.lopez.app.rutas.models;

import com.lopez.app.rutas.models.enums.Marcas;
import com.lopez.app.rutas.models.enums.Tipos;

public class Camion {
    private Long id;
    private String matricula;
    private Tipos tipoCamion;
    private Integer modelo;
    private Marcas marca;
    private Integer capacidad;
    private Double kilometros;
    private Boolean disponibilidad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Tipos getTipoCamion() {
        return tipoCamion;
    }

    public void setTipoCamion(Tipos tipoCamion) {
        this.tipoCamion = tipoCamion;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    public Marcas getMarca() {
        return marca;
    }

    public void setMarca(Marcas marca) {
        this.marca = marca;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Double getKilometros() {
        return kilometros;
    }

    public void setKilometros(Double kilometros) {
        this.kilometros = kilometros;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
