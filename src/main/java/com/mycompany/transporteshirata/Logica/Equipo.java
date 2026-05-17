/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.transporteshirata.Logica;

/**
 *
 * @author danie
 */
public class Equipo {

    private int idEquipo;
    private String tipo;
    private String codigo;
    private String marca;
    private String ubicacion;
    private String estado;

    public Equipo() {
    }

    public Equipo(int idEquipo, String tipo, String codigo, String marca, String ubicacion, String estado) {
        this.idEquipo = idEquipo;
        this.tipo = tipo;
        this.codigo = codigo;
        this.marca = marca;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMarca() {
        return marca;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return codigo + " - " + tipo;
    }
}
