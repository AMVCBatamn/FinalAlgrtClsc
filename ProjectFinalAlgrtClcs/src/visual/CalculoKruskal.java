package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Arista;
import logico.Grafo;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Toolkit;

public class CalculoKruskal extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CalculoKruskal dialog = new CalculoKruskal();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CalculoKruskal() {
		setResizable(false);
		setTitle("Cálculo Kruskal");
		setBounds(100, 100, 750, 528);
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
				JLabel label = new JLabel("Resultados:");
				label.setBounds(64, 14, 75, 14);
				panel.add(label);
			}
			{
				JLabel lblVerGrafoDe = new JLabel("Ver Grafo de Kruskal?");
				lblVerGrafoDe.setBounds(556, 59, 133, 14);
				panel.add(lblVerGrafoDe);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_1.setBounds(64, 42, 442, 385);
				panel.add(panel_1);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JScrollPane scrollPane = new JScrollPane((Component) null);
					panel_1.add(scrollPane, BorderLayout.CENTER);
					
					textArea = new JTextArea();
					textArea.setEditable(false);
					textArea.setFont(new Font("Courier New", Font.PLAIN, 14));
					scrollPane.setViewportView(textArea);
				}
			}
			{
				JButton btnSi = new JButton("Sí");
				btnSi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if (Grafo.getInstance().getMisAristas().size() == 0) {
							JOptionPane.showMessageDialog(null, "Información no disponible aún !!!", "Error de Creación de Grafo Virtual", JOptionPane.ERROR_MESSAGE);
						} else {
							loadGrafo();
						}
					}
				});
				btnSi.setBounds(556, 84, 117, 23);
				panel.add(btnSi);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGenerar = new JButton("Generar");
				btnGenerar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if (Grafo.getInstance().getMisAristas().size() == 0) {
							JOptionPane.showMessageDialog(null, "Información no disponible aún !!!", "Error de Cálculo de Kruskal", JOptionPane.ERROR_MESSAGE);
						} else {
							loadResultados();	
						}
					}
				});
				btnGenerar.setActionCommand("OK");
				buttonPane.add(btnGenerar);
				getRootPane().setDefaultButton(btnGenerar);
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
	}
	
	  /**
      Método: loadResultados
      
      Objetivo: Carga los resultados del algoritmo de Kruskal en el área de texto.
      
      Argumentos: Ninguno
      
      Retorno: Ninguno
     */
	private void loadResultados() {
		
		ArrayList<Arista> aristaKruskal = Grafo.getInstance().calcularKruskal(); 
		
		StringBuilder sb = new StringBuilder();
		sb.append(" Árbol de Expansión Mínima vía Kruskal:\n\n");
		sb.append(String.format(" %-40s \t| %-15s\n", "Conexión:", "Distancia:"));
	
		for (Arista arista : aristaKruskal) {
			String pesoArista = arista.getPeso() == Integer.MAX_VALUE ? "INF" : Integer.toString(arista.getPeso());
			sb.append(String.format(" %-40s \t| %-15s\n", "["+arista.getUbicacionOrigen().getNombreUbicacion() + ", " + arista.getUbicacionDestino().getNombreUbicacion()+"]", pesoArista));
		}
		textArea.setText(sb.toString());
		Font font = new Font("Verdana", Font.PLAIN, 12);
		textArea.setFont(font);
	}
	
	  /**
      Método: loadGrafo
      
      Objetivo: Carga y muestra el grafo generado por el algoritmo de Kruskal.
      
      Argumentos: Ninguno
      
      Retorno: Ninguno
     */
	
	private void loadGrafo() {
		
		ArrayList<Arista> aristaKruskal = Grafo.getInstance().calcularKruskal();
		Grafo.getInstanceMST();
		Grafo.generarMST(aristaKruskal);
		
	    DibujarGrafo dibujo = new DibujarGrafo(Grafo.getInstanceMST(), true);
	   
	    
	    JDialog JPrim = new JDialog();
	    JPrim.setTitle("Grafo de Kruskal");
	    JPrim.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    JPrim.setModal(true);
	    JPrim.getContentPane().add(dibujo, BorderLayout.CENTER);

	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    JPrim.setBounds(0, 0, (screenSize.width + 400) / 2, (screenSize.height + 600) / 2);

	    JPrim.setLocationRelativeTo(null);
	    JPrim.setVisible(true);
	    JPrim.setResizable(false);
	}
}
