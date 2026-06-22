package com.mycompany.transporteshirata.Logica;

import java.time.LocalDate;

public class MovimientoInventario {

    private int idMovimiento;
    private LocalDate fecha;
    private String tipoMovimiento;
    private int cantidad;
    private String motivo;
    private String tecnicoResponsable;
    private Pieza pieza;
    private MantenimientoEquipo mantenimientoEquipo;

    public MovimientoInventario() {
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getTecnicoResponsable() {
        return tecnicoResponsable;
    }

    public void setTecnicoResponsable(String tecnicoResponsable) {
        this.tecnicoResponsable = tecnicoResponsable;
    }

    public Pieza getPieza() {
        return pieza;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    public MantenimientoEquipo getMantenimientoEquipo() {
        return mantenimientoEquipo;
    }

    public void setMantenimientoEquipo(MantenimientoEquipo mantenimientoEquipo) {
        this.mantenimientoEquipo = mantenimientoEquipo;
    }
}
