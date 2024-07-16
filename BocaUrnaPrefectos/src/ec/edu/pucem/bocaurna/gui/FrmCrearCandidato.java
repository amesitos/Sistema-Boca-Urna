package ec.edu.pucem.bocaurna.gui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import ec.edu.pucem.bocaurna.models.Candidato;
import ec.edu.pucem.bocaurna.models.Provincia;
import ec.edu.pucem.bocaurna.models.ProvinciaManager;
import java.util.List;
import javax.swing.JComboBox;
import java.awt.Font;


public class FrmCrearCandidato extends JInternalFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private List<Candidato> candidatos;

    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtPartido;

    private JTable table;
    private DefaultTableModel model;
    private JButton btnGuardar;
    private JButton btnNuevo;
    private JButton btnCancelar;
    private JComboBox<Provincia> comboProvincia;
   
    public FrmCrearCandidato(List<Candidato> candidatos) {
        this.candidatos = candidatos;

        setTitle("Registro de candidatos a prefecto");
        setBounds(100, 100, 450, 500);
        getContentPane().setLayout(null);

        JLabel lblCedula = new JLabel("Cédula");
        lblCedula.setBounds(21, 20, 69, 13);
        getContentPane().add(lblCedula);

        JLabel lblNombres = new JLabel("Nombres");
        lblNombres.setBounds(21, 43, 69, 13);
        getContentPane().add(lblNombres);

        JLabel lblApellidos = new JLabel("Apellidos");
        lblApellidos.setBounds(21, 66, 69, 13);
        getContentPane().add(lblApellidos);

        JLabel lblPartido = new JLabel("Partido");
        lblPartido.setBounds(21, 89, 69, 13);
        getContentPane().add(lblPartido);

        txtCedula = new JTextField();
        txtCedula.setBounds(100, 17, 198, 19);
        getContentPane().add(txtCedula);
        txtCedula.setColumns(10);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 40, 198, 19);
        getContentPane().add(txtNombre);
        txtNombre.setColumns(10);

        txtApellidos = new JTextField();
        txtApellidos.setBounds(100, 63, 198, 19);
        getContentPane().add(txtApellidos);
        txtApellidos.setColumns(10);

        txtPartido = new JTextField();
        txtPartido.setBounds(100, 86, 198, 19);
        getContentPane().add(txtPartido);
        txtPartido.setColumns(10);

        btnNuevo = new JButton("Nuevo");
        btnNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiar();
            }
        });
        btnNuevo.setBounds(100, 429, 107, 25);
        getContentPane().add(btnNuevo);

        btnGuardar = new JButton("Registrar candidato");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarCandidato();
            }
        });
        btnGuardar.setBounds(250, 115, 160, 25);
        getContentPane().add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancelar.setBounds(233, 429, 107, 25);
        getContentPane().add(btnCancelar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(21, 192, 394, 227);
        getContentPane().add(scrollPane);

        table = new JTable();
        model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Cédula", "Nombres", "Apellidos", "Partido", "Provincia"}
        );
        table.setModel(model);
        scrollPane.setViewportView(table);

        JLabel lblProvincia = new JLabel("Provincia");
        lblProvincia.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblProvincia.setBounds(21, 158, 70, 15);
        getContentPane().add(lblProvincia);

        comboProvincia = new JComboBox<>(ProvinciaManager.getProvincias());
        comboProvincia.insertItemAt(new Provincia("Selecciona una provincia"), 0);
        comboProvincia.setSelectedIndex(0);
        comboProvincia.setBounds(100, 154, 130, 24);
        getContentPane().add(comboProvincia);

        agregarFilasTabla();
    }

    private void limpiar() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtPartido.setText("");
        comboProvincia.setSelectedIndex(0);
    }

    private void guardarCandidato() {
        String cedula = txtCedula.getText();
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String partido = txtPartido.getText();
        Provincia provincia = (Provincia) comboProvincia.getSelectedItem();

        if (cedula.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || partido.isEmpty() || provincia == null || provincia.getNombre().equals("Selecciona una provincia")) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, complete todos los campos.",
                    "Campos incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Candidato nuevoCandidato = new Candidato(cedula, nombre, apellidos, partido, provincia);
        candidatos.add(nuevoCandidato);

        agregarFilaTabla(nuevoCandidato);

        limpiar();
    }

    private void agregarFilasTabla() {
        model.setRowCount(0);
        for (Candidato candidato : candidatos) {
            agregarFilaTabla(candidato);
        }
    }

    private void agregarFilaTabla(Candidato candidato) {
        model.addRow(new Object[]{
                candidato.getCedula(),
                candidato.getNombre(),
                candidato.getApellido(),
                candidato.getPartido(),
                candidato.getProvincia().getNombre()
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}

