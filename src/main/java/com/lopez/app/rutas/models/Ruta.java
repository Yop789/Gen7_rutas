package com.lopez.app.rutas.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Ruta {

    private Long id;
    private Long camionId;
    private Long choferId;
    private Long direccionOriginalId;
    private Long direccionDestinoId;
    private Float diatancio;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegadaEstimada;
    private LocalDateTime fechaLlegadaReal;
    private Integer aTiempo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCamionId() {
        return camionId;
    }

    public void setCamionId(Long camionId) {
        this.camionId = camionId;
    }

    public Long getChoferId() {
        return choferId;
    }

    public void setChoferId(Long choferId) {
        this.choferId = choferId;
    }

    public Long getDireccionOriginalId() {
        return direccionOriginalId;
    }

    public void setDireccionOriginalId(Long direccionOriginalId) {
        this.direccionOriginalId = direccionOriginalId;
    }

    public Long getDireccionDestinoId() {
        return direccionDestinoId;
    }

    public void setDireccionDestinoId(Long direccionDestinoId) {
        this.direccionDestinoId = direccionDestinoId;
    }

    public Float getDiatancio() {
        return diatancio;
    }

    public void setDiatancio(Float diatancio) {
        this.diatancio = diatancio;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDateTime getFechaLlegadaEstimada() {
        return fechaLlegadaEstimada;
    }

    public void setFechaLlegadaEstimada(LocalDateTime fechaLlegadaEstimada) {
        this.fechaLlegadaEstimada = fechaLlegadaEstimada;
    }

    public LocalDateTime getFechaLlegadaReal() {
        return fechaLlegadaReal;
    }

    public void setFechaLlegadaReal(LocalDateTime fechaLlegadaReal) {
        this.fechaLlegadaReal = fechaLlegadaReal;
    }

    public Integer getaTiempo() {
        return aTiempo;
    }

    public void setaTiempo(Integer aTiempo) {
        this.aTiempo = aTiempo;
    }
}
