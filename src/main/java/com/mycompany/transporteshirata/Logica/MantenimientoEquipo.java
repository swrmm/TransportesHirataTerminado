<<<<<<< HEAD
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
package com.mycompany.transporteshirata.Logica;

import java.time.LocalDate;

/**
<<<<<<< HEAD
 * Entidad de negocio que modela un servicio técnico aplicado sobre un activo informático.
 * Encapsula la información requerida por los módulos RF-06 y RF-08.
=======
 *
 * @author danie
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
 */
public class MantenimientoEquipo {

    private int idMantenimientoEquipo;
<<<<<<< HEAD
    private EquipoOficina equipo;
    private LocalDate fecha;
    private String tipo; 
    private String descripcion;
    private String tecnico;
    private String observaciones;

    /**
     * Constructor de inicialización básica vacío.
     */
    public MantenimientoEquipo() {}

    /**
     * Constructor parametrizado de la entidad.
     * * @param idMantenimientoEquipo Clave primaria del mantenimiento.
     * @param equipo Instancia del activo asociado.
     * @param fecha Fecha de ejecución.
     * @param tipo Naturaleza técnica (Preventivo / Correctivo).
     * @param descripcion Detalle de los fallos identificados.
     * @param tecnico Nombre del operador a cargo.
     * @param observaciones Comentarios adicionales que incluyen la lista de chequeo compilada.
     */
    public MantenimientoEquipo(int idMantenimientoEquipo, EquipoOficina equipo,
                               LocalDate fecha, String tipo,
                               String descripcion, String tecnico,
                               String observaciones) {
        this.idMantenimientoEquipo = idMantenimientoEquipo;
        this.equipo = equipo;
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.tecnico = tecnico;
        this.observaciones = observaciones;
    }

    /** @return Identificador correlativo del servicio. */
    public int getIdMantenimientoEquipo() { return idMantenimientoEquipo; }
    
    /** @return Objeto del equipo intervenido. */
    public EquipoOficina getEquipo() { return equipo; }
    
    /** @return Fecha del mantenimiento. */
    public LocalDate getFecha() { return fecha; }
    
    /** @return Tipo de mantenimiento. */
    public String getTipo() { return tipo; }
    
    /** @return Descripción técnica del servicio. */
    public String getDescripcion() { return descripcion; }
    
    /** @return Nombre del técnico. */
    public String getTecnico() { return tecnico; }
    
    /** @return Observaciones finales del cierre. */
    public String getObservaciones() { return observaciones; }

    /** @param id Clave primaria de la tabla. */
    public void setIdMantenimientoEquipo(int id) { this.idMantenimientoEquipo = id; }
    
    /** @param equipo Activo informático a asociar. */
    public void setEquipo(EquipoOficina equipo) { this.equipo = equipo; }
    
    /** @param fecha Día en que se ejecuta la mantención. */
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    
    /** @param tipo Modalidad del servicio. */
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    /** @param descripcion Detalle técnico del trabajo. */
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    /** @param tecnico Nombre de la persona natural. */
    public void setTecnico(String tecnico) { this.tecnico = tecnico; }
    
    /** @param observaciones Bloque de texto con observaciones y marcas de checklist. */
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
=======
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
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
