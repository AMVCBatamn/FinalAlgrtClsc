package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;

public class CalculoDijkstra extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JRadioButton rdbtnTodas;
	private JRadioButton rdbtnEspcf;
	private JPanel panelSeleccion;
	private JComboBox cbxDestino;
	private JComboBox cbxtOrigen;//

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
				
				cbxtOrigen = new JComboBox();
				cbxtOrigen.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
				cbxtOrigen.setBounds(80, 26, 191, 18);
				panelSeleccion.add(cbxtOrigen);
				
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
			
			JTextArea textArea = new JTextArea();
			textArea.setEditable(false);
			panel_1.add(textArea, BorderLayout.CENTER);
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
	}
}
