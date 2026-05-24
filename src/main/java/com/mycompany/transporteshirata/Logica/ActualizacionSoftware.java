package com.mycompany.transporteshirata.Logica;

import java.time.LocalDate;

public class ActualizacionSoftware {

    private int idActualizacion;
    private LocalDate fechaActualizacion;
    private String versionAnterior;
    private String versionNueva;
    private String tecnicoResponsable;
    private String estado;
    private String observaciones;
    private Equipo equipo;
    private Software software;

    public ActualizacionSoftware() {
    }

    public int getIdActualizacion() {
        return idActualizacion;
    }

    public void setIdActualizacion(int idActualizacion) {
        this.idActualizacion = idActualizacion;
    }

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getVersionAnterior() {
        return versionAnterior;
    }

    public void setVersionAnterior(String versionAnterior) {
        this.versionAnterior = versionAnterior;
    }

    public String getVersionNueva() {
        return versionNueva;
    }

    public void setVersionNueva(String versionNueva) {
        this.versionNueva = versionNueva;
    }

    public String getTecnicoResponsable() {
        return tecnicoResponsable;
    }

    public void setTecnicoResponsable(String tecnicoResponsable) {
        this.tecnicoResponsable = tecnicoResponsable;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Software getSoftware() {
        return software;
    }

    public void setSoftware(Software software) {
        this.software = software;
    }
}
