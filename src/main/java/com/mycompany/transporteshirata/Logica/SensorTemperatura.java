package com.mycompany.transporteshirata.Logica;

import java.time.LocalDateTime;

public class SensorTemperatura {

    private int idLecturaTemperatura;
    private Camion camion;
    private LocalDateTime fechaHora;
    private double temperaturaCelsius;
    private double limiteCritico;
    private String estado;
    private String observacion;

    public int getIdLecturaTemperatura() {
        return idLecturaTemperatura;
    }

    public void setIdLecturaTemperatura(int idLecturaTemperatura) {
        this.idLecturaTemperatura = idLecturaTemperatura;
    }

    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public double getTemperaturaCelsius() {
        return temperaturaCelsius;
    }

    public void setTemperaturaCelsius(double temperaturaCelsius) {
        this.temperaturaCelsius = temperaturaCelsius;
    }

    public double getLimiteCritico() {
        return limiteCritico;
    }

    public void setLimiteCritico(double limiteCritico) {
        this.limiteCritico = limiteCritico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
