package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlanificacionRuta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnPlanificarTiempo;
	private JButton btnPlanificarDistancia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PlanificacionRuta dialog = new PlanificacionRuta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PlanificacionRuta() {
		setResizable(false);
		setTitle("Planificación de Ruta");
		setBounds(100, 100, 378, 249);
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
			
			JLabel lblNewLabel = new JLabel("Planificar mínima distancia:");
			lblNewLabel.setBounds(36, 48, 165, 14);
			panel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Planificar menor tiempo:");
			lblNewLabel_1.setBounds(36, 110, 165, 14);
			panel.add(lblNewLabel_1);
			
			btnPlanificarDistancia = new JButton("Planificar");
			btnPlanificarDistancia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CalculoDijkstra calcuDij = new CalculoDijkstra(false);
					calcuDij.setModal(true);
					calcuDij.setVisible(true);
				}
			});
			btnPlanificarDistancia.setBounds(211, 42, 89, 23);
			panel.add(btnPlanificarDistancia);
			
			btnPlanificarTiempo = new JButton("Planificar");
			btnPlanificarTiempo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CalculoDijkstra calcuDij = new CalculoDijkstra(true);
					calcuDij.setModal(true);
					calcuDij.setVisible(true);
				}
			});
			btnPlanificarTiempo.setBounds(211, 106, 89, 23);
			panel.add(btnPlanificarTiempo);
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
	}
}
