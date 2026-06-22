/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/MDIApplication.java to edit this template
 */
package com.mycompany.transporteshirata.GUI;

<<<<<<< HEAD
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
=======
import com.mycompany.transporteshirata.GUI.GuiLoginGeneral;
import javax.swing.JFrame;

/**
 * Ventana principal de la aplicación Transportes Hirata. Muestra un escritorio
 * con menús según el rol del usuario (administrador, conductor o personal).
 * Permite acceder a todos los módulos del sistema: registro, mantenimiento,
 * inventario, etc.
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Constructor. Maximiza la ventana, establece el título y restringe las
     * opciones del menú según el cargo del usuario logueado (conductor o
     * personal).
     */
    public Principal() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("     \n     Principal     \n     ");
        if ("conductor".equals(GuiLoginGeneral.cargo_global)) {
            GuiConductor();
        }
        if ("personal".equals(GuiLoginGeneral.cargo_global)) {
            GuiPersonal();
        }
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
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
<<<<<<< HEAD
        bt_conductor = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        openMenuItem1 = new javax.swing.JMenuItem();
        openMenuItem2 = new javax.swing.JMenuItem();
        bt_conductor1 = new javax.swing.JMenu();
        bt_mantenimiento = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        openMenuItem3 = new javax.swing.JMenuItem();
=======
        bt_menu1 = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        openMenuItem1 = new javax.swing.JMenuItem();
        openMenuItem4 = new javax.swing.JMenuItem();
        openMenuItemInventarioPiezas = new javax.swing.JMenuItem();
        openMenuItemPersonal = new javax.swing.JMenuItem();
        bt_menu2 = new javax.swing.JMenu();
        bt_mantenimiento = new javax.swing.JMenuItem();
        bt_mantenimiento2 = new javax.swing.JMenuItem();
        jMenuItemActualizacionSoftware = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItemHistorialEquipos = new javax.swing.JMenuItem();
        bt_menu3 = new javax.swing.JMenu();
        bt_mantenimiento1 = new javax.swing.JMenuItem();
        bt_menu4 = new javax.swing.JMenu();
        openMenuItem5 = new javax.swing.JMenuItem();
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be

        jMenuItem2.setText("jMenuItem2");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

<<<<<<< HEAD
        bt_conductor.setMnemonic('f');
        bt_conductor.setText("Registro");
        bt_conductor.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
=======
        bt_menu1.setMnemonic('f');
        bt_menu1.setText("Registro");
        bt_menu1.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be

        openMenuItem.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Registrar camion");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
<<<<<<< HEAD
        bt_conductor.add(openMenuItem);
=======
        bt_menu1.add(openMenuItem);
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be

        openMenuItem1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        openMenuItem1.setMnemonic('o');
        openMenuItem1.setText("Registrar conductor");
        openMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItem1ActionPerformed(evt);
            }
        });
<<<<<<< HEAD
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
=======
        bt_menu1.add(openMenuItem1);

        openMenuItem4.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        openMenuItem4.setMnemonic('o');
        openMenuItem4.setText("Registrar equipo");
        openMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItem4ActionPerformed(evt);
            }
        });
        bt_menu1.add(openMenuItem4);

        openMenuItemInventarioPiezas.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        openMenuItemInventarioPiezas.setMnemonic('o');
        openMenuItemInventarioPiezas.setText("Inventario de piezas");
        openMenuItemInventarioPiezas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemInventarioPiezasActionPerformed(evt);
            }
        });
        bt_menu1.add(openMenuItemInventarioPiezas);

        openMenuItemPersonal.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        openMenuItemPersonal.setMnemonic('o');
        openMenuItemPersonal.setText("Registrar personal");
        openMenuItemPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemPersonalActionPerformed(evt);
            }
        });
        bt_menu1.add(openMenuItemPersonal);

        menuBar.add(bt_menu1);

        bt_menu2.setMnemonic('f');
        bt_menu2.setText("Mantenimiento");
        bt_menu2.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N

        bt_mantenimiento.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        bt_mantenimiento.setMnemonic('o');
        bt_mantenimiento.setText("Hacer mantenimiento camion");
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
        bt_mantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_mantenimientoActionPerformed(evt);
            }
        });
<<<<<<< HEAD
        bt_conductor1.add(bt_mantenimiento);
=======
        bt_menu2.add(bt_mantenimiento);

        bt_mantenimiento2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        bt_mantenimiento2.setMnemonic('o');
        bt_mantenimiento2.setText("Hacer mantenimiento equipo");
        bt_mantenimiento2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_mantenimiento2ActionPerformed(evt);
            }
        });
        bt_menu2.add(bt_mantenimiento2);

        jMenuItemActualizacionSoftware.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jMenuItemActualizacionSoftware.setText("Actualizar software de equipo");
        jMenuItemActualizacionSoftware.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemActualizacionSoftwareActionPerformed(evt);
            }
        });
        bt_menu2.add(jMenuItemActualizacionSoftware);
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jMenuItem1.setText("Historial Mantenimiento");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
<<<<<<< HEAD
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
=======
        bt_menu2.add(jMenuItem1);

        jMenuItemHistorialEquipos.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jMenuItemHistorialEquipos.setText("Historial mantenimiento de equipos");
        jMenuItemHistorialEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHistorialEquiposActionPerformed(evt);
            }
        });
        bt_menu2.add(jMenuItemHistorialEquipos);

        menuBar.add(bt_menu2);

        bt_menu3.setMnemonic('f');
        bt_menu3.setText("actualizar_kilometraje");
        bt_menu3.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N

        bt_mantenimiento1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        bt_mantenimiento1.setMnemonic('o');
        bt_mantenimiento1.setText("actualizar kilometraje de camion");
        bt_mantenimiento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_mantenimiento1ActionPerformed(evt);
            }
        });
        bt_menu3.add(bt_mantenimiento1);

        menuBar.add(bt_menu3);

        bt_menu4.setMnemonic('f');
        bt_menu4.setText("cerrar sesion");
        bt_menu4.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N

        openMenuItem5.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        openMenuItem5.setMnemonic('o');
        openMenuItem5.setText("cerrar sesion");
        openMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItem5ActionPerformed(evt);
            }
        });
        bt_menu4.add(openMenuItem5);

        menuBar.add(bt_menu4);
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
<<<<<<< HEAD
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
=======
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE)
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
<<<<<<< HEAD

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed

    }//GEN-LAST:event_openMenuItemActionPerformed

    private void openMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItem1ActionPerformed

    }//GEN-LAST:event_openMenuItem1ActionPerformed


    private void bt_mantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_mantenimientoActionPerformed

    }//GEN-LAST:event_bt_mantenimientoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

    }//GEN-LAST:event_jMenuItem1ActionPerformed

=======
    /**
     * Restringe la interfaz para usuarios con rol "conductor". Deshabilita los
     * menús de Registro y Mantenimiento.
     */
    private void GuiConductor() {
        bt_menu1.setEnabled(false);
        bt_menu2.setEnabled(false);
    }

    /**
     * Restringe la interfaz para usuarios con rol "personal". Deshabilita
     * partes del menú de Registro, Mantenimiento y actualización de
     * kilometraje.
     */
    private void GuiPersonal() {
        bt_menu1.setEnabled(false);
        bt_mantenimiento.setEnabled(false);
        jMenuItem1.setEnabled(false);
        jMenuItemHistorialEquipos.setEnabled(false);
        jMenuItemHistorialEquipos.setVisible(false);
        bt_menu3.setEnabled(false);
    }

    /**
     * Abre la ventana interna para registrar un camión.
     */
    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        GuiRegistrarCamion v = new GuiRegistrarCamion();
        desktopPane.add(v);
        v.setVisible(true);
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void openMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItem1ActionPerformed
        GuiRegistrarConductor c = new GuiRegistrarConductor();
        desktopPane.add(c);
        c.setVisible(true);
    }//GEN-LAST:event_openMenuItem1ActionPerformed

    private void bt_mantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_mantenimientoActionPerformed
        GuiMantenimiento m = new GuiMantenimiento();
        desktopPane.add(m);
        m.setVisible(true);
    }//GEN-LAST:event_bt_mantenimientoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        GuiHistorialMantenimiento hist = new GuiHistorialMantenimiento();
        desktopPane.add(hist);
        hist.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItemHistorialEquiposActionPerformed(java.awt.event.ActionEvent evt) {
        GuiHistorialMantenimientoEquipos histEquipos = new GuiHistorialMantenimientoEquipos();
        desktopPane.add(histEquipos);
        histEquipos.setVisible(true);
    }

>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

<<<<<<< HEAD
    private void openMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItem2ActionPerformed

    }//GEN-LAST:event_openMenuItem2ActionPerformed

    private void openMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItem3ActionPerformed

    }//GEN-LAST:event_openMenuItem3ActionPerformed
=======
    private void bt_mantenimiento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_mantenimiento1ActionPerformed
        GuiKilometraje pc = new GuiKilometraje();
        pc.setVisible(true);
        pc.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }//GEN-LAST:event_bt_mantenimiento1ActionPerformed

    private void openMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItem4ActionPerformed
        GuiRegistrarEquipo re = new GuiRegistrarEquipo();
        desktopPane.add(re);
        re.setVisible(true);
    }//GEN-LAST:event_openMenuItem4ActionPerformed

    private void openMenuItemInventarioPiezasActionPerformed(java.awt.event.ActionEvent evt) {
        GuiInventarioPiezas ip = new GuiInventarioPiezas();
        desktopPane.add(ip);
        ip.setVisible(true);
    }

    private void openMenuItemPersonalActionPerformed(java.awt.event.ActionEvent evt) {
        GuiRegistrarPersonal rp = new GuiRegistrarPersonal();
        desktopPane.add(rp);
        rp.setVisible(true);
    }

    private void openMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItem5ActionPerformed
        this.setVisible(false);
        GuiLoginGeneral p = new GuiLoginGeneral();
        p.setVisible(true);
    }//GEN-LAST:event_openMenuItem5ActionPerformed

    private void bt_mantenimiento2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_mantenimiento2ActionPerformed
        GuiMantenimientoEquipos me = new GuiMantenimientoEquipos();
        desktopPane.add(me);
        me.setVisible(true);
    }//GEN-LAST:event_bt_mantenimiento2ActionPerformed

    private void jMenuItemActualizacionSoftwareActionPerformed(java.awt.event.ActionEvent evt) {
        GuiActualizacionSoftware as = new GuiActualizacionSoftware();
        desktopPane.add(as);
        as.setVisible(true);
    }
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be

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
<<<<<<< HEAD
                
                new Principal("Administrador General").setVisible(true);
=======
                new Principal().setVisible(true);
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
<<<<<<< HEAD
    private javax.swing.JMenu bt_conductor;
    private javax.swing.JMenu bt_conductor1;
    private javax.swing.JMenuItem bt_mantenimiento;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenuItem jMenuItem1;
=======
    private javax.swing.JMenuItem bt_mantenimiento;
    private javax.swing.JMenuItem bt_mantenimiento1;
    private javax.swing.JMenuItem bt_mantenimiento2;
    private javax.swing.JMenu bt_menu1;
    private javax.swing.JMenu bt_menu2;
    private javax.swing.JMenu bt_menu3;
    private javax.swing.JMenu bt_menu4;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemActualizacionSoftware;
    private javax.swing.JMenuItem jMenuItemHistorialEquipos;
>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem openMenuItem1;
<<<<<<< HEAD
    private javax.swing.JMenuItem openMenuItem2;
    private javax.swing.JMenuItem openMenuItem3;
    // End of variables declaration//GEN-END:variables

    private javax.swing.JMenu bt_equipos;
    private javax.swing.JMenuItem menuEquipos;
    private javax.swing.JMenuItem menuMantEquipo;
    private javax.swing.JMenuItem menuSoftware;
    private javax.swing.JMenuItem menuHistorialEquipos;
    private javax.swing.JMenuItem menuInventario;
=======
    private javax.swing.JMenuItem openMenuItem4;
    private javax.swing.JMenuItem openMenuItem5;
    private javax.swing.JMenuItem openMenuItemInventarioPiezas;
    private javax.swing.JMenuItem openMenuItemPersonal;
    // End of variables declaration//GEN-END:variables

>>>>>>> 5fd7d53bd2d4321ed030986163cb7309bcd8a2be
}
