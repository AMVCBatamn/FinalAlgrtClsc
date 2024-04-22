package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import logico.Arista;
import logico.Grafo;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

public class CalculoPrim extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea;
	private JPanel panelMST;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CalculoPrim dialog = new CalculoPrim();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CalculoPrim() {
		setResizable(false);
		setTitle("Cálculo Prim");
		setBounds(100, 100, 750, 528);
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
			
			JLabel lblNewLabel = new JLabel("Resultados:");
			lblNewLabel.setBounds(64, 14, 75, 14);
			panel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Ver Grafo de Prim?");
			lblNewLabel_1.setBounds(556, 59, 117, 14);
			panel.add(lblNewLabel_1);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(64, 42, 442, 385);
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane((Component) null);
			panel_1.add(scrollPane, BorderLayout.CENTER);
			
			textArea = new JTextArea();
			textArea.setFont(new Font("Courier New", Font.PLAIN, 14));
			textArea.setEditable(false);
			scrollPane.setViewportView(textArea);
			
			JButton btnNewButton = new JButton("Sí");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if (Grafo.getInstance().getMisAristas().size() == 0) {
						JOptionPane.showMessageDialog(null, "Información no disponible aún !!!", "Error de Creación de Grafo Virtual", JOptionPane.ERROR_MESSAGE);
					} else {
						loadGrafo();
					}
				}
			});
			btnNewButton.setBounds(556, 84, 117, 23);
			panel.add(btnNewButton);
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
							JOptionPane.showMessageDialog(null, "Información no disponible aún !!!", "Error de Cálculo de Prim", JOptionPane.ERROR_MESSAGE);
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
      
      Objetivo: Carga los resultados del algoritmo de Prim en el área de texto.
      
      Argumentos: Ninguno
      
      Retorno: Ninguno
     */
	private void loadResultados() {
		
		ArrayList<Arista> aristasPrim = Grafo.getInstance().calcularPrim(); 
		
		StringBuilder sb = new StringBuilder();
		sb.append(" Árbol de Expansión Mínima vía Prim:\n\n");
		sb.append(String.format(" %-40s \t| %-15s\n", "Conexión:", "Distancia:"));

		for (Arista arista : aristasPrim) {
			String pesoArista = arista.getPeso() == Integer.MAX_VALUE ? "INF" : Integer.toString(arista.getPeso());
			sb.append(String.format(" %-40s \t| %-15s\n", "["+arista.getUbicacionOrigen().getNombreUbicacion() + ", " + arista.getUbicacionDestino().getNombreUbicacion()+"]", pesoArista));
		}
		textArea.setText(sb.toString());
		Font font = new Font("Verdana", Font.PLAIN, 12);
		textArea.setFont(font);
	}
	
	
    /**
      Método: loadGrafo
      
      Objetivo: Carga y muestra el grafo generado por el algoritmo de Prim.
      
      Argumentos: Ninguno
      
      Retorno: Ninguno
     */
	private void loadGrafo() {
		
		ArrayList<Arista> aristasPrim = Grafo.getInstance().calcularPrim();
		Grafo.getInstanceMST();
		Grafo.generarMST(aristasPrim);
		
	    DibujarGrafo dibujo = new DibujarGrafo(Grafo.getInstanceMST(), true);
	   
	    
	    JDialog JPrim = new JDialog();
	    JPrim.setTitle("Grafo de Prim");
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
