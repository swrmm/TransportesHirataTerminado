/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.transporteshirata.GUI;

import com.mycompany.transporteshirata.Datos.UsuarioDao;
import com.mycompany.transporteshirata.Logica.Usuario;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author danie
 */
public class GuiRegistrarPersonal extends javax.swing.JInternalFrame {

    /**
     * Creates new form GuiRegistrarPersonal
     */
    public GuiRegistrarPersonal() {
        initComponents();
        txt_id.setVisible(false);
        jLabel1.setVisible(false);
        cargarTabla();
        cambiarAModoNuevo();
        this.setTitle("     Registrar Personal     ");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (pantalla.width - this.getWidth());
        int y = (pantalla.height - this.getHeight());
        this.setLocation(x / 2 + 150, y / 2 - 40);
        this.setSize(900, 600);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_rut = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_clave = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        txt_confirmar = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        txt_cargo = new javax.swing.JTextField();
        bt_guardar = new javax.swing.JButton();
        bt_editar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        bt_cancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_personal = new javax.swing.JTable();

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("ID");
        txt_id.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("RUT");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Clave");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Confirmar clave");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Cargo");
        txt_cargo.setEditable(false);
        txt_cargo.setText("personal");

        bt_guardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bt_guardar.setText("Guardar");
        bt_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_guardarActionPerformed(evt);
            }
        });

        bt_editar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bt_editar.setText("Editar");
        bt_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editarActionPerformed(evt);
            }
        });

        bt_eliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bt_eliminar.setText("Eliminar");
        bt_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminarActionPerformed(evt);
            }
        });

        bt_cancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_id)
                                        .addComponent(txt_rut)
                                        .addComponent(txt_clave)
                                        .addComponent(txt_confirmar)
                                        .addComponent(txt_cargo))
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(bt_guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bt_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(bt_editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bt_cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(txt_rut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txt_clave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(txt_confirmar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(txt_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bt_guardar)
                                        .addComponent(bt_editar))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bt_eliminar)
                                        .addComponent(bt_cancelar))
                                .addContainerGap(151, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Listado de personal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbl_personal.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null}
                },
                new String[]{
                    "ID", "RUT", "Cargo"
                }
        ));
        tbl_personal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_personalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_personal);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
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
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
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
        String col[] = {"ID", "RUT", "Cargo"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);

        for (Usuario u : usuarioDao.listarPersonal()) {
            Object[] objs = {
                u.getIdUsuario(),
                u.getRut(),
                u.getCargo()
            };
            tableModel.addRow(objs);
        }
        tbl_personal.setModel(tableModel);
    }

    public void cambiarAModoEdicion() {
        this.bt_cancelar.setEnabled(true);
        this.bt_eliminar.setEnabled(true);
        this.bt_editar.setEnabled(true);
        this.bt_guardar.setEnabled(false);
    }

    public void cambiarAModoNuevo() {
        this.bt_cancelar.setEnabled(false);
        this.bt_eliminar.setEnabled(false);
        this.bt_editar.setEnabled(false);
        this.bt_guardar.setEnabled(true);
        limpiarFormulario();
    }

    public void limpiarFormulario() {
        this.txt_id.setText("");
        this.txt_rut.setText("");
        this.txt_clave.setText("");
        this.txt_confirmar.setText("");
        this.txt_cargo.setText("personal");
        this.tbl_personal.clearSelection();
    }

    private boolean validarFormulario() {
        String rut = txt_rut.getText().trim();
        String clave = new String(txt_clave.getPassword());
        String confirmar = new String(txt_confirmar.getPassword());

        if (rut.isEmpty() || clave.isEmpty() || confirmar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete RUT y clave.");
            return false;
        }
        if (!GuiRegistrarConductor.validarRut(rut)) {
            JOptionPane.showMessageDialog(this, "Formato de RUT invalido. Use el formato: 12345678-9");
            return false;
        }
        if (clave.length() < 6 || clave.length() > 20) {
            JOptionPane.showMessageDialog(this, "La clave debe tener entre 6 y 20 caracteres.");
            return false;
        }
        if (!clave.equals(confirmar)) {
            JOptionPane.showMessageDialog(this, "Las claves no coinciden.");
            return false;
        }
        return true;
    }

    private Usuario obtenerUsuarioDesdeFormulario() {
        Usuario usuario = new Usuario();
        if (!txt_id.getText().trim().isEmpty()) {
            usuario.setIdUsuario(Integer.parseInt(txt_id.getText()));
        }
        usuario.setRut(txt_rut.getText().trim());
        usuario.setClave(new String(txt_clave.getPassword()));
        usuario.setCargo("personal");
        return usuario;
    }

    private void bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {
        if (!validarFormulario()) {
            return;
        }

        Usuario usuario = obtenerUsuarioDesdeFormulario();
        if (usuarioDao.existeRut(usuario.getRut(), 0)) {
            JOptionPane.showMessageDialog(this, "Ya existe un usuario registrado con ese RUT.");
            return;
        }

        if (usuarioDao.registrarPersonal(usuario)) {
            JOptionPane.showMessageDialog(this, "Personal registrado correctamente.");
            cargarTabla();
            cambiarAModoNuevo();
        }
    }

    private void tbl_personalMouseClicked(java.awt.event.MouseEvent evt) {
        int selectedRow = this.tbl_personal.getSelectedRow();
        if (selectedRow != -1) {
            int idSeleccionado = (int) tbl_personal.getValueAt(selectedRow, 0);
            for (Usuario u : usuarioDao.listarPersonal()) {
                if (u.getIdUsuario() == idSeleccionado) {
                    this.txt_id.setText(String.valueOf(u.getIdUsuario()));
                    this.txt_rut.setText(u.getRut());
                    this.txt_clave.setText(u.getClave());
                    this.txt_confirmar.setText(u.getClave());
                    this.txt_cargo.setText(u.getCargo());
                    break;
                }
            }
            cambiarAModoEdicion();
        }
    }

    private void bt_editarActionPerformed(java.awt.event.ActionEvent evt) {
        if (txt_id.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario de la tabla.");
            return;
        }
        if (!validarFormulario()) {
            return;
        }

        Usuario usuario = obtenerUsuarioDesdeFormulario();
        if (usuarioDao.existeRut(usuario.getRut(), usuario.getIdUsuario())) {
            JOptionPane.showMessageDialog(this, "Ya existe otro usuario registrado con ese RUT.");
            return;
        }

        if (usuarioDao.modificarPersonal(usuario)) {
            JOptionPane.showMessageDialog(this, "Personal actualizado correctamente.");
            cargarTabla();
            cambiarAModoNuevo();
        }
    }

    private void bt_eliminarActionPerformed(java.awt.event.ActionEvent evt) {
        if (txt_id.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario de la tabla.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "Esta seguro de eliminar este usuario de personal?", "Confirmar eliminacion", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            int idUsuario = Integer.parseInt(txt_id.getText());
            if (usuarioDao.eliminarPersonal(idUsuario)) {
                JOptionPane.showMessageDialog(this, "Personal eliminado correctamente.");
                cargarTabla();
                cambiarAModoNuevo();
            }
        }
    }

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {
        cambiarAModoNuevo();
    }

    UsuarioDao usuarioDao = new UsuarioDao();
    private javax.swing.JButton bt_cancelar;
    private javax.swing.JButton bt_editar;
    private javax.swing.JButton bt_eliminar;
    private javax.swing.JButton bt_guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_personal;
    private javax.swing.JTextField txt_cargo;
    private javax.swing.JPasswordField txt_clave;
    private javax.swing.JPasswordField txt_confirmar;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_rut;
}
