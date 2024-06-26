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
	private JSpinner spnTiempo;

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
			lblOrigen.setBounds(34, 39, 58, 14);
			panel.add(lblOrigen);
			
			cbxOrigen = new JComboBox();
			cbxOrigen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarTabla();
					spnPeso.setEnabled(false);
				}
			});
			cbxOrigen.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
			cbxOrigen.setBounds(87, 36, 145, 20);
			panel.add(cbxOrigen);
			
			JLabel lblNewLabel = new JLabel("Peso:");
			lblNewLabel.setBounds(328, 39, 46, 14);
			panel.add(lblNewLabel);
			
			spnPeso = new JSpinner();
			spnPeso.setEnabled(false);
			spnPeso.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnPeso.setBounds(371, 36, 84, 20);
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
			                spnTiempo.setEnabled(true);
			                spnTiempo.setValue(0);
			                
						} else {
							
							spnPeso.setEnabled(true);
							spnPeso.setValue(1);
							spnTiempo.setEnabled(true);
			                spnTiempo.setValue(1);
						}
						
					} else {
						
						spnPeso.setEnabled(false);
						spnTiempo.setEnabled(false);
					}
				}
			});
			scrollPane.setViewportView(table);
			
			JLabel lblNewLabel_1 = new JLabel("Tiempo:");
			lblNewLabel_1.setBounds(488, 39, 58, 14);
			panel.add(lblNewLabel_1);
			
			spnTiempo = new JSpinner();
			spnTiempo.setEnabled(false);
			spnTiempo.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnTiempo.setBounds(540, 36, 84, 20);
			panel.add(spnTiempo);
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
	
	/*
	  Método: actualizarTabla
	  
	  Objetivo: Actualiza la tabla de ubicaciones desconocidas.
	  
	  Argumento: Ninguno
	  
	  Retorno: void
	 */
	
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

	/*
	  Método: loadDesconocidos
	  
	  Objetivo: Carga en un ArrayList los nodos que no están conectados al nodo de origen.
	  
	  Argumento: Nodo origen: Nodo del que se desea buscar las conexiones.
	  
	  Retorno: ArrayList<Nodo>: Lista de nodos no conectados al nodo de origen.
	 */
	private ArrayList<Nodo> loadDesconocidos(Nodo origen) {
		
		ArrayList<Nodo> desconocidos = new ArrayList<>();
		
		for (Nodo nodo : Grafo.getInstance().getMisNodos()) {
			
			if(!esConocido(nodo.getNombreUbicacion(),origen)) {
				desconocidos.add(nodo);
			}
		}
		
		return desconocidos;	
	}
	
	
	/*
	  Método: esConocido
	  
	  Objetivo: Verifica si una ubicación es conocida desde un nodo de origen.
	  
	  Argumento: String nombreUbicacion: Nombre de la ubicación a verificar.
	             Nodo origen: Nodo desde el que se desea verificar la conexión.
	  
	  Retorno: boolean: true si la ubicación es conocida desde el nodo de origen, false en caso contrario.
	 */
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
	
	
	/*
	  Método: loadUbicaciones
	  
	  Objetivo: Carga las ubicaciones en el ComboBox de origen.
	  
	  Argumento: Ninguno
	  
	  Retorno: void
	 */
	private void loadUbicaciones() {
		
		if (cbxOrigen == null ) {
			cbxOrigen = new JComboBox();
		}
		
		ArrayList<Nodo> misNodos = Grafo.getInstance().getMisNodos();
		
		for (Nodo nodo: misNodos) {
			cbxOrigen.addItem(nodo.getNombreUbicacion());
		}
	}
	
	/*
	  Método: agregarConexion
	  
	  Objetivo: Agrega una nueva conexión entre dos ubicaciones al grafo.
	  
	  Argumento: Ninguno
	  
	  Retorno: void
	 */
	private void agregarConexion() {
		
		String origen = (String) cbxOrigen.getSelectedItem();
	    int peso = (int) spnPeso.getValue();
	    int tiempo = (int) spnTiempo.getValue();
	    
	    if (cbxOrigen.getSelectedIndex() == -1 || table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una opción válida.", "Agregar Conexión", JOptionPane.WARNING_MESSAGE);
            
	    } else {
	    	
	    	if (origen.equalsIgnoreCase(table.getValueAt(table.getSelectedRow(), 1).toString())) {
	    		peso = 0;
	    		tiempo = 0;
	    	}
	    	
	    	Arista temp = new Arista(Grafo.getInstance().buscarNodoByNombre(origen), Grafo.getInstance().buscarNodoByNombre(table.getValueAt(table.getSelectedRow(), 1).toString()), peso, tiempo);
	    	Grafo.getInstance().insertarArista(temp);
	    	JOptionPane.showMessageDialog(null, "Conexión agregada correctamente.", "Agregar Conexión", JOptionPane.INFORMATION_MESSAGE);
	    	clean();
	    }
	    actualizarTabla();
	}
	
	
	
	/*
	  Método: clean
	  
	  Objetivo: Limpia los campos de la interfaz.
	  
	  Argumento: Ninguno
	  
	  Retorno: void
	 */
	private void clean() {
		cbxOrigen.setSelectedIndex(0);
		spnPeso.setEnabled(false);
		spnPeso.setValue(1);
		spnTiempo.setEnabled(false);
		spnTiempo.setValue(1);
		table.clearSelection();
	}
}
