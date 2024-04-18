package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane; // Importar JOptionPane

import logico.Grafo;
import logico.Nodo;

public class AgregarUbicacion extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JSpinner spnLatitud;
    private JTextField txtNombre;
    private JSpinner spnValor;
    private JSpinner spnLongitud;

    // Referencia al grafo donde se agregarán los nodos
    private Grafo grafo;
    private JTextField txtcodigo;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            AgregarUbicacion dialog = new AgregarUbicacion();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            
            // Crear una instancia de Grafo y establecerla en el diálogo
            Grafo grafo = new Grafo();
            dialog.setGrafo(grafo);
            
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public AgregarUbicacion() {
    	setTitle("Agregar Ubicación");
        setBounds(100, 100, 293, 398);
        setLocationRelativeTo(null); 
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(null);
            {
                JLabel lblNewLabel = new JLabel("Valor:");
                lblNewLabel.setBounds(10, 100, 38, 14);
                panel.add(lblNewLabel);
            }
            {
                JLabel lblNewLabel_1 = new JLabel("Nombre:");
                lblNewLabel_1.setBounds(10, 157, 61, 14);
                panel.add(lblNewLabel_1);
            }
            {
                JLabel lblNewLabel_2 = new JLabel("Longitud:");
                lblNewLabel_2.setBounds(10, 214, 61, 14);
                panel.add(lblNewLabel_2);
            }
            {
                JLabel lblNewLabel_3 = new JLabel("Latitud:");
                lblNewLabel_3.setBounds(10, 271, 46, 14);
                panel.add(lblNewLabel_3);
            }

            spnValor = new JSpinner();
            spnValor.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
            spnValor.setBounds(68, 97, 170, 20);
            panel.add(spnValor);

            txtNombre = new JTextField();
            txtNombre.setBounds(68, 154, 170, 20);
            panel.add(txtNombre);
            txtNombre.setColumns(10);

            spnLatitud = new JSpinner();
            spnLatitud.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
            spnLatitud.setBounds(68, 268, 170, 20);
            panel.add(spnLatitud);

            spnLongitud = new JSpinner();
            spnLongitud.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
            spnLongitud.setBounds(68, 211, 170, 20);
            panel.add(spnLongitud);
            
            JLabel lblNewLabel_4 = new JLabel("Codigo:");
            lblNewLabel_4.setBounds(10, 43, 46, 14);
            panel.add(lblNewLabel_4);
            
            txtcodigo = new JTextField();
            txtcodigo.setEditable(false);
            txtcodigo.setBounds(68, 40, 170, 20);
            txtcodigo.setText("Ubic-" + Nodo.getCodigoNodo());
            panel.add(txtcodigo);
            txtcodigo.setColumns(10);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
            	JButton okButton = new JButton("Agregar");
            	okButton.addActionListener(new ActionListener() {
            	    public void actionPerformed(ActionEvent e) {
            	        int valor = (int) spnValor.getValue();
            	        String nombre = txtNombre.getText();
            	        double longitud = (double) spnLongitud.getValue();
            	        double latitud = (double) spnLatitud.getValue();
            	        Nodo nuevoNodo = new Nodo(valor, nombre, longitud, latitud);
            	        
            	        grafo.insertarNodo(nuevoNodo);
            	        
            	        txtcodigo.setText("Ubic-" + Nodo.getCodigoNodo());
   
            	        spnValor.setValue(0);
            	        txtNombre.setText("");
            	        spnLongitud.setValue(0.0);
            	        spnLatitud.setValue(0.0);
            	        
            	        // Mostrar mensaje de confirmación
            	        JOptionPane.showMessageDialog(null, "Ubicación agregada correctamente.");
            	    }
            	});

            	dispose();
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
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setResizable(false);
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }
}

