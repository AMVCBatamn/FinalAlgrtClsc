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
			lblNewLabel.setBounds(47, 42, 137, 14);
			panel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Ubicación destino:");
			lblNewLabel_1.setBounds(47, 98, 137, 14);
			panel.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Peso:");
			lblNewLabel_2.setBounds(92, 154, 46, 14);
			panel.add(lblNewLabel_2);
			
			spnPeso = new JSpinner();
			spnPeso.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnPeso.setBounds(165, 151, 101, 20);
			panel.add(spnPeso);
			
			cbxDestino = new JComboBox();
			cbxDestino.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
			cbxDestino.setBounds(165, 95, 146, 20);
			panel.add(cbxDestino);
			
			cbxOrigen = new JComboBox();
			cbxOrigen.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
			cbxOrigen.setBounds(165, 39, 146, 20);
			panel.add(cbxOrigen);
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
						
						
						JOptionPane.showMessageDialog(null, "Conexión modificada correctamente.");
						dispose();
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
			
			if (arista.getPeso() == 0 && cbxDestino.getSelectedItem().equals(cbxOrigen.getSelectedItem())) {
				
				spnPeso.setEnabled(false);
				
			} else {
				
				spnPeso.setEnabled(true);
			}
		}	
	}
}
