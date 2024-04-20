package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Arista;
import logico.Grafo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class ListarConexion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private static Object[] rows;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarConexion dialog = new ListarConexion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarConexion() {
		setResizable(false);
		setTitle("Listar Conexiones");
		setBounds(100, 100, 640, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane, BorderLayout.CENTER);
				{	
					model = new DefaultTableModel();
			        String[] header = {"Código", "Ubicación 1", "Ubicación 2", "Peso"};
			        model.setColumnIdentifiers(header);
			        
					table = new JTable();
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane.setViewportView(table);
					table.setModel(model);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						int index = -1; 
						index = table.getSelectedRow();
						if (index != -1) {
							
							Arista selected = Grafo.getInstance().buscarAristaByCodigo(table.getValueAt(index, 0).toString().substring(4));
							ModificarConexion mod = new ModificarConexion(selected);
							mod.setModal(true);
							mod.setVisible(true);
							loadConexiones();
							
						} else {
							
							JOptionPane.showMessageDialog(null, "Por favor, seleccione una conexión para modificar.", "Modificar Conexión", JOptionPane.WARNING_MESSAGE);
						}
						
					}
				});
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
				{
					JButton btnEliminar = new JButton("Eliminar");
					btnEliminar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							int index = -1; 
							index = table.getSelectedRow();
							if (index != -1) {
								
								int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar esta conexión?", "Eliminar Conexión", JOptionPane.YES_NO_OPTION);
				                
								if (opcion == JOptionPane.YES_OPTION) {
				                	Arista selected = Grafo.getInstance().buscarAristaByCodigo(table.getValueAt(index, 0).toString().substring(4));
									Grafo.getInstance().eliminarArista(selected);
									loadConexiones();
				                }
								
							} else {
								JOptionPane.showMessageDialog(null, "Por favor, seleccione una conexión para eliminar.", "Eliminar Conexión", JOptionPane.WARNING_MESSAGE);
							}
						}
					});
					buttonPane.add(btnEliminar);
				}
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
		loadConexiones();
	}
	
	private void loadConexiones() {
		
		rows = new Object[model.getColumnCount()];
		model.setRowCount(0);
		
		for (Arista arista : Grafo.getInstance().getMisAristas()) {
			rows[0] = "Cxn-"+arista.getCodigo();
			rows[1] = arista.getUbicacionOrigen().getNombreUbicacion();
			rows[2] = arista.getUbicacionDestino().getNombreUbicacion();
			rows[3] = arista.getPeso();
			model.addRow(rows);
		}
		table.clearSelection();
	}
}
