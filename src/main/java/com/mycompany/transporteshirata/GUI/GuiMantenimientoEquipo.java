package com.mycompany.transporteshirata.GUI;

import com.mycompany.transporteshirata.Datos.EquipoOficinaDao;
import com.mycompany.transporteshirata.Datos.MantenimientoEquipoDao;
import com.mycompany.transporteshirata.Datos.PiezaRepuestoDao;
import com.mycompany.transporteshirata.Logica.EquipoOficina;
import com.mycompany.transporteshirata.Logica.MantenimientoEquipo;
import com.mycompany.transporteshirata.Logica.PiezaRepuesto;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Interfaz Gráfica de Usuario para el Registro y Control de Mantenimiento de
 * Equipos. Satisface los requerimientos del caso vinculados al módulo del
 * sistema. Permite documentar auditorías técnicas mediante listas de chequeo y
 * asociar consumos de stock.
 */
public class GuiMantenimientoEquipo extends javax.swing.JInternalFrame {
    
    private MantenimientoEquipoDao dao = new MantenimientoEquipoDao();
    private EquipoOficinaDao daoEq = new EquipoOficinaDao();
    private PiezaRepuestoDao daoPieza = new PiezaRepuestoDao();
    private DefaultTableModel modeloTabla;

    private JComboBox<EquipoOficina> cmbEquipo = new JComboBox<>();
    private JComboBox<String> cmbTipo = new JComboBox<>(new String[]{"Preventivo", "Correctivo"});
    private JTextField txtFecha = new JTextField(LocalDate.now().toString());
    private JTextField txtTecnico = new JTextField(20);
    private JTextArea txtDescripcion = new JTextArea(3, 20);

    private JComboBox<PiezaRepuesto> cmbPieza = new JComboBox<>();
    private JTextField txtCantidadPieza = new JTextField("0");

    private JCheckBox chkLimpieza = new JCheckBox("Limpieza Física");
    private JCheckBox chkHardware = new JCheckBox("Revisión Hardware");
    private JCheckBox chkSoftware = new JCheckBox("Revisión Software");
    private JCheckBox chkPiezas = new JCheckBox("Reemplazo Piezas");

    private JTextArea txtObservaciones = new JTextArea(3, 20);

    private JButton btnRegistrar = new JButton("Registrar");
    private JButton btnLimpiar = new JButton("Limpiar");
    private JButton btnActualizar = new JButton("Actualizar lista");

    private JTable tabla;

    /**
     * Constructor de la clase GuiMantenimientoEquipo. Establece las propiedades
     * de redimensión del frame interno y gatilla la precarga de datos
     * relacionales.
     */
    public GuiMantenimientoEquipo() {
        txtTecnico.setText(GuiLoginGeneral.rut_global);
        txtTecnico.setEditable(false);
        setTitle("Mantenimiento de Equipos de Oficina");
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(950, 680);

        cargarEquiposEnCombo();
        cargarPiezasEnCombo();
        inicializarUI();
        cargarTabla(dao.listarMantenimientos());
        configurarEventos();
    }

    /**
     * Consulta los registros de equipamiento activos y los vuelca en el
     * componente ComboBox de selección.
     */
    private void cargarEquiposEnCombo() {
        List<EquipoOficina> equipos = daoEq.listarEquipos();
        cmbEquipo.removeAllItems();
        for (EquipoOficina eq : equipos) {
            cmbEquipo.addItem(eq);
        }
    }

    /**
     * Consulta el inventariado de repuestos físicos poblando el ComboBox
     * correspondiente. Incorpora de forma estática una entidad nula para
     * operaciones que no demanden insumos.
     */
    private void cargarPiezasEnCombo() {
        List<PiezaRepuesto> piezas = daoPieza.listarPiezas();
        cmbPieza.removeAllItems();

        PiezaRepuesto pNula = new PiezaRepuesto();
        pNula.setIdPieza(0);
        pNula.setNombre("Ninguna / No aplica");
        cmbPieza.addItem(pNula);

        for (PiezaRepuesto p : piezas) {
            cmbPieza.addItem(p);
        }
    }

    /**
     * Construye la distribución estructural del panel de control previniendo el
     * colapso vertical de componentes.
     */
    private void inicializarUI() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Registrar Mantenimiento"));
        panelForm.setPreferredSize(new Dimension(420, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        agregarFila(panelForm, gbc, 0, "Equipo:", cmbEquipo);
        agregarFila(panelForm, gbc, 1, "Tipo:", cmbTipo);
        agregarFila(panelForm, gbc, 2, "Fecha (Y-M-D):", txtFecha);
        agregarFila(panelForm, gbc, 3, "Técnico:", txtTecnico);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        panelForm.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.2;
        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
        panelForm.add(scrollDesc, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JPanel panelPiezas = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
        panelPiezas.setBorder(BorderFactory.createTitledBorder("Repuesto Utilizado"));
        cmbPieza.setPreferredSize(new Dimension(220, 25));
        panelPiezas.add(cmbPieza);
        panelPiezas.add(new JLabel("Cant:"));
        txtCantidadPieza.setPreferredSize(new Dimension(40, 25));
        panelPiezas.add(txtCantidadPieza);
        panelForm.add(panelPiezas, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JPanel panelChecklist = new JPanel(new GridLayout(2, 2, 5, 5));
        panelChecklist.setBorder(BorderFactory.createTitledBorder("Lista de Chequeo"));
        panelChecklist.add(chkLimpieza);
        panelChecklist.add(chkHardware);
        panelChecklist.add(chkSoftware);
        panelChecklist.add(chkPiezas);
        panelForm.add(panelChecklist, gbc);

        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        panelForm.add(new JLabel("Observaciones:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.2;
        JScrollPane scrollObs = new JScrollPane(txtObservaciones);
        panelForm.add(scrollObs, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;

        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        btnRegistrar.setBackground(new Color(0, 160, 80));
        btnRegistrar.setForeground(Color.WHITE);
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnLimpiar);
        panelForm.add(panelBotones, gbc);

        gbc.gridy = 9;
        gbc.weighty = 1.0;
        panelForm.add(Box.createVerticalGlue(), gbc);

        JPanel panelTabla = new JPanel(new BorderLayout(5, 5));
        panelTabla.setBorder(BorderFactory.createTitledBorder("Mantenimientos Registrados"));

        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelTop.add(btnActualizar);
        panelTabla.add(panelTop, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(
                new String[]{"ID", "Equipo", "Tipo", "Fecha", "Técnico", "Descripción", "Observaciones"}, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(150);
        panelTabla.add(new JScrollPane(tabla), BorderLayout.CENTER);

        add(panelForm, BorderLayout.WEST);
        add(panelTabla, BorderLayout.CENTER);
    }

    /**
     * Agrega un conjunto de etiqueta e input alineados al panel con formato
     * GridBagLayout.
     *
     * @param panel Panel contenedor principal.
     * @param gbc Restricciones de diseño GridBagConstraints.
     * @param fila Número de fila en la cuadrícula.
     * @param label Texto descriptivo de la etiqueta.
     * @param campo Componente interactivo a insertar.
     */
    private void agregarFila(JPanel panel, GridBagConstraints gbc, int fila, String label, JComponent campo) {
        gbc.gridy = fila;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(campo, gbc);
    }

    /**
     * Enlaza los métodos operativos de negocio a los disparadores de acción de
     * los botones.
     */
    private void configurarEventos() {
        btnRegistrar.addActionListener(e -> registrar());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnActualizar.addActionListener(e -> cargarTabla(dao.listarMantenimientos()));
    }

    /**
     * Valida la consistencia de los datos del formulario, compila la lista de
     * chequeo y ejecuta el registro transaccional delegando el descuento de
     * existencias al DAO.
     */
    private void registrar() {
        EquipoOficina eq = (EquipoOficina) cmbEquipo.getSelectedItem();
        if (eq == null) {
            JOptionPane.showMessageDialog(this, "Registre un equipo primero.");
            return;
        }

        LocalDate fecha;
        try {
            fecha = LocalDate.parse(txtFecha.getText().trim());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use YYYY-MM-DD.");
            return;
        }

        PiezaRepuesto piezaSel = (PiezaRepuesto) cmbPieza.getSelectedItem();
        int idPieza = (piezaSel != null) ? piezaSel.getIdPieza() : 0;
        int cantidad = 0;

        if (idPieza > 0) {
            try {
                cantidad = Integer.parseInt(txtCantidadPieza.getText().trim());
                if (cantidad <= 0) {
                    throw new Exception();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Si selecciona una pieza, la cantidad debe ser mayor a 0.");
                return;
            }
            if (piezaSel.getStock() < cantidad) {
                JOptionPane.showMessageDialog(this, "Stock insuficiente. Solo hay " + piezaSel.getStock() + " disponibles.");
                return;
            }
        }

        String checklist = "\n[Checklist]: "
                + (chkLimpieza.isSelected() ? "✓ Limpieza " : "✗ Limpieza ") + "| "
                + (chkHardware.isSelected() ? "✓ Hardware " : "✗ Hardware ") + "| "
                + (chkSoftware.isSelected() ? "✓ Software " : "✗ Software ") + "| "
                + (chkPiezas.isSelected() ? "✓ Piezas " : "✗ Piezas ");

        String observacionesFinales = txtObservaciones.getText().trim() + checklist;

        MantenimientoEquipo m = new MantenimientoEquipo();
        m.setEquipo(eq);
        m.setFecha(fecha);
        m.setTipo(cmbTipo.getSelectedItem().toString());
        m.setDescripcion(txtDescripcion.getText().trim());
        m.setTecnico(txtTecnico.getText().trim());
        m.setObservaciones(observacionesFinales);

        if (dao.registrarMantenimiento(m, idPieza, cantidad)) {
            JOptionPane.showMessageDialog(this, "Mantenimiento registrado correctamente.");
            limpiarFormulario();
            cargarTabla(dao.listarMantenimientos());
            cargarPiezasEnCombo();
        }
    }

    /**
     * Despliega la colección de datos históricos procesados dentro del
     * componente visual de tabla.
     *
     * @param lista Colección de entidades de tipo {@link MantenimientoEquipo}.
     */
    private void cargarTabla(List<MantenimientoEquipo> lista) {
        modeloTabla.setRowCount(0);
        for (MantenimientoEquipo m : lista) {
            modeloTabla.addRow(new Object[]{
                m.getIdMantenimientoEquipo(),
                m.getEquipo() != null ? m.getEquipo().getNombre() : "-",
                m.getTipo(),
                m.getFecha(),
                m.getTecnico(),
                m.getDescripcion(),
                m.getObservaciones()
            });
        }
    }

    /**
     * Limpia y restablece los selectores, cajas de texto y casillas de
     * verificación a su estado inicial.
     */
    private void limpiarFormulario() {
        txtFecha.setText(LocalDate.now().toString());

        txtTecnico.setText(GuiLoginGeneral.rut_global);
        txtTecnico.setEditable(false);

        txtDescripcion.setText("");
        txtObservaciones.setText("");
        cmbTipo.setSelectedIndex(0);
        cmbPieza.setSelectedIndex(0);
        txtCantidadPieza.setText("0");
        chkLimpieza.setSelected(false);
        chkHardware.setSelected(false);
        chkSoftware.setSelected(false);
        chkPiezas.setSelected(false);
    }
}
