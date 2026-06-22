package com.mycompany.transporteshirata.GUI;

import com.mycompany.transporteshirata.Datos.UsuarioDao;
import com.mycompany.transporteshirata.Logica.Usuario;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Formulario de interfaz gráfica para la administración de cuentas y roles del sistema.
 */
public class GuiGestionUsuarios extends javax.swing.JInternalFrame {

    private UsuarioDao dao = new UsuarioDao();
    private DefaultTableModel modeloTabla;

    private JTextField txtRut = new JTextField(15);
    
    private JPasswordField txtClave = new JPasswordField(15);
    private JCheckBox chkMostrarClave = new JCheckBox("Mostrar"); 
    
    private JComboBox<String> cmbCargo = new JComboBox<>(new String[]{
        "Administrador General", 
        "Administrador IT", 
        "Técnico de soporte", 
        "Administrador de mantenimiento", 
        "Administrador de inventario", 
        "Conductor"
    });

    private JButton btnRegistrar = new JButton("Registrar");
    private JButton btnModificar = new JButton("Modificar");
    private JButton btnEliminar = new JButton("Eliminar");
    private JButton btnLimpiar = new JButton("Limpiar");

    private JTable tabla;

    public GuiGestionUsuarios() {
        setTitle("Gestión de Usuarios y Roles del Sistema");
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(780, 480);

        inicializarUI();
        cargarTabla(dao.listarUsuarios());
        configurarEventos();
    }

    private void inicializarUI() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos de la Cuenta"));
        panelForm.setPreferredSize(new Dimension(360, 0)); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        agregarFila(panelForm, gbc, 0, "RUT:", txtRut);
        
        
        JPanel panelClave = new JPanel(new BorderLayout(5, 0));
        panelClave.add(txtClave, BorderLayout.CENTER);
        panelClave.add(chkMostrarClave, BorderLayout.EAST);
        agregarFila(panelForm, gbc, 1, "Clave Acceso:", panelClave);
        
        agregarFila(panelForm, gbc, 2, "Rol / Cargo:", cmbCargo);

        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 2; gbc.insets = new Insets(20, 5, 5, 5);
        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 8, 8));
        
        btnRegistrar.setBackground(new Color(0, 120, 215));
        btnRegistrar.setForeground(Color.WHITE);
        btnModificar.setBackground(new Color(220, 130, 0));
        btnModificar.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(180, 50, 50));
        btnEliminar.setForeground(Color.WHITE);
        
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        panelForm.add(panelBotones, gbc);

        gbc.gridy = 4;
        gbc.weighty = 1.0;
        panelForm.add(Box.createVerticalGlue(), gbc);

        JPanel panelTabla = new JPanel(new BorderLayout(5, 5));
        panelTabla.setBorder(BorderFactory.createTitledBorder("Cuentas Registradas"));

        modeloTabla = new DefaultTableModel(new String[]{"ID", "RUT", "Clave", "Cargo/Rol"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        panelTabla.add(new JScrollPane(tabla), BorderLayout.CENTER);

        add(panelForm, BorderLayout.WEST);
        add(panelTabla, BorderLayout.CENTER);
    }

    private void agregarFila(JPanel panel, GridBagConstraints gbc, int fila, String label, JComponent campo) {
        gbc.gridy = fila; gbc.gridx = 0; gbc.gridwidth = 1; gbc.weightx = 0;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        panel.add(campo, gbc);
    }

    private void configurarEventos() {
        btnRegistrar.addActionListener(e -> registrar());
        btnModificar.addActionListener(e -> modificar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        
        chkMostrarClave.addActionListener(e -> {
            if (chkMostrarClave.isSelected()) {
                txtClave.setEchoChar((char) 0); 
            } else {
                txtClave.setEchoChar('\u2022'); 
            }
        });

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    txtRut.setText(modeloTabla.getValueAt(fila, 1).toString());
                    txtClave.setText(modeloTabla.getValueAt(fila, 2).toString());
                    cmbCargo.setSelectedItem(modeloTabla.getValueAt(fila, 3).toString());
                }
            }
        });
    }

    private void registrar() {
        String rutIngresado = txtRut.getText().trim();
        String claveIngresada = new String(txtClave.getPassword()).trim(); 

        if (rutIngresado.isEmpty() || claveIngresada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "RUT y Clave son obligatorios.");
            return;
        }

        if (!validarRutChileno(rutIngresado)) {
            JOptionPane.showMessageDialog(this, "El RUT ingresado no es válido.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario u = new Usuario(rutIngresado, claveIngresada, cmbCargo.getSelectedItem().toString());
        if (dao.registrarUsuario(u)) {
            JOptionPane.showMessageDialog(this, "Cuenta registrada correctamente.");
            limpiarFormulario();
            cargarTabla(dao.listarUsuarios());
        }
    }

    private void modificar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario de la tabla para modificar.");
            return;
        }
        
        String rutIngresado = txtRut.getText().trim();
        String claveIngresada = new String(txtClave.getPassword()).trim();

        if (rutIngresado.isEmpty() || claveIngresada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "RUT y Clave son obligatorios.");
            return;
        }

        if (!validarRutChileno(rutIngresado)) {
            JOptionPane.showMessageDialog(this, "El RUT ingresado no es válido.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
            Usuario u = new Usuario(rutIngresado, claveIngresada, cmbCargo.getSelectedItem().toString());
            u.setIdUsuario(id);
            
            if (dao.modificarUsuario(u)) {
                JOptionPane.showMessageDialog(this, "Cuenta actualizada exitosamente.");
                limpiarFormulario();
                cargarTabla(dao.listarUsuarios());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al leer el identificador del usuario.");
        }
    }

    private void eliminar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar.");
            return;
        }
        try {
            int id = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
            int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar cuenta de acceso?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (dao.eliminarUsuario(id)) {
                    JOptionPane.showMessageDialog(this, "Cuenta eliminada.");
                    limpiarFormulario();
                    cargarTabla(dao.listarUsuarios());
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error de sistema.");
        }
    }

    private void cargarTabla(List<Usuario> lista) {
        modeloTabla.setRowCount(0);
        for (Usuario u : lista) {
            modeloTabla.addRow(new Object[]{u.getIdUsuario(), u.getRut(), u.getClave(), u.getCargo()});
        }
    }

    private void limpiarFormulario() {
        txtRut.setText("");
        txtClave.setText("");
        cmbCargo.setSelectedIndex(0);
        chkMostrarClave.setSelected(false);
        txtClave.setEchoChar('\u2022'); 
        tabla.clearSelection();
    }

    /**
     * Valida matemáticamente un RUT chileno (Algoritmo Módulo 11).
     */
    private boolean validarRutChileno(String rut) {
        boolean validacion = false;
        try {
            rut = rut.toUpperCase().replace(".", "").replace("-", "");
            if (rut.length() < 8) {
                return false; 
            }
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
            char dv = rut.charAt(rut.length() - 1);
            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }
        } catch (Exception e) {
            return false;
        }
        return validacion;
    }
}