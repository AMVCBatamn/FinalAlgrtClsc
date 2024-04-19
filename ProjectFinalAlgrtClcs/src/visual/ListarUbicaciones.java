package visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import logico.Grafo;
import logico.Nodo;
import java.awt.*;
import java.util.ArrayList;

public class ListarUbicaciones extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel model;
    private Grafo grafo; // referencia al grafo donde se encuentran los nodos/
    private JButton btnEliminar;
    private JButton btnModificar;

    /**
     * Create the dialog.
     */
    public ListarUbicaciones() {
        setTitle("Lista de Ubicaciones");
        setBounds(100, 100, 575, 488);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        model = new DefaultTableModel();
        String[] header = {"Codigo", "Valor", "Ubicacion", "Longitud", "Latitud"};
        model.setColumnIdentifiers(header);

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        btnModificar = new JButton("Modificar");
        buttonPane.add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        buttonPane.add(btnEliminar);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);

        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = table.getSelectedRow();
            if (filaSeleccionada != -1) {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar esta ubicación ?", "Eliminar Ubicación", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    int codigoNodo = (int) table.getValueAt(filaSeleccionada, 0);
                    Nodo nodoEliminar = null;
                    for (Nodo nodo : grafo.getMisNodos()) {
                        if (nodo.getCodigo() == codigoNodo) {
                            nodoEliminar = nodo;
                            break;
                        }
                    }
                    if (nodoEliminar != null) {
                        grafo.eliminarNodo(nodoEliminar);
                        actualizarTabla();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una ubicación para eliminar.", "Eliminar Ubicación", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        btnModificar.addActionListener(e -> {
            int filaSeleccionada = table.getSelectedRow();
            if (filaSeleccionada != -1) {
                int codigoNodo = Integer.parseInt(table.getValueAt(filaSeleccionada, 0).toString().substring(5));
                Nodo nodoModificar = null;
                for (Nodo nodo : grafo.getMisNodos()) {
                    if (nodo.getCodigo() == codigoNodo) {
                        nodoModificar = nodo;
                        break;
                    }
                }
                if (nodoModificar != null) {
                    ModificarUbicacion modificarNodo = new ModificarUbicacion();
                    modificarNodo.setGrafo(grafo);
                    modificarNodo.setNodo(nodoModificar);
                    modificarNodo.setModal(true); 
                    modificarNodo.setVisible(true);
                    actualizarTabla();

                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una ubicación para modificar.", "Modificar Ubicación", JOptionPane.WARNING_MESSAGE);
            }
        });

        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
        actualizarTabla();
    }

    private void actualizarTabla() {
        if (grafo != null && model != null) {
            model.setRowCount(0);

            ArrayList<Nodo> nodos = grafo.getMisNodos();

            for (Nodo nodo : nodos) {
                Object[] rowData = {
                		"Ubic-"+nodo.getCodigo(),
                        nodo.getValor(),
                        nodo.getNombreUbicacion(),
                        nodo.getLonguitud(),
                        nodo.getLatitud()
                };
                model.addRow(rowData);
            }
        }
    }
}
