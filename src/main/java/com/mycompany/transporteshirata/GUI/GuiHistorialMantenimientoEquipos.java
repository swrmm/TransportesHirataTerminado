/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.transporteshirata.GUI;

import com.mycompany.transporteshirata.Datos.MantenimientoEquipoDao;
import com.mycompany.transporteshirata.Logica.MantenimientoEquipo;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Ventana interna para visualizar el historial de mantenimientos de equipos.
 * Permite listar todos los mantenimientos registrados, buscar por código de
 * equipo y ver los detalles completos de cada mantenimiento.
 */
public class GuiHistorialMantenimientoEquipos extends javax.swing.JInternalFrame {

    /**
     * Constructor. Inicializa la interfaz gráfica, establece el título y carga
     * la tabla con todos los mantenimientos registrados.
     */
    public GuiHistorialMantenimientoEquipos() {
        initComponents();
        this.setTitle("     Historial Mantenimiento Equipos     ");
        cargarTabla("");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_historial = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_buscar = new javax.swing.JTextField();
        bt_buscar = new javax.swing.JButton();
        bt_refrescar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_tipo_equipo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_fecha = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_tipo_mantenimiento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_tecnico = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_estado = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_descripcion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_observaciones = new javax.swing.JTextField();
        bt_cancelar = new javax.swing.JButton();

        setClosable(true);

        tbl_historial.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null}
                },
                new String[]{
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        tbl_historial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_historialMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_historial);

        jLabel1.setText("Buscar por codigo");

        bt_buscar.setText("Buscar");
        bt_buscar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_buscarActionPerformed(evt);
            }
        });

        bt_refrescar.setText("Ver Todos");
        bt_refrescar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bt_refrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_refrescarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle mantenimiento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel2.setText("ID");
        txt_id.setEditable(false);

        jLabel3.setText("Codigo equipo");
        txt_codigo.setEditable(false);

        jLabel4.setText("Tipo equipo");
        txt_tipo_equipo.setEditable(false);

        jLabel5.setText("Fecha");
        txt_fecha.setEditable(false);

        jLabel6.setText("Tipo mant.");
        txt_tipo_mantenimiento.setEditable(false);

        jLabel7.setText("Tecnico");
        txt_tecnico.setEditable(false);

        jLabel8.setText("Estado");
        txt_estado.setEditable(false);

        jLabel9.setText("Descripcion");
        txt_descripcion.setEditable(false);

        jLabel10.setText("Observaciones");
        txt_observaciones.setEditable(false);

        bt_cancelar.setText("Cancelar");
        bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_id)
                                        .addComponent(txt_codigo)
                                        .addComponent(txt_tipo_equipo)
                                        .addComponent(txt_fecha)
                                        .addComponent(txt_tipo_mantenimiento)
                                        .addComponent(txt_tecnico)
                                        .addComponent(txt_estado)
                                        .addComponent(txt_descripcion)
                                        .addComponent(txt_observaciones))
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(138, 138, 138)
                                .addComponent(bt_cancelar)
                                .addContainerGap(154, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(txt_tipo_equipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(txt_tipo_mantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(txt_tecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(txt_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(txt_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel10)
                                        .addComponent(txt_observaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(bt_cancelar)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(18, 18, 18)
                                                .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(bt_refrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel1)
                                                        .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(bt_buscar)
                                                        .addComponent(bt_refrescar))
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        pack();
    }

    /**
     * Carga la tabla de historial con los mantenimientos de equipos. Si se
     * proporciona un filtro, muestra solo los equipos cuyo código coincida
     * parcialmente (sin distinción de mayúsculas/minúsculas).
     *
     * @param filtroCodigo código de equipo para filtrar (puede ser vacío o
     * null)
     */
    public void cargarTabla(String filtroCodigo) {
        String col[] = {"ID", "Fecha", "Tipo Mant.", "Codigo", "Equipo", "Tecnico", "Estado"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        MantenimientoEquipoDao dao = new MantenimientoEquipoDao();
        List<MantenimientoEquipo> lista = dao.listarMantenimientosEquipo();

        for (MantenimientoEquipo m : lista) {
            String codigo = m.getEquipo().getCodigo();
            if (filtroCodigo == null || filtroCodigo.isEmpty()
                    || codigo.toLowerCase().contains(filtroCodigo.toLowerCase())) {
                Object[] objs = {
                    m.getIdMantenimientoEquipo(),
                    m.getFechaMantenimiento(),
                    m.getTipoMantenimiento(),
                    codigo,
                    m.getEquipo().getTipo(),
                    m.getTecnicoResponsable(),
                    m.getEstado()
                };
                tableModel.addRow(objs);
            }
        }
        tbl_historial.setModel(tableModel);

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay mantenimientos de equipos registrados.");
        }
    }

    /**
     * Limpia todos los campos de detalle del mantenimiento seleccionado.
     */
    public void limpiarCajas() {
        txt_id.setText("");
        txt_codigo.setText("");
        txt_tipo_equipo.setText("");
        txt_fecha.setText("");
        txt_tipo_mantenimiento.setText("");
        txt_tecnico.setText("");
        txt_estado.setText("");
        txt_descripcion.setText("");
        txt_observaciones.setText("");
    }

    /**
     * Evento de clic en la tabla de historial. Muestra los detalles completos
     * del mantenimiento seleccionado en el panel de la derecha.
     */
    private void tbl_historialMouseClicked(java.awt.event.MouseEvent evt) {
        int fila = tbl_historial.getSelectedRow();
        if (fila != -1) {
            int idSeleccionado = (int) tbl_historial.getValueAt(fila, 0);
            MantenimientoEquipoDao dao = new MantenimientoEquipoDao();
            for (MantenimientoEquipo m : dao.listarMantenimientosEquipo()) {
                if (m.getIdMantenimientoEquipo() == idSeleccionado) {
                    txt_id.setText(String.valueOf(m.getIdMantenimientoEquipo()));
                    txt_codigo.setText(m.getEquipo().getCodigo());
                    txt_tipo_equipo.setText(m.getEquipo().getTipo());
                    txt_fecha.setText(m.getFechaMantenimiento().toString());
                    txt_tipo_mantenimiento.setText(m.getTipoMantenimiento());
                    txt_tecnico.setText(m.getTecnicoResponsable());
                    txt_estado.setText(m.getEstado());
                    txt_descripcion.setText(m.getDescripcion());
                    txt_observaciones.setText(m.getObservaciones() == null ? "" : m.getObservaciones());
                    break;
                }
            }
        }
    }

    /**
     * Evento del botón "Buscar". Filtra la tabla por el código ingresado.
     */
    private void bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {
        cargarTabla(txt_buscar.getText());
        limpiarCajas();
    }

    /**
     * Evento del botón "Ver Todos". Limpia el filtro y muestra todos los
     * mantenimientos.
     */
    private void bt_refrescarActionPerformed(java.awt.event.ActionEvent evt) {
        txt_buscar.setText("");
        cargarTabla("");
        limpiarCajas();
    }

    /**
     * Evento del botón "Cancelar". Limpia los campos de detalle y deselecciona
     * la fila actual de la tabla.
     */
    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {
        limpiarCajas();
        tbl_historial.clearSelection();
    }

    private javax.swing.JButton bt_buscar;
    private javax.swing.JButton bt_cancelar;
    private javax.swing.JButton bt_refrescar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_historial;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JTextField txt_estado;
    private javax.swing.JTextField txt_fecha;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_observaciones;
    private javax.swing.JTextField txt_tecnico;
    private javax.swing.JTextField txt_tipo_equipo;
    private javax.swing.JTextField txt_tipo_mantenimiento;
}
