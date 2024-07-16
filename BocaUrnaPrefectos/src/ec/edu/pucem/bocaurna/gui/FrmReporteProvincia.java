package ec.edu.pucem.bocaurna.gui;

import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import ec.edu.pucem.bocaurna.models.Candidato;
import ec.edu.pucem.bocaurna.models.DatosVotacion;
import ec.edu.pucem.bocaurna.models.Provincia;
import ec.edu.pucem.bocaurna.models.ProvinciaManager;
import ec.edu.pucem.bocaurna.models.Voto;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;


public class FrmReporteProvincia extends JInternalFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
	private List<Candidato> prefectos;
    private JButton btnCancelar;
    private JLabel lblNombres;
    private JComboBox<Provincia> comboBox;
    private JTable table;
    private DefaultTableModel model;

    public FrmReporteProvincia(List<Candidato> prefectos) {
        this.prefectos = prefectos;
        
        setTitle("REPORTE GENERAL POR PROVINCIA");
        setBounds(100, 100, 600, 309);
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 48, 566, 167);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Apellidos", "Partido", "Votos" }));
        scrollPane.setViewportView(table);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancelar.setBounds(234, 227, 117, 25);
        getContentPane().add(btnCancelar);
        
        lblNombres = new JLabel("Provincia");
        lblNombres.setBounds(12, 21, 70, 15);
        getContentPane().add(lblNombres);
        
        comboBox = new JComboBox<>(ProvinciaManager.getProvincias());
        comboBox.insertItemAt(new Provincia("Seleccione una provincia"), 0);
        comboBox.setSelectedIndex(0);
        comboBox.setBounds(79, 12, 231, 24);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                llenarTabla();
            }
        });
        getContentPane().add(comboBox);

        model = (DefaultTableModel) table.getModel();
        llenarTabla();
    }

    private void llenarTabla() {
        Provincia provinciaSeleccionada = (Provincia) comboBox.getSelectedItem();
        if (provinciaSeleccionada == null || provinciaSeleccionada.getNombre().equals("Seleccione una provincia")) {
            model.setRowCount(0);
            return;
        }

        model.setRowCount(0);
        for (Candidato candidato : prefectos) {
            if (candidato.getProvincia().getNombre().equals(provinciaSeleccionada.getNombre())) {
                int votosCount = contarVotosPorCandidato(candidato);
                Object[] fila = new Object[4];
                fila[0] = candidato.getNombre();
                fila[1] = candidato.getApellido();
                fila[2] = candidato.getPartido();
                fila[3] = votosCount;
                model.addRow(fila);
            }
        }
    }

    private int contarVotosPorCandidato(Candidato candidato) {
        int count = 0;
        List<Voto> votos = DatosVotacion.getVotos(); // Obtener la lista de votos actualizada
        for (Voto voto : votos) {
            if (voto.getCandidato().equals(candidato)) {
                count++;
            }
        }
        return count;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

