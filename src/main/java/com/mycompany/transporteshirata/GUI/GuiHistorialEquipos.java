package com.mycompany.transporteshirata.GUI;

import com.mycompany.transporteshirata.Datos.ActualizacionSoftwareDao;
import com.mycompany.transporteshirata.Datos.EquipoOficinaDao;
import com.mycompany.transporteshirata.Datos.MantenimientoEquipoDao;
import com.mycompany.transporteshirata.Logica.ActualizacionSoftware;
import com.mycompany.transporteshirata.Logica.EquipoOficina;
import com.mycompany.transporteshirata.Logica.MantenimientoEquipo;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Formulario Integral de Trazabilidad de Activos.
 * Agrupa las auditorías de Hardware  y Software  en una vista unificada mediante pestañas.
 * Carga todo el panorama corporativo por defecto y permite filtrado específico.
 */
public class GuiHistorialEquipos extends javax.swing.JInternalFrame {

    private EquipoOficinaDao daoEq = new EquipoOficinaDao();
    private MantenimientoEquipoDao daoMant = new MantenimientoEquipoDao();
    private ActualizacionSoftwareDao daoSoft = new ActualizacionSoftwareDao();

    private JComboBox<EquipoOficina> cmbEquipo = new JComboBox<>();
    private JButton btnBuscar = new JButton("Filtrar Historial");

    private JTabbedPane panelPestanas = new JTabbedPane();
    
    
    private DefaultTableModel modeloMant;
    private JTable tablaMant;
    
    
    private DefaultTableModel modeloSoft;
    private JTable tablaSoft;

    public GuiHistorialEquipos() {
        setTitle("Bitácora Integral de Equipos (Hardware & Software)");
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(1050, 600);

        cargarEquipos();
        inicializarUI();
        configurarEventos();
        
        
        buscarHistoriales(); 
    }

    private void cargarEquipos() {
        List<EquipoOficina> equipos = daoEq.listarEquipos();
        cmbEquipo.removeAllItems();
        
        
        EquipoOficina todos = new EquipoOficina();
        todos.setIdEquipo(0);
        todos.setNombre("-- Todos los Equipos --");
        cmbEquipo.addItem(todos);

        for (EquipoOficina eq : equipos) {
            cmbEquipo.addItem(eq);
        }
    }

    private void inicializarUI() {
        setLayout(new BorderLayout(10, 10));

        
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelTop.setBorder(BorderFactory.createTitledBorder("Herramienta de Auditoría y Filtrado"));
        panelTop.add(new JLabel("Seleccione Activo:"));
        cmbEquipo.setPreferredSize(new Dimension(300, 30));
        panelTop.add(cmbEquipo);
        
        btnBuscar.setBackground(new Color(0, 120, 215));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setPreferredSize(new Dimension(150, 30));
        panelTop.add(btnBuscar);

        add(panelTop, BorderLayout.NORTH);

        
        panelPestanas.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // --- Pestaña 1: Hardware ---
        modeloMant = new DefaultTableModel(
            new String[]{"ID", "Equipo", "Fecha", "Tipo", "Técnico", "Descripción", "Observaciones (Checklist)"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaMant = new JTable(modeloMant);
        tablaMant.getColumnModel().getColumn(0).setPreferredWidth(40);
        tablaMant.getColumnModel().getColumn(1).setPreferredWidth(120); 
        tablaMant.getColumnModel().getColumn(6).setPreferredWidth(250); 
        JScrollPane scrollMant = new JScrollPane(tablaMant);
        panelPestanas.addTab(" Historial de Mantenimiento Físico ", scrollMant);

        // --- Pestaña 2: Software ---
        modeloSoft = new DefaultTableModel(
            new String[]{"ID", "Equipo", "Fecha", "Software", "V. Anterior", "V. Nueva", "Responsable IT"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaSoft = new JTable(modeloSoft);
        tablaSoft.getColumnModel().getColumn(0).setPreferredWidth(40);
        tablaSoft.getColumnModel().getColumn(1).setPreferredWidth(120); 
        JScrollPane scrollSoft = new JScrollPane(tablaSoft);
        panelPestanas.addTab(" Historial de Actualizaciones de Software ", scrollSoft);

        add(panelPestanas, BorderLayout.CENTER);
    }

    private void configurarEventos() {
        btnBuscar.addActionListener(e -> buscarHistoriales());
    }

    /**
     * Recupera el identificador del equipo seleccionado y dispara las consultas.
     * Si el ID es 0 (Todos los equipos), carga la bitácora completa de la empresa.
     */
    private void buscarHistoriales() {
        EquipoOficina eq = (EquipoOficina) cmbEquipo.getSelectedItem();
        if (eq == null) {
            return;
        }

        int idEquipo = eq.getIdEquipo();
        List<MantenimientoEquipo> listaMant;
        List<ActualizacionSoftware> listaSoft;

      
        if (idEquipo == 0) {
            listaMant = daoMant.listarMantenimientos();
            listaSoft = daoSoft.listarActualizaciones();
        } else {
            listaMant = daoMant.listarPorEquipo(idEquipo);
            listaSoft = daoSoft.listarPorEquipo(idEquipo);
        }

    
        modeloMant.setRowCount(0);
        for (MantenimientoEquipo m : listaMant) {
            modeloMant.addRow(new Object[]{
                m.getIdMantenimientoEquipo(),
                m.getEquipo() != null ? m.getEquipo().getNombre() : "-",
                m.getFecha(),
                m.getTipo(),
                m.getTecnico(),
                m.getDescripcion(),
                m.getObservaciones()
            });
        }

       
        modeloSoft.setRowCount(0);
        for (ActualizacionSoftware a : listaSoft) {
            modeloSoft.addRow(new Object[]{
                a.getIdActualizacion(),
                a.getEquipo() != null ? a.getEquipo().getNombre() : "-",
                a.getFechaActualizacion(),
                a.getNombreSoftware(),
                a.getVersionAnterior(),
                a.getVersionNueva(),
                a.getResponsable()
            });
        }
        
        
        if (idEquipo != 0 && listaMant.isEmpty() && listaSoft.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El equipo seleccionado aún no cuenta con registros históricos en la base de datos.", "Auditoría en Blanco", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}