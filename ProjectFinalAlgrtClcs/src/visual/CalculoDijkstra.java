package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import logico.Grafo;
import logico.Nodo;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

public class CalculoDijkstra extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JRadioButton rdbtnTodas;
	private JRadioButton rdbtnEspcf;
	private JPanel panelSeleccion;
	private JComboBox cbxDestino;
	private JComboBox cbxOrigen;
	private Grafo grafo;
	private JTextArea textArea;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CalculoDijkstra dialog = new CalculoDijkstra();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CalculoDijkstra() {
		setResizable(false);
		setTitle("Cálculo Dijkstra");
		setBounds(100, 100, 625, 380);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				panelSeleccion = new JPanel();
				panelSeleccion.setBorder(new TitledBorder(null, "Tipo de selecci\u00F3n:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panelSeleccion.setBounds(10, 11, 288, 113);
				panel.add(panelSeleccion);
				panelSeleccion.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Origen:");
				lblNewLabel.setBounds(17, 28, 46, 14);
				panelSeleccion.add(lblNewLabel);
				
				JLabel lblNewLabel_1 = new JLabel("Destino:");
				lblNewLabel_1.setBounds(17, 70, 67, 14);
				panelSeleccion.add(lblNewLabel_1);
				
				cbxOrigen = new JComboBox();
				cbxOrigen.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
				cbxOrigen.setBounds(80, 26, 191, 18);
				panelSeleccion.add(cbxOrigen);
				
				cbxDestino = new JComboBox();
				cbxDestino.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
				cbxDestino.setBounds(80, 68, 191, 18);
				panelSeleccion.add(cbxDestino);
			}
			{
				rdbtnTodas = new JRadioButton("Todas");
				rdbtnTodas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {	
						rdbtnEspcf.setSelected(false);
						cbxDestino.setEnabled(false);
					}
				});
				rdbtnTodas.setBounds(159, 146, 109, 23);
				panel.add(rdbtnTodas);
			}
			{
				rdbtnEspcf = new JRadioButton("Específica");
				rdbtnEspcf.setSelected(true);
				rdbtnEspcf.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rdbtnTodas.setSelected(false);
						cbxDestino.setEnabled(true);
					}
				});
				rdbtnEspcf.setBounds(48, 146, 109, 23);
				panel.add(rdbtnEspcf);
			}
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(308, 11, 287, 278);
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			
			textArea = new JTextArea();
			textArea.setFont(new Font("Courier New", Font.PLAIN, 14));
			textArea.setEditable(false);
			scrollPane = new JScrollPane(textArea);
	        panel_1.add(scrollPane, BorderLayout.CENTER);
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Calcular");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						String origen = (String) cbxOrigen.getSelectedItem();
						String destino = (String) cbxDestino.getSelectedItem();
						
						if (origen.equals("<Seleccione>") || (rdbtnEspcf.isSelected() && destino.equals("<Seleccione>"))) {
							JOptionPane.showMessageDialog(null, "Por favor, ingrese una opción válida", "Error", JOptionPane.ERROR_MESSAGE);
				            return;
				        }
						
						int [][] matrizAdyacencia = Grafo.getInstance().generarMatrizAdyacencia();
						int index = Grafo.getInstance().buscarIndexByNombre(origen);
						int [] distancias = Grafo.getInstance().calcularDijkstra(matrizAdyacencia,index);
						
						StringBuilder resultado = new StringBuilder();
						
						if (rdbtnTodas.isSelected()) {
							
							resultado.append(" Destino: \tDistancia Mínima Desde ").append(origen).append(":\n\n");
							
							for (int i = 0; i < distancias.length; i++) {
								String nodoDestino = Grafo.getInstance().getMisNodos().get(i).getNombreUbicacion();
								if (distancias[i] == Integer.MAX_VALUE) {
									resultado.append(String.format(" %-15s\t%3s\n", nodoDestino, "  INF"));
								} else {
									resultado.append(String.format(" %-15s\t%3d km\n", nodoDestino, distancias[i]));
								}
							}
							
						} else if(rdbtnEspcf.isSelected() && destino != null){
							
							resultado.append(" Destino: \tDistancia Mínima Desde ").append(origen).append(":\n\n");
							
							int destinoIndex = Grafo.getInstance().buscarIndexByNombre(destino);
							
			                if (destinoIndex != -1) {
			                	if (distancias[destinoIndex] == Integer.MAX_VALUE) {
			                		resultado.append(String.format(" %-15s\t%3s\n", destino, "   INF"));
			                	} else {
			                		resultado.append(String.format(" %-15s\t%3d km\n", destino, distancias[destinoIndex]));
			                	}
			                }
							
						} else {
							resultado.append(" Destino no válido");
						}
						textArea.setText(resultado.toString());
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
		cargarDatos();
	}

	private void cargarDatos() {
		
		if (cbxOrigen == null && cbxDestino == null) {
			cbxOrigen = new JComboBox();
			cbxDestino = new JComboBox();
			}
		
		ArrayList<Nodo> misNodos = grafo.getInstance().getMisNodos();
		
		for (Nodo nodo: misNodos) {
			cbxOrigen.addItem(nodo.getNombreUbicacion());
			cbxDestino.addItem(nodo.getNombreUbicacion());
		}
	}
}
