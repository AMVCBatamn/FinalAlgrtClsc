package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Arista;
import logico.Grafo;
import logico.Nodo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModificarConexion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox cbxOrigen;
	private JComboBox cbxDestino;
	private Arista arista = null;
	private JSpinner spnPeso;
	private JSpinner spnTiempo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModificarConexion dialog = new ModificarConexion(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param selected 
	 */
	public ModificarConexion(Arista selected) {
		setResizable(false);
		
		arista = selected;
		
		setTitle("Modificar Conexión");
		setBounds(100, 100, 450, 300);
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
			
			JLabel lblNewLabel = new JLabel("Ubicación origen:");
			lblNewLabel.setBounds(47, 31, 137, 14);
			panel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Ubicación destino:");
			lblNewLabel_1.setBounds(47, 76, 137, 14);
			panel.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Peso:");
			lblNewLabel_2.setBounds(92, 121, 46, 14);
			panel.add(lblNewLabel_2);
			
			spnPeso = new JSpinner();
			spnPeso.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnPeso.setBounds(165, 118, 101, 20);
			panel.add(spnPeso);
			
			cbxDestino = new JComboBox();
			cbxDestino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reloadUbicaciones();
				}
			});
			cbxDestino.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
			cbxDestino.setBounds(165, 73, 146, 20);
			panel.add(cbxDestino);
			
			cbxOrigen = new JComboBox();
			cbxOrigen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reloadUbicaciones();
				}
			});
			cbxOrigen.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
			cbxOrigen.setBounds(165, 28, 146, 20);
			panel.add(cbxOrigen);
			
			JLabel label = new JLabel("Tiempo:");
			label.setBounds(86, 166, 58, 14);
			panel.add(label);
			
			spnTiempo = new JSpinner();
			spnTiempo.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnTiempo.setBounds(165, 163, 101, 20);
			panel.add(spnTiempo);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton tbnModificar = new JButton("Modificar");
				tbnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String origen = (String) cbxOrigen.getSelectedItem();
						String destino = (String) cbxDestino.getSelectedItem();
						int peso = (int) spnPeso.getValue();
						int tiempo = (int) spnTiempo.getValue();
						
						if (origen.equals("<Seleccione>") || destino.equals("<Seleccione>")) {
							
							JOptionPane.showMessageDialog(null, "Por favor, ingrese una opción válida", "Error", JOptionPane.WARNING_MESSAGE);
							
						} else {
							
							int codigo = selected.getCodigo();
							Nodo nuevoOrigen = Grafo.getInstance().buscarNodoByNombre(cbxOrigen.getSelectedItem().toString());
							Nodo nuevoDestino = Grafo.getInstance().buscarNodoByNombre(cbxDestino.getSelectedItem().toString());
							
							if (origen.equalsIgnoreCase(destino)) {
								peso = 0;
								spnPeso.setValue(0);
								tiempo = 0;
								spnTiempo.setValue(0);
							}
							
							Arista nuevoArista = new Arista(nuevoOrigen, nuevoDestino, peso, tiempo);
							nuevoArista.setCodigo(codigo);
							int index = Grafo.getInstance().buscarAristaIndexByCodigo(codigo);
							
							if (Grafo.getInstance().existeArista(origen, destino, peso, tiempo)) {
								
								JOptionPane.showMessageDialog(null, "La conexión ya existe en el grafo. Por favor, elija otra.", "Error", JOptionPane.ERROR_MESSAGE);
								
							} else {
								
								Grafo.getInstance().actualizarArista(index,nuevoArista);
								Grafo.getInstance().eliminarArista(selected);
								
								JOptionPane.showMessageDialog(null, "Conexión modificada correctamente.", "Modificar", JOptionPane.INFORMATION_MESSAGE);
								clean();
								dispose();	
							}
						}
					}
				});
				tbnModificar.setActionCommand("OK");
				buttonPane.add(tbnModificar);
				getRootPane().setDefaultButton(tbnModificar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadUbicaciones();
	}
	
	/*
	  Método: loadUbicaciones
	  
	  Objetivo: Carga las ubicaciones en los ComboBox.
	  
	  Argumento: Ninguno
	  
	  Retorno: void
	 */
	
	private void loadUbicaciones() {
		
		if (cbxOrigen == null || cbxDestino == null) {
			cbxOrigen = new JComboBox();
			cbxDestino = new JComboBox();
		}
		
		ArrayList<Nodo> misNodos = Grafo.getInstance().getMisNodos();
		
		if (misNodos.size() != 0) {
			 
			for (Nodo nodo: misNodos) {
				cbxOrigen.addItem(nodo.getNombreUbicacion());
				cbxDestino.addItem(nodo.getNombreUbicacion());
			}
			
			cbxDestino.setSelectedItem(arista.getUbicacionDestino().getNombreUbicacion());
			cbxOrigen.setSelectedItem(arista.getUbicacionOrigen().getNombreUbicacion());
			spnPeso.setValue(arista.getPeso());
			spnTiempo.setValue(arista.getTiempo());
			reloadUbicaciones();
		}
	}
	
	/*
	  Método: reloadUbicaciones
	  
	  Objetivo: Recarga los ComboBox de acuerdo a la selección.
	  
	  Argumento: Ninguno
	  
	  Retorno: void
	 */
	private void reloadUbicaciones() {
		
		String origen = (String) cbxOrigen.getSelectedItem();
        String destino = (String) cbxDestino.getSelectedItem();

        if (origen.equalsIgnoreCase("<Seleccione>") || destino.equalsIgnoreCase("<Seleccione>") || origen.equalsIgnoreCase(destino)) {
            spnPeso.setEnabled(false);
            spnPeso.setValue(0);
            spnTiempo.setEnabled(false);
            spnTiempo.setValue(0);
        } else {
            spnPeso.setEnabled(true);
            spnTiempo.setEnabled(true);
        }
	}
	
	/*
	  Método: clean
	  
	  Objetivo: Limpia los campos de entrada.
	  
	  Argumento: Ninguno
	  
	  Retorno: void
	 */
	
	private void clean() {
		cbxOrigen.setSelectedItem("<Seleccione>");
		cbxDestino.setSelectedItem("<Seleccione>");
	    spnPeso.setValue(1);
	    spnTiempo.setValue(1);
	}
}
