package com.mycompany.transporteshirata.Logica;

import java.time.LocalDateTime;

public class SensorGps {

    private int idLecturaGps;
    private Camion camion;
    private LocalDateTime fechaHora;
    private double latitud;
    private double longitud;
    private double velocidadKmh;
    private String ruta;
    private int tiempoRecorridoMinutos;
    private String estadoSenal;

    public int getIdLecturaGps() {
        return idLecturaGps;
    }

    public void setIdLecturaGps(int idLecturaGps) {
        this.idLecturaGps = idLecturaGps;
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

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getVelocidadKmh() {
        return velocidadKmh;
    }

    public void setVelocidadKmh(double velocidadKmh) {
        this.velocidadKmh = velocidadKmh;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getTiempoRecorridoMinutos() {
        return tiempoRecorridoMinutos;
    }

    public void setTiempoRecorridoMinutos(int tiempoRecorridoMinutos) {
        this.tiempoRecorridoMinutos = tiempoRecorridoMinutos;
    }

    public String getEstadoSenal() {
        return estadoSenal;
    }

    public void setEstadoSenal(String estadoSenal) {
        this.estadoSenal = estadoSenal;
    }
}
