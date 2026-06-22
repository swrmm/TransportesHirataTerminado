package com.mycompany.transporteshirata.Logica;

/**
 * Entidad de negocio que representa un insumo o pieza de repuesto física.
 * Modela las propiedades básicas del inventariado asociadas al módulo RF-09.
 */
public class PiezaRepuesto {

    private int idPieza;
    private String nombre;
    private String descripcion;
    private int stock;
    private String estado; 

    /**
     * Constructor predeterminado vacío de la entidad.
     */
    public PiezaRepuesto() {}

    /**
     * Constructor parametrizado de la pieza de repuesto.
     * * @param idPieza Clave única del insumo.
     * @param nombre Denominación comercial del artículo.
     * @param descripcion Detalle del alcance técnico o compatibilidad.
     * @param stock Volumen disponible en el inventario.
     * @param estado Clasificación (Disponible, Sin Stock, Descontinuado).
     */
    public PiezaRepuesto(int idPieza, String nombre, String descripcion,
                         int stock, String estado) {
        this.idPieza = idPieza;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.estado = estado;
    }

    /** @return Clave primaria de la pieza. */
    public int getIdPieza() { return idPieza; }
    
    /** @return Nombre descriptivo. */
    public String getNombre() { return nombre; }
    
    /** @return Resumen descriptivo. */
    public String getDescripcion() { return descripcion; }
    
    /** @return Cantidad disponible. */
    public int getStock() { return stock; }
    
    /** @return Cadena de estado de inventario. */
    public String getEstado() { return estado; }

    /** @param idPieza Identificador numérico de la BD. */
    public void setIdPieza(int idPieza) { this.idPieza = idPieza; }
    
    /** @param nombre Nombre del artículo. */
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    /** @param d Descripción del artículo. */
    public void setDescripcion(String d) { this.descripcion = d; }
    
    /** @param stock Cantidad de unidades físicas. */
    public void setStock(int stock) { this.stock = stock; }
    
    /** @param estado Etiqueta de la disponibilidad del stock. */
    public void setEstado(String estado) { this.estado = estado; }

    /**
     * Formatea el objeto en una representación legible para menús descolgables.
     * * @return Nombre de la pieza acompañado del stock en tiempo real.
     */
    @Override
    public String toString() {
        return nombre + " (Stock: " + stock + ")";
    }
}
