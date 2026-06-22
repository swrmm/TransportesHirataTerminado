package com.mycompany.transporteshirata.Logica;

import java.time.LocalDate;

<<<<<<< HEAD
/**
 * Entidad de negocio que representa el registro de una actualización de software.
 * Mapea la estructura lógica para dar cumplimiento al requerimiento funcional RF-07.
 */
public class ActualizacionSoftware {

    private int idActualizacion;
    private EquipoOficina equipo;
    private String nombreSoftware;
    private String versionAnterior;
    private String versionNueva;
    private LocalDate fechaActualizacion;
    private String responsable;

    /**
     * Constructor predeterminado vacío de la entidad.
     */
    public ActualizacionSoftware() {}

    /**
     * Constructor parametrizado con todos los atributos de la entidad.
     * * @param idActualizacion Identificador único del registro.
     * @param equipo Entidad del equipo asociado al software.
     * @param nombreSoftware Nombre comercial del software actualizado.
     * @param versionAnterior Versión previa del sistema o aplicativo.
     * @param versionNueva Nueva versión instalada.
     * @param fechaActualizacion Fecha en que se materializó el proceso.
     * @param responsable Nombre o credencial del técnico a cargo.
     */
    public ActualizacionSoftware(int idActualizacion, EquipoOficina equipo,
                                  String nombreSoftware, String versionAnterior,
                                  String versionNueva, LocalDate fechaActualizacion,
                                  String responsable) {
        this.idActualizacion = idActualizacion;
        this.equipo = equipo;
        this.nombreSoftware = nombreSoftware;
        this.versionAnterior = versionAnterior;
        this.versionNueva = versionNueva;
        this.fechaActualizacion = fechaActualizacion;
        this.responsable = responsable;
    }

    /** @return El identificador de la actualización. */
    public int getIdActualizacion() { return idActualizacion; }
    
    /** @return El objeto equipo de oficina asociado. */
    public EquipoOficina getEquipo() { return equipo; }
    
    /** @return El nombre del software. */
    public String getNombreSoftware() { return nombreSoftware; }
    
    /** @return La cadena de la versión previa. */
    public String getVersionAnterior() { return versionAnterior; }
    
    /** @return La cadena de la nueva versión. */
    public String getVersionNueva() { return versionNueva; }
    
    /** @return La fecha de la actualización. */
    public LocalDate getFechaActualizacion() { return fechaActualizacion; }
    
    /** @return El nombre del técnico responsable. */
    public String getResponsable() { return responsable; }

    /** @param id Nuevo valor para el identificador único. */
    public void setIdActualizacion(int id) { this.idActualizacion = id; }
    
    /** @param equipo Entidad del equipo a vincular. */
    public void setEquipo(EquipoOficina equipo) { this.equipo = equipo; }
    
    /** @param n Nombre del software. */
    public void setNombreSoftware(String n) { this.nombreSoftware = n; }
    
    /** @param v Identificador de la versión anterior. */
    public void setVersionAnterior(String v) { this.versionAnterior = v; }
    
    /** @param v Identificador de la versión nueva. */
    public void setVersionNueva(String v) { this.versionNueva = v; }
    
    /** @param fecha Fecha en que se efectúa el cambio. */
    public void setFechaActualizacion(LocalDate fecha) { this.fechaActualizacion = fecha; }
    
    /** @param responsable Nombre de la persona a cargo. */
    public void setResponsable(String responsable) { this.responsable = responsable; }
}
=======
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
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
