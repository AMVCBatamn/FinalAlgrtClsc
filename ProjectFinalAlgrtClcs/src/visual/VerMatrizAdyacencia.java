package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabExpander;

import logico.Arista;
import logico.Grafo;
import logico.Nodo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class VerMatrizAdyacencia extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private Grafo grafo;
	private int[][] matrizAdyacencia;
	private JRadioButton rdbtnNo;
	private JRadioButton rdbtnSi;
    private boolean noPeso = false;
    private boolean esFloyd = false;
    private JLabel lblNewLabel_1;
    
	public VerMatrizAdyacencia(Grafo grafo, boolean habilitarPesoPanel, boolean esFloydWarshal) {
		
		if (grafo == null) {		
			JOptionPane.showMessageDialog(null, "Información no disponible aún !!!", "Error de Creación de Matriz de Adyacencia", JOptionPane.ERROR_MESSAGE);
		} else {
			this.grafo = grafo;
			this.esFloyd = esFloydWarshal;
		}
		
		setResizable(false);
		setTitle("Matriz Adyacencia");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1300, 545);
        setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(17, 11, 1240, 45);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ver pesos?\r\n");
		lblNewLabel.setBounds(228, 11, 109, 23);
		panel_1.add(lblNewLabel);
		
		rdbtnSi = new JRadioButton("Sí");
		rdbtnSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnSi.setSelected(true);
                rdbtnNo.setSelected(false);
                noPeso = false;
                generarMatrizObjetos(noPeso,esFloyd);
			}
		});
		rdbtnSi.setSelected(true);
		rdbtnSi.setBounds(565, 11, 109, 23);
		panel_1.add(rdbtnSi);
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnSi.setSelected(false);
                rdbtnNo.setSelected(true);
                noPeso = true;
                generarMatrizObjetos(noPeso,esFloyd);
			}
		});
		rdbtnNo.setBounds(902, 11, 109, 23);
		panel_1.add(rdbtnNo);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(17, 67, 1240, 394);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		if (habilitarPesoPanel) {
			rdbtnNo.setEnabled(false);
		} else {
			rdbtnNo.setEnabled(true);
		}
		
		if (esFloydWarshal) {
			lblNewLabel_1 = new JLabel("Nota: La siguiente matriz muestra las distancias (Conexiones) más cortas entre cada par de Vértices (Ubicaciones).");
			lblNewLabel_1.setBounds(17, 472, 776, 14);
			panel.add(lblNewLabel_1);
		}
		
		generarMatrizObjetos(noPeso,esFloyd);
	}
	
	public void generarMatrizObjetos(boolean esPeso, boolean esFloydWarshal) {
    	
    	int tam = grafo.getMisNodos().size() + 1; //Tamano de matrix mas agregado de los identificadores.
    	
    	Object[][] matrizObjetos = new Object[tam][tam];
    	
    	if (esFloyd) {
    		matrizAdyacencia = grafo.calcularFloydWarchall();
    	} else {
    		matrizAdyacencia = grafo.generarMatrizAdyacencia();
    	}
    	
    	setearIdentificadores(matrizObjetos);
    	seterMatrizAdyacencia(matrizObjetos, matrizAdyacencia, esPeso);
    	 
    	if (table == null) {
    		table = new JTable();
    	}
    	
    	ArrayList<Nodo> misNodos = grafo.getMisNodos();
    	String[] header = new String[tam];

    	for (int i = 0; i < tam; i++) {
    	    header[i] = "";
    	}
    	
    	DefaultTableModel model = new DefaultTableModel(matrizObjetos, header);
    	Font font = new Font("Verdana", Font.BOLD, 11);
        table.setFont(font);
    	table.setModel(model);
    	table.setTableHeader(null);
    	
    	DefaultTableCellRenderer justificarCentro = new DefaultTableCellRenderer();
    	justificarCentro.setHorizontalAlignment(JLabel.CENTER);
        
        DefaultTableCellRenderer justificarDerecho = new DefaultTableCellRenderer();
        justificarDerecho.setHorizontalAlignment(JLabel.RIGHT);
        
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(justificarCentro);
        }   
        table.getColumnModel().getColumn(0).setCellRenderer(justificarDerecho);
    }
    
	private void seterMatrizAdyacencia(Object[][] matrizObjetos, int[][] matrizAdyacencia, boolean esPeso) {
		
		for (int i = 1; i < matrizObjetos.length; i++) {
			
			for (int j = 1; j < matrizObjetos[0].length; j++) {
				
				if (esPeso) {
					
	                if (matrizAdyacencia[i - 1][j - 1] != 0) {
	                    
	                	matrizObjetos[i][j] = 1;
	                	
	                } else {
	                	
	                    matrizObjetos[i][j] = matrizAdyacencia[i - 1][j - 1];
	                }
	                
	            } else {
	            	if (matrizAdyacencia[i - 1][j - 1] == Integer.MAX_VALUE) {
	            		matrizObjetos[i ][j] = "INF";
	            	} else {
		                matrizObjetos[i][j] = matrizAdyacencia[i - 1][j - 1];
	            	}
	            }
			}
		}
	}
	
	private void setearIdentificadores(Object[][] matrizObjetos) {
	    
		ArrayList<Nodo> misNodos = grafo.getMisNodos();
	    
	    for (int i = 0; i < matrizObjetos.length; i++) {
	        for (int j = 0; j < matrizObjetos[0].length; j++) {
	            if (i == 0 && j == 0) {
	                matrizObjetos[0][0] = " ";
	            } else if (i == 0) {
	                matrizObjetos[i][j] = misNodos.get(j-1).getNombreUbicacion();
	            } else if (j == 0) {
	                matrizObjetos[i][j] = misNodos.get(i-1).getNombreUbicacion();
	            }
	        }
	    }
	}	
}
