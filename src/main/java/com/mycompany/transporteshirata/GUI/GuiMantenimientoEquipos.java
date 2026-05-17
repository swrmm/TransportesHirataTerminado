/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.transporteshirata.GUI;

import com.mycompany.transporteshirata.Datos.EquipoDao;
import com.mycompany.transporteshirata.Datos.MantenimientoEquipoDao;
import com.mycompany.transporteshirata.Logica.Equipo;
import com.mycompany.transporteshirata.Logica.MantenimientoEquipo;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author danie
 */
public class GuiMantenimientoEquipos extends javax.swing.JInternalFrame {

    private int idEquipoSeleccionado = 0;

    /**
     * Creates new form GuiMantenimientoEquipos
     */
    public GuiMantenimientoEquipos() {
        initComponents();
        this.setTitle("     Mantenimiento Equipos de Oficina     ");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (pantalla.width - this.getWidth());
        int y = (pantalla.height - this.getHeight());
        this.setLocation(x / 2 + 150, y / 2 - 40);
        cargarTabla();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_equipos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_equipo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_fecha = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmb_tipo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txt_descripcion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_tecnico = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_observaciones = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cmb_estado = new javax.swing.JComboBox<>();
        bt_guardar = new javax.swing.JButton();
        bt_cancelar = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Equipos de oficina", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbl_equipos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null}
                },
                new String[]{
                    "ID", "Tipo", "Codigo", "Marca", "Ubicacion", "Estado"
                }
        ));
        tbl_equipos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_equiposMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_equipos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registrar mantenimiento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setText("ID");
        txt_id.setEditable(false);

        jLabel2.setText("Equipo");
        txt_equipo.setEditable(false);

        jLabel3.setText("Fecha");
        txt_fecha.setEditable(false);

        jLabel4.setText("Tipo");
        cmb_tipo.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        cmb_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"preventivo", "correctivo"}));

        jLabel5.setText("descripcion");

        jLabel6.setText("Tecnico");

        jLabel7.setText("Observaciones");

        jLabel8.setText("Estado");
        cmb_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"realizado", "pendiente"}));

        bt_guardar.setText("Guardar");
        bt_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_guardarActionPerformed(evt);
            }
        });

        bt_cancelar.setText("Cancelar");
        bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txt_id)
                                                        .addComponent(txt_equipo)
                                                        .addComponent(txt_fecha)
                                                        .addComponent(cmb_tipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txt_descripcion)
                                                        .addComponent(txt_tecnico)
                                                        .addComponent(txt_observaciones)
                                                        .addComponent(cmb_estado, 0, 404, Short.MAX_VALUE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(bt_guardar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(bt_cancelar)))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(txt_equipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(cmb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(txt_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(txt_tecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(txt_observaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(cmb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bt_guardar)
                                        .addComponent(bt_cancelar))
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();
    }

    public void cargarTabla() {
        String col[] = {"ID", "Tipo", "Codigo", "Marca", "Ubicacion", "Estado"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        List<Equipo> lista = equipoDao.listarEquipos();

        for (Equipo e : lista) {
            Object[] objs = {
                e.getIdEquipo(),
                e.getTipo(),
                e.getCodigo(),
                e.getMarca(),
                e.getUbicacion(),
                e.getEstado()
            };
            tableModel.addRow(objs);
        }

        tbl_equipos.setModel(tableModel);
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay equipos registrados. Debe registrar el equipo primero.");
        }
    }

    public void limpiarFormulario() {
        this.idEquipoSeleccionado = 0;
        this.txt_id.setText("");
        this.txt_equipo.setText("");
        this.txt_fecha.setText("");
        this.txt_descripcion.setText("");
        this.txt_tecnico.setText("");
        this.txt_observaciones.setText("");
        this.tbl_equipos.clearSelection();
        this.cmb_tipo.setSelectedIndex(0);
        this.cmb_estado.setSelectedIndex(0);
    }

    private void tbl_equiposMouseClicked(java.awt.event.MouseEvent evt) {
        int fila = tbl_equipos.getSelectedRow();
        if (fila != -1) {
            idEquipoSeleccionado = (int) tbl_equipos.getValueAt(fila, 0);
            String codigo = tbl_equipos.getValueAt(fila, 2).toString();
            String tipo = tbl_equipos.getValueAt(fila, 1).toString();

            txt_id.setText(String.valueOf(idEquipoSeleccionado));
            txt_equipo.setText(codigo + " - " + tipo);
            txt_fecha.setText(LocalDate.now().toString());
        }
    }

    private void bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {
        if (tbl_equipos.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No hay equipos registrados. Debe registrar el equipo primero.");
            return;
        }
        if (idEquipoSeleccionado == 0 || txt_id.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un equipo de la tabla.");
            return;
        }
        if (!equipoDao.existeEquipo(idEquipoSeleccionado)) {
            JOptionPane.showMessageDialog(this, "El equipo seleccionado no esta registrado. Debe registrarlo primero.");
            cargarTabla();
            limpiarFormulario();
            return;
        }
        if (txt_descripcion.getText().trim().isEmpty() || txt_tecnico.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete la descripcion y el tecnico responsable.");
            return;
        }

        try {
            Equipo equipo = new Equipo();
            equipo.setIdEquipo(idEquipoSeleccionado);

            MantenimientoEquipo mantenimiento = new MantenimientoEquipo();
            mantenimiento.setEquipo(equipo);
            mantenimiento.setFechaMantenimiento(LocalDate.parse(txt_fecha.getText()));
            mantenimiento.setTipoMantenimiento(cmb_tipo.getSelectedItem().toString());
            mantenimiento.setDescripcion(txt_descripcion.getText());
            mantenimiento.setTecnicoResponsable(txt_tecnico.getText());
            mantenimiento.setObservaciones(txt_observaciones.getText());
            mantenimiento.setEstado(cmb_estado.getSelectedItem().toString());

            if (mantenimientoEquipoDao.registrarMantenimientoEquipo(mantenimiento)) {
                JOptionPane.showMessageDialog(this, "Mantenimiento de equipo registrado correctamente.");
                limpiarFormulario();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en los datos. Revise el formato.");
        }
    }

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {
        limpiarFormulario();
    }

    EquipoDao equipoDao = new EquipoDao();
    MantenimientoEquipoDao mantenimientoEquipoDao = new MantenimientoEquipoDao();
    private javax.swing.JButton bt_cancelar;
    private javax.swing.JButton bt_guardar;
    private javax.swing.JComboBox<String> cmb_estado;
    private javax.swing.JComboBox<String> cmb_tipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_equipos;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JTextField txt_equipo;
    private javax.swing.JTextField txt_fecha;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_observaciones;
    private javax.swing.JTextField txt_tecnico;
}
