package com.mycompany.transporteshirata.Logica;

/**
 * Entidad de negocio que representa un activo físico o equipo de oficina.
 * Clasifica los dispositivos informáticos asociados al requerimiento funcional RF-06.
 */
public class EquipoOficina {

    private int idEquipo;
    private String nombre;
    private String tipo; 
    private String marca;
    private String modelo;
    private String numeroSerie;
    private String estado; 

    /**
     * Constructor predeterminado vacío de la entidad.
     */
    public EquipoOficina() {}

    /**
     * Constructor parametrizado con las propiedades técnicas del activo.
     * * @param idEquipo Identificador correlativo en la BD.
     * @param nombre Nombre descriptivo del activo.
     * @param tipo Categoría del hardware (PC, Laptop, Impresora).
     * @param marca Fabricante del dispositivo.
     * @param modelo Código del modelo comercial.
     * @param numeroSerie Identificador de fábrica único.
     * @param estado Etiqueta operativa actual del equipo.
     */
    public EquipoOficina(int idEquipo, String nombre, String tipo,
                         String marca, String modelo,
                         String numeroSerie, String estado) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.estado = estado;
    }

    /** @return El identificador del equipo. */
    public int getIdEquipo() { return idEquipo; }
    
    /** @return El nombre asignado al equipo. */
    public String getNombre() { return nombre; }
    
    /** @return El tipo de hardware. */
    public String getTipo() { return tipo; }
    
    /** @return El fabricante o marca. */
    public String getMarca() { return marca; }
    
    /** @return El modelo del equipo. */
    public String getModelo() { return modelo; }
    
    /** @return El número de serie de fábrica. */
    public String getNumeroSerie() { return numeroSerie; }
    
    /** @return El estado operativo actual. */
    public String getEstado() { return estado; }

    /** @param idEquipo Identificador numérico único de la BD. */
    public void setIdEquipo(int idEquipo) { this.idEquipo = idEquipo; }
    
    /** @param nombre Nombre del dispositivo. */
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    /** @param tipo Categoría de hardware. */
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    /** @param marca Nombre del fabricante. */
    public void setMarca(String marca) { this.marca = marca; }
    
    /** @param modelo Variante comercial del fabricante. */
    public void setModelo(String modelo) { this.modelo = modelo; }
    
    /** @param n Cadena única de serie. */
    public void setNumeroSerie(String n) { this.numeroSerie = n; }
    
    /** @param estado Condición operativa (Activo, Reparación, Baja). */
    public void setEstado(String estado) { this.estado = estado; }

    /**
     * Representación textual simplificada del objeto, utilizada por los ComboBox.
     * * @return Formato de cadena con nombre, tipo y especificaciones técnicas.
     */
   @Override
    public String toString() {
       
        return (nombre != null && !nombre.isEmpty()) ? nombre : "Equipo sin nombre";
    }
}