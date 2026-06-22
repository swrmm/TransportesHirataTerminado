package com.mycompany.transporteshirata.Logica;

public class Software {

    private int idSoftware;
    private String nombre;
    private String versionActual;
    private String proveedor;
    private String tipo;
    private String estado;

    public Software() {
    }

    public Software(int idSoftware, String nombre, String versionActual, String proveedor, String tipo, String estado) {
        this.idSoftware = idSoftware;
        this.nombre = nombre;
        this.versionActual = versionActual;
        this.proveedor = proveedor;
        this.tipo = tipo;
        this.estado = estado;
    }

    public int getIdSoftware() {
        return idSoftware;
    }

    public void setIdSoftware(int idSoftware) {
        this.idSoftware = idSoftware;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVersionActual() {
        return versionActual;
    }

    public void setVersionActual(String versionActual) {
        this.versionActual = versionActual;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombre + " - " + versionActual;
    }
}
