/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.transporteshirata.Logica;

import java.time.LocalDate;

/**
 *
 * @author danie
 */
public class MantenimientoEquipo {

    private int idMantenimientoEquipo;
    private LocalDate fechaMantenimiento;
    private String tipoMantenimiento;
    private String descripcion;
    private String tecnicoResponsable;
    private String observaciones;
    private String estado;
    private Equipo equipo;

    public MantenimientoEquipo() {
    }

    public MantenimientoEquipo(int idMantenimientoEquipo, LocalDate fechaMantenimiento, String tipoMantenimiento,
            String descripcion, String tecnicoResponsable, String observaciones, String estado, Equipo equipo) {
        this.idMantenimientoEquipo = idMantenimientoEquipo;
        this.fechaMantenimiento = fechaMantenimiento;
        this.tipoMantenimiento = tipoMantenimiento;
        this.descripcion = descripcion;
        this.tecnicoResponsable = tecnicoResponsable;
        this.observaciones = observaciones;
        this.estado = estado;
        this.equipo = equipo;
    }

    public int getIdMantenimientoEquipo() {
        return idMantenimientoEquipo;
    }

    public LocalDate getFechaMantenimiento() {
        return fechaMantenimiento;
    }

    public String getTipoMantenimiento() {
        return tipoMantenimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTecnicoResponsable() {
        return tecnicoResponsable;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setIdMantenimientoEquipo(int idMantenimientoEquipo) {
        this.idMantenimientoEquipo = idMantenimientoEquipo;
    }

    public void setFechaMantenimiento(LocalDate fechaMantenimiento) {
        this.fechaMantenimiento = fechaMantenimiento;
    }

    public void setTipoMantenimiento(String tipoMantenimiento) {
        this.tipoMantenimiento = tipoMantenimiento;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTecnicoResponsable(String tecnicoResponsable) {
        this.tecnicoResponsable = tecnicoResponsable;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
