package visual;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.border.TitledBorder;

import logico.Arista;
import logico.Grafo;
import logico.Nodo;

import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {
//
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Grafo grafo; // Referencia al grafo principal

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Menu frame = new Menu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Menu() {
    	
    	Grafo grafo = new Grafo();
    	
		Nodo A = new Nodo(10, "Moca", 10.5, 20.3);
		Nodo B = new Nodo(23, "La Vega", 15.2, 25.7);
		Nodo C = new Nodo(13, "Santiago", 20.8, 30.1);
		
		grafo.insertarNodo(A);
		grafo.insertarNodo(B);
		grafo.insertarNodo(C);
		
		Arista arista1 = new Arista(A, B, 5);
		Arista arista2 = new Arista(B, C, 8);
		Arista arista3 = new Arista(A, C, 1);
		
		///PROBANDO LOS METODOS:
		
		grafo.insertarArista(arista1);
		grafo.insertarArista(arista2);
		grafo.insertarArista(arista3);
    	
    	Grafo.getInstance().setMisAristas(grafo.getMisAristas());
    	Grafo.getInstance().setMisNodos(grafo.getMisNodos());
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 328);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setToolTipText("");
        setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("Gestion de Ubicaciones");
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewMenuItem = new JMenuItem("Agregar Ubicacion");
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AgregarUbicacion agregarnodo = new AgregarUbicacion();
                agregarnodo.setGrafo(grafo); 
                agregarnodo.setModal(true);
                agregarnodo.setVisible(true);
            }
        });

        mnNewMenu.add(mntmNewMenuItem);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Listar Ubicacion");
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListarUbicaciones listnodo = new ListarUbicaciones();
                listnodo.setGrafo(grafo); 
                listnodo.setModal(true);
                listnodo.setVisible(true);
            }
        });
        mnNewMenu.add(mntmNewMenuItem_1);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Modificar Ubicacion");
        mnNewMenu.add(mntmNewMenuItem_2);

        JMenu mnNewMenu_1 = new JMenu("Calculo de ruta");
        menuBar.add(mnNewMenu_1);

        JMenuItem mntmNewMenuItem_3 = new JMenuItem("Dijkstra");
        mntmNewMenuItem_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if (Grafo.getInstance() == null || Grafo.getInstance().getMisNodos().size() == 0 || Grafo.getInstance().getMisAristas().size() == 0) { 
        			JOptionPane.showMessageDialog(null, "Información no disponible aún !!!", "Error de Creación de Grafo Virtual", JOptionPane.ERROR_MESSAGE); 
        		} else {
            		CalculoDijkstra calcudik = new CalculoDijkstra();
            		calcudik.setModal(true);
            		calcudik.setVisible(true);	
        		}
        	}
        });
        mnNewMenu_1.add(mntmNewMenuItem_3);

        JMenuItem mntmNewMenuItem_4 = new JMenuItem("Bellman-Ford");
        mnNewMenu_1.add(mntmNewMenuItem_4);

        JMenuItem mntmNewMenuItem_5 = new JMenuItem("Floyd-Warshall");
        mnNewMenu_1.add(mntmNewMenuItem_5);

        JMenu mnNewMenu_2 = new JMenu("Optimizacion de Rutas");
        menuBar.add(mnNewMenu_2);

        JMenuItem mntmNewMenuItem_6 = new JMenuItem("Agregar Ruta");
        mnNewMenu_2.add(mntmNewMenuItem_6);

        JMenuItem mntmNewMenuItem_7 = new JMenuItem("Listar Ruta");
        mnNewMenu_2.add(mntmNewMenuItem_7);

        JMenuItem mntmNewMenuItem_8 = new JMenuItem("Modificar Ruta");
        mnNewMenu_2.add(mntmNewMenuItem_8);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
    }
}
