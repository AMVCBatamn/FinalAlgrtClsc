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
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgregarConexion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox cbxOrigen;
	private JSpinner spnPeso;
	private JList listConocidas;
	private JList listDesconocidas;
	private DefaultListModel<String> defaultConocidos;
	private DefaultListModel<String> defaultDesconocidos;

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
			lblOrigen.setBounds(43, 43, 58, 14);
			panel.add(lblOrigen);
			
			cbxOrigen = new JComboBox();
			cbxOrigen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadConocidos();
					loadDesconocidos();
				}
			});
			cbxOrigen.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
			cbxOrigen.setBounds(111, 40, 170, 20);
			panel.add(cbxOrigen);
			
			JLabel lblNewLabel = new JLabel("Peso:");
			lblNewLabel.setBounds(357, 43, 46, 14);
			panel.add(lblNewLabel);
			
			spnPeso = new JSpinner();
			spnPeso.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnPeso.setBounds(413, 40, 170, 20);
			panel.add(spnPeso);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "Ubicaci\u00F3n Destino:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(34, 100, 590, 305);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setBounds(21, 49, 216, 207);
			panel_1.add(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel_2.add(scrollPane, BorderLayout.CENTER);
			
			defaultConocidos = new DefaultListModel<String>();
			defaultDesconocidos = new DefaultListModel<String>();
			
			listConocidas = new JList();
			listConocidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(listConocidas);
			listConocidas.setModel(defaultConocidos);
			
			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_3.setBounds(353, 49, 216, 207);
			panel_1.add(panel_3);
			panel_3.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_1 = new JScrollPane();
			panel_3.add(scrollPane_1, BorderLayout.CENTER);
			
			listDesconocidas = new JList();
			listDesconocidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane_1.setViewportView(listDesconocidas);
			listDesconocidas.setModel(defaultDesconocidos);
			
			JButton btnDerecha = new JButton(">>");
			btnDerecha.setEnabled(false);
			btnDerecha.setBounds(258, 112, 74, 23);
			panel_1.add(btnDerecha);
			
			JButton btnIzquierda = new JButton("<<");
			btnIzquierda.setEnabled(false);
			btnIzquierda.setBounds(258, 156, 74, 23);
			panel_1.add(btnIzquierda);
			
			JLabel lblNewLabel_1 = new JLabel("Conocidas:");
			lblNewLabel_1.setBounds(21, 31, 130, 14);
			panel_1.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Desconocidas:");
			lblNewLabel_2.setBounds(353, 31, 141, 14);
			panel_1.add(lblNewLabel_2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Agregar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadUbicaciones();
		
	}

	private void loadConocidos() {
	
		if (cbxOrigen.getSelectedIndex() == -1) {
		    clean();
		    
		} else {
			
			Nodo origen = Grafo.getInstance().buscarNodoByNombre((String) cbxOrigen.getSelectedItem());   
			
		    if(origen != null) {
		        clean();
		        
		        for (Arista arista: Grafo.getInstance().getMisAristas()) {
		            
		        	if(arista.getUbicacionOrigen().getNombreUbicacion().equalsIgnoreCase(origen.getNombreUbicacion())) {
		        
		        		defaultConocidos.addElement(arista.getUbicacionDestino().getNombreUbicacion());
		            	
		            }else if(arista.getUbicacionDestino().getNombreUbicacion().equalsIgnoreCase(origen.getNombreUbicacion())) {
		            	
		            	defaultConocidos.addElement(arista.getUbicacionOrigen().getNombreUbicacion());
		            }
		        }
		    }   
		}
	}

	private void loadDesconocidos() {
		
		if (cbxOrigen.getSelectedIndex() == -1) {
			
			clean();
			
		} else {
			
			Nodo origen = Grafo.getInstance().buscarNodoByNombre((String)cbxOrigen.getSelectedItem());
			if(origen != null) {
				
				for (Nodo nodo : Grafo.getInstance().getMisNodos()) {
					
					if(!esConocido(nodo.getNombreUbicacion())) {
						defaultDesconocidos.addElement(nodo.getNombreUbicacion());
					}
				}
			}
		} 
	}

	private boolean esConocido(String nombreUbicacion) {
		
		boolean conocido = false;
		
		for (int i = 0; i < defaultConocidos.size(); i++) {
			
			if(defaultConocidos.getElementAt(i).equalsIgnoreCase(nombreUbicacion)) {
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
	
	private void clean() {
		defaultConocidos.removeAllElements();
		defaultDesconocidos.removeAllElements();
		
	}
}
