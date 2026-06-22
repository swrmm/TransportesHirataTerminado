/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/MDIApplication.java to edit this template
 */
package com.mycompany.transporteshirata.GUI;

import javax.swing.JFrame;

/**
 *
 * @author danie
 */
public class Principal extends javax.swing.JFrame {

   /**
     * Recibe el rol para configurar el sistema
     */
    public Principal(String cargo) {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("     \n     Sistema Principal - Transportes Hirata | Rol: " + cargo + "     \n     ");
        configurarMenuCompleto(cargo);
    }

    /**
     * Configura dinámicamente el menú y aplica el Control de Acceso (RBAC)
     */
    private void configurarMenuCompleto(String cargo) {
        menuBar.removeAll();

        // ==========================================
        // MENÚ 1: FLOTA (Etapa 1)
        // ==========================================
        javax.swing.JMenu menuFlota = new javax.swing.JMenu("Flota y Conductores");
        menuFlota.setFont(new java.awt.Font("Sitka Text", 1, 20));

        javax.swing.JMenuItem itemRegistrarCamion = new javax.swing.JMenuItem("Registrar Camión");
        itemRegistrarCamion.setFont(new java.awt.Font("Segoe UI", 0, 16));
        itemRegistrarCamion.addActionListener(e -> {
            GuiRegistrarCamion v = new GuiRegistrarCamion();
            desktopPane.add(v);
            v.setVisible(true);
        });

        javax.swing.JMenuItem itemRegistrarConductor = new javax.swing.JMenuItem("Registrar Conductor");
        itemRegistrarConductor.setFont(new java.awt.Font("Segoe UI", 0, 16));
        itemRegistrarConductor.addActionListener(e -> {
            GuiRegistrarConductor c = new GuiRegistrarConductor();
            desktopPane.add(c);
            c.setVisible(true);
        });
        menuFlota.add(itemRegistrarCamion);
        menuFlota.add(itemRegistrarConductor);

        // ==========================================
        // MENÚ 2: MANTENIMIENTO CAMIONES (Etapa 1)
        // ==========================================
        javax.swing.JMenu menuMantCamiones = new javax.swing.JMenu("Mant. Camiones");
        menuMantCamiones.setFont(new java.awt.Font("Sitka Text", 1, 20));

        javax.swing.JMenuItem itemMantenimientoCamion = new javax.swing.JMenuItem("Registrar Mantenimiento");
        itemMantenimientoCamion.setFont(new java.awt.Font("Segoe UI", 0, 16));
        itemMantenimientoCamion.addActionListener(e -> {
            GuiMantenimiento m = new GuiMantenimiento();
            desktopPane.add(m);
            m.setVisible(true);
        });

        javax.swing.JMenuItem itemHistorialCamiones = new javax.swing.JMenuItem("Historial Camiones");
        itemHistorialCamiones.setFont(new java.awt.Font("Segoe UI", 0, 16));
        itemHistorialCamiones.addActionListener(e -> {
            GuiHistorialMantenimiento hist = new GuiHistorialMantenimiento();
            desktopPane.add(hist);
            hist.setVisible(true);
        });
        menuMantCamiones.add(itemMantenimientoCamion);
        menuMantCamiones.add(itemHistorialCamiones);

        // ==========================================
        // MENÚ 3: EQUIPOS DE OFICINA (Etapa 2)
        // ==========================================
        javax.swing.JMenu menuEquiposOficina = new javax.swing.JMenu("Equipos de Oficina");
        menuEquiposOficina.setFont(new java.awt.Font("Sitka Text", 1, 20));

        javax.swing.JMenuItem itemRegEquipo = new javax.swing.JMenuItem("Registrar Equipo");
        itemRegEquipo.setFont(new java.awt.Font("Segoe UI", 0, 16));
        itemRegEquipo.addActionListener(e -> {
            GuiEquipoOficina v = new GuiEquipoOficina();
            desktopPane.add(v);
            v.setVisible(true);
        });

        javax.swing.JMenuItem itemMantEquipo = new javax.swing.JMenuItem("Mantenimiento Equipos");
        itemMantEquipo.setFont(new java.awt.Font("Segoe UI", 0, 16));
        itemMantEquipo.addActionListener(e -> {
            GuiMantenimientoEquipo v = new GuiMantenimientoEquipo();
            desktopPane.add(v);
            v.setVisible(true);
        });

        javax.swing.JMenuItem itemSoftware = new javax.swing.JMenuItem("Actualización Software");
        itemSoftware.setFont(new java.awt.Font("Segoe UI", 0, 16));
        itemSoftware.addActionListener(e -> {
            GuiActualizacionSoftware v = new GuiActualizacionSoftware();
            desktopPane.add(v);
            v.setVisible(true);
        });

        javax.swing.JMenuItem itemHistorialEquipos = new javax.swing.JMenuItem("Historial Equipos");
        itemHistorialEquipos.setFont(new java.awt.Font("Segoe UI", 0, 16));
        itemHistorialEquipos.addActionListener(e -> {
            GuiHistorialEquipos v = new GuiHistorialEquipos();
            desktopPane.add(v);
            v.setVisible(true);
        });

        javax.swing.JMenuItem itemInventario = new javax.swing.JMenuItem("Inventario de Piezas");
        itemInventario.setFont(new java.awt.Font("Segoe UI", 0, 16));
        itemInventario.addActionListener(e -> {
            GuiInventarioPiezas v = new GuiInventarioPiezas();
            desktopPane.add(v);
            v.setVisible(true);
        });

        menuEquiposOficina.add(itemRegEquipo);
        menuEquiposOficina.add(itemMantEquipo);
        menuEquiposOficina.add(itemSoftware);
        menuEquiposOficina.add(itemHistorialEquipos);
        menuEquiposOficina.add(itemInventario);

        javax.swing.JMenu menuSensores = new javax.swing.JMenu("Sensores IoT");
        menuSensores.setFont(new java.awt.Font("Sitka Text", 1, 20));

        javax.swing.JMenuItem itemMonitoreoSensores = new javax.swing.JMenuItem("Monitoreo y Reportes");
        itemMonitoreoSensores.setFont(new java.awt.Font("Segoe UI", 0, 16));
        itemMonitoreoSensores.addActionListener(e -> {
            GuiMonitoreoSensores v = new GuiMonitoreoSensores();
            desktopPane.add(v);
            v.setVisible(true);
        });
        menuSensores.add(itemMonitoreoSensores);

        // ==========================================
        // NUEVO MENÚ 4: SEGURIDAD Y USUARIOS
        // ==========================================
        javax.swing.JMenu menuSeguridad = new javax.swing.JMenu("Seguridad");
        menuSeguridad.setFont(new java.awt.Font("Sitka Text", 1, 20));

        javax.swing.JMenuItem itemUsuarios = new javax.swing.JMenuItem("Gestión de Usuarios y Roles");
        itemUsuarios.setFont(new java.awt.Font("Segoe UI", 0, 16));
        itemUsuarios.addActionListener(e -> {
            GuiGestionUsuarios v = new GuiGestionUsuarios();
            desktopPane.add(v);
            v.setVisible(true);
        });
        menuSeguridad.add(itemUsuarios);

        // ==========================================
        // MENÚ 5: SALIR / CERRAR SESIÓN
        // ==========================================
        javax.swing.JMenu menuSalir = new javax.swing.JMenu("Sistema");
        menuSalir.setFont(new java.awt.Font("Sitka Text", 1, 20));

        javax.swing.JMenuItem itemCerrarSesion = new javax.swing.JMenuItem("Cerrar Sesión");
        itemCerrarSesion.setFont(new java.awt.Font("Segoe UI", 1, 16));
        itemCerrarSesion.setForeground(java.awt.Color.RED);
        itemCerrarSesion.addActionListener(e -> {
            this.setVisible(false);
            GuiLoginGeneral login = new GuiLoginGeneral();
            login.setVisible(true);
        });
        menuSalir.add(itemCerrarSesion);

        // ==========================================
        // APLICACIÓN DE PERMISOS (RBAC)
        // ==========================================
        
 
        menuFlota.setVisible(false);
        menuMantCamiones.setVisible(false);
        menuEquiposOficina.setVisible(false);
        menuSensores.setVisible(false);
        menuSeguridad.setVisible(false);
        
        itemRegEquipo.setVisible(false);
        itemMantEquipo.setVisible(false);
        itemSoftware.setVisible(false);
        itemHistorialEquipos.setVisible(false);
        itemInventario.setVisible(false);

        
        switch (cargo) {
            case "Administrador General":
            case "administrador": 
                menuFlota.setVisible(true);
                menuMantCamiones.setVisible(true);
                menuEquiposOficina.setVisible(true);
                menuSensores.setVisible(true);
                menuSeguridad.setVisible(true);
                
                itemRegEquipo.setVisible(true);
                itemMantEquipo.setVisible(true);
                itemSoftware.setVisible(true);
                itemHistorialEquipos.setVisible(true);
                itemInventario.setVisible(true);
                break;
                
            case "Administrador IT":
                menuEquiposOficina.setVisible(true);
                menuSensores.setVisible(true);
                itemRegEquipo.setVisible(true);
                itemSoftware.setVisible(true);
                itemHistorialEquipos.setVisible(true);
                break;
                
            case "Técnico de soporte":
                menuEquiposOficina.setVisible(true);
                itemMantEquipo.setVisible(true);
                itemSoftware.setVisible(true);
                break;
                
            case "Administrador de mantenimiento":
                menuMantCamiones.setVisible(true);
                menuEquiposOficina.setVisible(true);
                menuSensores.setVisible(true);
                itemMantEquipo.setVisible(true);
                itemHistorialEquipos.setVisible(true);
                break;
                
            case "Administrador de inventario":
                menuEquiposOficina.setVisible(true);
                itemInventario.setVisible(true);
                break;
        }

        
        menuBar.add(menuFlota);
        menuBar.add(menuMantCamiones);
        menuBar.add(menuEquiposOficina);
        menuBar.add(menuSensores);
        menuBar.add(menuSeguridad);
        menuBar.add(menuSalir);

        menuBar.revalidate();
        menuBar.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        jButton1 = new javax.swing.JButton();
        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        bt_conductor = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        openMenuItem1 = new javax.swing.JMenuItem();
        openMenuItem2 = new javax.swing.JMenuItem();
        bt_conductor1 = new javax.swing.JMenu();
        bt_mantenimiento = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        openMenuItem3 = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bt_conductor.setMnemonic('f');
        bt_conductor.setText("Registro");
        bt_conductor.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N

        openMenuItem.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Registrar camion");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        bt_conductor.add(openMenuItem);

        openMenuItem1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        openMenuItem1.setMnemonic('o');
        openMenuItem1.setText("Registrar conductor");
        openMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItem1ActionPerformed(evt);
            }
        });
        bt_conductor.add(openMenuItem1);

        openMenuItem2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        openMenuItem2.setText("Volver");
        openMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItem2ActionPerformed(evt);
            }
        });
        bt_conductor.add(openMenuItem2);

        menuBar.add(bt_conductor);

        bt_conductor1.setMnemonic('f');
        bt_conductor1.setText("Mantenimiento");
        bt_conductor1.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N

        bt_mantenimiento.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        bt_mantenimiento.setMnemonic('o');
        bt_mantenimiento.setText("Hacer mantenimiento");
        bt_mantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_mantenimientoActionPerformed(evt);
            }
        });
        bt_conductor1.add(bt_mantenimiento);

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jMenuItem1.setText("Historial Mantenimiento");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        bt_conductor1.add(jMenuItem1);

        openMenuItem3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        openMenuItem3.setText("Volver");
        openMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItem3ActionPerformed(evt);
            }
        });
        bt_conductor1.add(openMenuItem3);

        menuBar.add(bt_conductor1);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed

    }//GEN-LAST:event_openMenuItemActionPerformed

    private void openMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItem1ActionPerformed

    }//GEN-LAST:event_openMenuItem1ActionPerformed


    private void bt_mantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_mantenimientoActionPerformed

    }//GEN-LAST:event_bt_mantenimientoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void openMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItem2ActionPerformed

    }//GEN-LAST:event_openMenuItem2ActionPerformed

    private void openMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItem3ActionPerformed

    }//GEN-LAST:event_openMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new Principal("Administrador General").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu bt_conductor;
    private javax.swing.JMenu bt_conductor1;
    private javax.swing.JMenuItem bt_mantenimiento;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem openMenuItem1;
    private javax.swing.JMenuItem openMenuItem2;
    private javax.swing.JMenuItem openMenuItem3;
    // End of variables declaration//GEN-END:variables

    private javax.swing.JMenu bt_equipos;
    private javax.swing.JMenuItem menuEquipos;
    private javax.swing.JMenuItem menuMantEquipo;
    private javax.swing.JMenuItem menuSoftware;
    private javax.swing.JMenuItem menuHistorialEquipos;
    private javax.swing.JMenuItem menuInventario;
}
