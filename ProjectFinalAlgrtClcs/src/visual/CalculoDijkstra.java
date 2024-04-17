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
	private JComboBox cbxtOrigen;

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
		setBounds(100, 100, 625, 380);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				panelSeleccion = new JPanel();
				panelSeleccion.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Seleccion", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panelSeleccion.setBounds(10, 11, 288, 113);
				panel.add(panelSeleccion);
				panelSeleccion.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Origen:");
				lblNewLabel.setBounds(10, 29, 46, 14);
				panelSeleccion.add(lblNewLabel);
				
				JLabel lblNewLabel_1 = new JLabel("Destino:");
				lblNewLabel_1.setBounds(28, 70, 67, 14);
				panelSeleccion.add(lblNewLabel_1);
				
				cbxtOrigen = new JComboBox();
				cbxtOrigen.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
				cbxtOrigen.setBounds(55, 27, 212, 18);
				panelSeleccion.add(cbxtOrigen);
				
				cbxDestino = new JComboBox();
				cbxDestino.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
				cbxDestino.setBounds(87, 68, 191, 18);
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
				rdbtnTodas.setBounds(96, 143, 109, 23);
				panel.add(rdbtnTodas);
			}
			{
				rdbtnEspcf = new JRadioButton("Especifico");
				rdbtnEspcf.setSelected(true);
				rdbtnEspcf.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						rdbtnTodas.setSelected(false);
						cbxDestino.setEnabled(true);
						
					}
				});
				rdbtnEspcf.setBounds(96, 188, 109, 23);
				panel.add(rdbtnEspcf);
			}
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "Resultados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(308, 11, 287, 278);
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			
			JTextArea textArea = new JTextArea();
			panel_1.add(textArea, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
