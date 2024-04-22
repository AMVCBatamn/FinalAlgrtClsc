package visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import logico.Grafo;
import logico.Nodo;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

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
    	grafo = Grafo.getInstance();
        setTitle("Lista de Ubicaciones");
        setBounds(100, 100, 575, 488);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        model = new DefaultTableModel();
        String[] header = {"Codigo", "Valor", "Ubicacion", "Longitud", "Latitud"};
        model.setColumnIdentifiers(header);

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        Font font = new Font("Verdana", Font.BOLD, 11);
        table.setFont(font);
        table.getTableHeader().setFont(font);

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
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
        	}
        });
        buttonPane.add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar esta ubicación ?", "Eliminar Ubicación", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION) {
                        int codigoNodo = Integer.parseInt(table.getValueAt(filaSeleccionada, 0).toString().substring(5));
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
        	}
        });
        buttonPane.add(btnEliminar);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);

        setLocationRelativeTo(null);
        setResizable(false);
        actualizarTabla();
    }

    private void actualizarTabla() {
        Object[] rows;
        rows = new Object[model.getColumnCount()];
        model.setRowCount(0);

        if (grafo != null) {
            ArrayList<Nodo> nodos = grafo.getMisNodos();
            for (Nodo nodo : nodos) {
                rows[0] = "Ubic-" + nodo.getCodigo();
                rows[1] = nodo.getValor();
                rows[2] = nodo.getNombreUbicacion();
                rows[3] = nodo.getLonguitud();
                rows[4] = nodo.getLatitud();
                model.addRow(rows);
            }
        }
        
        table.clearSelection();
    }
}