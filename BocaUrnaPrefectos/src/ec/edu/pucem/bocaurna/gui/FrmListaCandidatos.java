package ec.edu.pucem.bocaurna.gui;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ec.edu.pucem.bocaurna.models.Candidato;

import java.util.List;

public class FrmListaCandidatos extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private JTable table;
    private DefaultTableModel model;
    private List<Candidato> candidatos;

    public FrmListaCandidatos(List<Candidato> candidatos) {
        this.candidatos = candidatos;

        setTitle("Lista de prefectos");
        setClosable(true);
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Cédula", "Nombres", "Apellidos", "Partido", "Provincia"}
        );
        table.setModel(model);
        scrollPane.setViewportView(table);

        llenarTabla();
    }

    private void llenarTabla() {
        model.setRowCount(0);
        for (Candidato candidato : candidatos) {
            model.addRow(new Object[]{
                    candidato.getCedula(),
                    candidato.getNombre(),
                    candidato.getApellido(),
                    candidato.getPartido(),
                    candidato.getProvincia().getNombre()
            });
        }
    }
}

