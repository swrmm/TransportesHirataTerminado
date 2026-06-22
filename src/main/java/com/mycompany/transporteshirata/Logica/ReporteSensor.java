package com.mycompany.transporteshirata.Logica;

public class ReporteSensor {

    private String requerimiento;
    private String indicador;
    private String valor;
    private String detalle;

    public ReporteSensor(String requerimiento, String indicador, String valor, String detalle) {
        this.requerimiento = requerimiento;
        this.indicador = indicador;
        this.valor = valor;
        this.detalle = detalle;
    }

    public String getRequerimiento() {
        return requerimiento;
    }

    public String getIndicador() {
        return indicador;
    }

    public String getValor() {
        return valor;
    }

    public String getDetalle() {
        return detalle;
    }
}
