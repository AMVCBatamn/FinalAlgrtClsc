package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Grafo;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerGrafo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Dimension screenSize = null;
	private JRadioButton rdbtnPeso;
	private JRadioButton rdbtnTiempo;
	private JPanel panel;
	private JPanel panelGrafo;
	private boolean verPeso = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VerGrafo dialog = new VerGrafo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	/**
	  Constructor: VerGrafo
	  
	  Objetivo: Inicializa la ventana de visualización del grafo.
	 */
	public VerGrafo() {
		setResizable(false);
		setTitle("Ver Grafo");
		setBounds(100, 100, 450, 300);
		
		screenSize= Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, (screenSize.width+400)/2, (screenSize.height+600)/2);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(20, 17, 1107, 659);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));

		}
		{
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setBounds(20, 693, 1107, 61);
			contentPanel.add(panel_2);
			panel_2.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Ver Distancia o Tiempo?");
				lblNewLabel.setBounds(175, 19, 141, 23);
				panel_2.add(lblNewLabel);
			}
			
			rdbtnPeso = new JRadioButton("Ver Distancia (km)\r\n");
			rdbtnPeso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verPeso = true;
					rdbtnTiempo.setSelected(false);
					rdbtnPeso.setSelected(true);
					loadDibujoGrafo(verPeso);
				}
			});
			rdbtnPeso.setSelected(true);
			rdbtnPeso.setBounds(491, 19, 162, 23);
			panel_2.add(rdbtnPeso);
			
			rdbtnTiempo = new JRadioButton("Ver Tiempo (mins)");
			rdbtnTiempo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verPeso = false;
					rdbtnPeso.setSelected(false);
					rdbtnTiempo.setSelected(true);
					loadDibujoGrafo(verPeso);
				}
			});
			rdbtnTiempo.setBounds(791, 19, 141, 23);
			panel_2.add(rdbtnTiempo);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
		loadDibujoGrafo(verPeso);
	}
	
	/*
	  Método: loadDibujoGrafo
	  
	  Objetivo: Carga el grafo en el panel para visualización.
	  
	  Argumento: boolean verPeso: Indica si se quiere visualizar el peso (true) o el tiempo (false) de las conexiones.
	  
	  Retorno: void
	 */
	
	private void loadDibujoGrafo(boolean verPeso) {
		
		panel.removeAll();
	    panelGrafo = new DibujarGrafo(Grafo.getInstance(), verPeso);
	    panel.add(panelGrafo, BorderLayout.CENTER);
	    panel.revalidate();
	    panel.repaint();
	}
}
