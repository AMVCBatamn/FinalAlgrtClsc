
package visual;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Grafo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetallesMtx extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JRadioButton rdbtnSi;
    private JTable table;
    private JRadioButton rdbtnNo;
    private DefaultTableModel model;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            // Crear una instancia de Grafo
            Grafo grafo = Grafo.getInstance();
            DetallesMtx dialog = new DetallesMtx(grafo);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public DetallesMtx(Grafo grafo) {
        setBounds(100, 100, 450, 351);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);

        // Obtener la matriz de adyacencia usando el algoritmo de Floyd-Warshall
        int[][] matrizAdyacencia = grafo.calcularFloydWarchall();

        // Crear el modelo de tabla
        model = new DefaultTableModel(matrizAdyacencia.length, matrizAdyacencia[0].length);
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            for (int j = 0; j < matrizAdyacencia[0].length; j++) {
                model.setValueAt(matrizAdyacencia[i][j], i, j);
            }
        }
        table.setModel(model);

        JPanel panel_1 = new JPanel();
        contentPanel.add(panel_1, BorderLayout.NORTH);
        panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        JLabel lblNewLabel = new JLabel("Ver peso ?");
        panel_1.add(lblNewLabel);

        rdbtnSi = new JRadioButton("Si");
        rdbtnSi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rdbtnSi.setSelected(true);
                rdbtnNo.setSelected(false);
            }
        });
        rdbtnSi.setSelected(true);
        panel_1.add(rdbtnSi);

        rdbtnNo = new JRadioButton("No");
        rdbtnNo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rdbtnSi.setSelected(false);
                rdbtnNo.setSelected(true);
            }
        });
        panel_1.add(rdbtnNo);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }
}
