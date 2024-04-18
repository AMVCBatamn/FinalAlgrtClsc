package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logico.Grafo;
import logico.Nodo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarUbicaciones extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel model;
    private Object[] row;
    private Grafo grafo; // Referencia al grafo donde se encuentran los nodos

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
        buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton btnActualizar = new JButton("Actualizar (ver nuevos)");
        btnActualizar.addActionListener(e -> actualizarTabla());
        buttonPane.add(btnActualizar);
        
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        setResizable(false);
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    private void actualizarTabla() {
        model.setRowCount(0);

        ArrayList<Nodo> nodos = grafo.getMisNodos();

       
        for (Nodo nodo : nodos) {
            row = new Object[5];
            row[0] = nodo.getCodigo();
            row[1] = nodo.getValor();
            row[2] = nodo.getNombreUbicacion();
            row[3] = nodo.getLonguitud();
            row[4] = nodo.getLatitud();
            
            model.addRow(row);
        }//

        
        for (Nodo nodo : nodos) {
            System.out.println(nodo);
        }
    }
}//
