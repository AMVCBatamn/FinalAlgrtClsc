package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Arista;
import logico.Grafo;
import logico.Nodo;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;

public class AgregarConexion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox cbxOrigen;
	private JSpinner spnPeso;
	private JTable table;
	private DefaultTableModel model;
	private static Object[] rows;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AgregarConexion dialog = new AgregarConexion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AgregarConexion() {
		setTitle("Agregar Conexiones");
		setBounds(100, 100, 685, 535);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblOrigen = new JLabel("Origen:");
			lblOrigen.setBounds(43, 36, 58, 14);
			panel.add(lblOrigen);
			
			cbxOrigen = new JComboBox();
			cbxOrigen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarTabla();
					spnPeso.setEnabled(false);
				}
			});
			cbxOrigen.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
			cbxOrigen.setBounds(111, 33, 170, 20);
			panel.add(cbxOrigen);
			
			JLabel lblNewLabel = new JLabel("Peso:");
			lblNewLabel.setBounds(357, 36, 46, 14);
			panel.add(lblNewLabel);
			
			spnPeso = new JSpinner();
			spnPeso.setEnabled(false);
			spnPeso.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnPeso.setBounds(413, 33, 170, 20);
			panel.add(spnPeso);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(34, 92, 590, 325);
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel_1.add(scrollPane, BorderLayout.CENTER);
			
			model = new DefaultTableModel();
	        String[] header = {"Código", "Ubicación ", "Valor", "Longitud", "Latitud"};
	        model.setColumnIdentifiers(header);
			
			table = new JTable();
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = -1; 
					index = table.getSelectedRow();
					if (index != -1) {
						
						Nodo UbiSelected = Grafo.getInstance().buscarNodoByNombre(table.getValueAt(index, 1).toString());
						String origen = (String) cbxOrigen.getSelectedItem();
						
						if (UbiSelected.getNombreUbicacion().equalsIgnoreCase(origen)) {
							
							spnPeso.setEnabled(true);
			                spnPeso.setValue(0);
			                
						} else {
							
							spnPeso.setEnabled(true);
							spnPeso.setValue(1);
						}
						
					} else {
						
						spnPeso.setEnabled(false);
					}
				}
			});
			scrollPane.setViewportView(table);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Agregar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						agregarConexion();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadUbicaciones();
		actualizarTabla();
	}
	
	private void actualizarTabla() {
		
		if (Grafo.getInstance() != null && model != null && cbxOrigen.getSelectedIndex() != -1) {
			model.setRowCount(0);
			rows = new Object[model.getColumnCount()];
			
			Nodo origen = Grafo.getInstance().buscarNodoByNombre((String) cbxOrigen.getSelectedItem());
			
			if (origen != null) {
				
				ArrayList<Nodo> desconocidos = loadDesconocidos(origen);
							
				for (Nodo nodo : desconocidos) {
					rows[0] = "Cxn-"+nodo.getCodigo();
					rows[1] = nodo.getNombreUbicacion();
					rows[2] = nodo.getValor();
					rows[3] = nodo.getLonguitud();
					rows[4] = nodo.getLatitud();
					model.addRow(rows);	
				}
			}
			table.setModel(model);
		}
	}

	private ArrayList<Nodo> loadDesconocidos(Nodo origen) {
		
		ArrayList<Nodo> desconocidos = new ArrayList<>();
		
		for (Nodo nodo : Grafo.getInstance().getMisNodos()) {
			
			if(!esConocido(nodo.getNombreUbicacion(),origen)) {
				desconocidos.add(nodo);
			}
		}
		
		return desconocidos;	
	}
	
	private boolean esConocido(String nombreUbicacion, Nodo origen) {
		
		boolean conocido = false;
		
		for (Arista arista : Grafo.getInstance().getMisAristas()) {
			
			if ((arista.getUbicacionOrigen().equals(origen) && arista.getUbicacionDestino().getNombreUbicacion().equalsIgnoreCase(nombreUbicacion)) || 
					(arista.getUbicacionDestino().equals(origen) && arista.getUbicacionOrigen().getNombreUbicacion().equalsIgnoreCase(nombreUbicacion))) {
	            
				conocido = true;
	        }	
		}
		return conocido;
	}
	
	private void loadUbicaciones() {
		
		if (cbxOrigen == null ) {
			cbxOrigen = new JComboBox();
		}
		
		ArrayList<Nodo> misNodos = Grafo.getInstance().getMisNodos();
		
		for (Nodo nodo: misNodos) {
			cbxOrigen.addItem(nodo.getNombreUbicacion());
		}
	}
	
	private void agregarConexion() {
		
		String origen = (String) cbxOrigen.getSelectedItem();
	    int peso = (int) spnPeso.getValue();
	    
	    if (cbxOrigen.getSelectedIndex() == -1 || table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una opción válida.", "Agregar Conexión", JOptionPane.WARNING_MESSAGE);
            
	    } else {

	    	Arista temp = new Arista(Grafo.getInstance().buscarNodoByNombre(origen), Grafo.getInstance().buscarNodoByNombre(table.getValueAt(table.getSelectedRow(), 1).toString()), peso);
	    	Grafo.getInstance().insertarArista(temp);
	    	clean();
	    }
	    actualizarTabla();
	}
	
	private void clean() {
		cbxOrigen.setSelectedIndex(0);
		spnPeso.setEnabled(false);
		spnPeso.setValue(1);
		table.clearSelection();
	}
}
