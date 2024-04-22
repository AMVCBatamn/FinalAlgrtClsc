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

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
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
    	
    	//DUMMY DATA V1//
    	
    	Grafo grafo = Grafo.getInstance();
    	
    	Nodo A = new Nodo(10, "Moca", 20.5, 20.3);
    	Nodo B = new Nodo(23, "La Vega", 35.2, 25.7);
    	Nodo C = new Nodo(13, "Santiago", 50.8, 30.1);
    	Nodo D = new Nodo(17, "Puerto Plata", 12.0, 18.5);
    	Nodo E = new Nodo(8, "Jarabacoa", 58.9, 23.4);
    	Nodo F = new Nodo(21, "Barahona", 94.7, 19.2);
    	Nodo G = new Nodo(29, "San Pedro ", 18.3, 29.8);
    	Nodo H = new Nodo(7, "Higuey", 20.1, 32.6);
    	Nodo I = new Nodo(15, "Santo Domingo", 25.6, 35.2);
    	Nodo J = new Nodo(19, "San Francisco", 30.4, 40.1);

    	grafo.insertarNodo(A);
    	grafo.insertarNodo(B);
    	grafo.insertarNodo(C);
    	grafo.insertarNodo(D);
    	grafo.insertarNodo(E);
    	grafo.insertarNodo(F);
    	grafo.insertarNodo(G);
    	grafo.insertarNodo(H);
    	grafo.insertarNodo(I);
    	grafo.insertarNodo(J);
		
		Arista arista1 = new Arista(A, B, 5, 10);
		Arista arista2 = new Arista(B, C, 8, 20);
		Arista arista3 = new Arista(C, D, 1, 30);
		Arista arista4 = new Arista(D, E, 6, 15);
		Arista arista5 = new Arista(E, F, 9, 25);
		Arista arista6 = new Arista(F, G, 3, 35);
		Arista arista7 = new Arista(G, H, 4, 12);
		Arista arista8 = new Arista(H, I, 7, 22);
		Arista arista9 = new Arista(I, J, 2, 18);
		Arista arista10 = new Arista(J, B, 11, 28);
		Arista arista11 = new Arista(A, C, 13, 40);
		Arista arista12 = new Arista(D, F, 14, 45);
		Arista arista13 = new Arista(G, E, 5, 17);
		Arista arista14 = new Arista(H, J, 8, 24);
		Arista arista15 = new Arista(I, E, 3, 10);

		grafo.insertarArista(arista1);
		grafo.insertarArista(arista2);
		grafo.insertarArista(arista3);
		grafo.insertarArista(arista4);
		grafo.insertarArista(arista5);
		grafo.insertarArista(arista6);
		grafo.insertarArista(arista7);
		grafo.insertarArista(arista8);
		grafo.insertarArista(arista9);
		grafo.insertarArista(arista10);
		grafo.insertarArista(arista11);
		grafo.insertarArista(arista12);
		grafo.insertarArista(arista13);
		grafo.insertarArista(arista14);
		grafo.insertarArista(arista15);

    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 328);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setToolTipText("");
        setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("Gestión de Ubicaciones");
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewMenuItem = new JMenuItem("Agregar Ubicación");
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {   	
            	AgregarUbicacion agregarnodo = new AgregarUbicacion();
            	agregarnodo.setModal(true);
            	agregarnodo.setVisible(true);	
            }
        });

        mnNewMenu.add(mntmNewMenuItem);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Listar Ubicaciones");
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	if (Grafo.getInstance() == null || Grafo.getInstance().getMisNodos().size() == 0 || Grafo.getInstance().getMisAristas().size() == 0) {
        			JOptionPane.showMessageDialog(null, "Información no disponible aún !!!", "Error de Operación", JOptionPane.ERROR_MESSAGE);
        		} else {
                	ListarUbicaciones listnodo = new ListarUbicaciones();            
                    listnodo.setModal(true);
                    listnodo.setVisible(true);
        		}
            }
        });
        mnNewMenu.add(mntmNewMenuItem_1);
        
        JMenu mnNewMenu_3 = new JMenu("Gestión de Conexiones");
        menuBar.add(mnNewMenu_3);
        
        JMenuItem mntmNewMenuItem_9 = new JMenuItem("Agregar Conexión");
        mntmNewMenuItem_9.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if (Grafo.getInstance() == null || Grafo.getInstance().getMisNodos().size() == 0 || Grafo.getInstance().getMisAristas().size() == 0) {
        			JOptionPane.showMessageDialog(null, "Información no disponible aún !!!", "Error de Operación", JOptionPane.ERROR_MESSAGE);
        		} else {
            		AgregarConexion conexion = new AgregarConexion();
            		conexion.setModal(true);
            		conexion.setVisible(true);
        		}
        	}
        });
        mnNewMenu_3.add(mntmNewMenuItem_9);
        
        JMenuItem mntmNewMenuItem_10 = new JMenuItem("Listar Conexiones");
        mntmNewMenuItem_10.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if (Grafo.getInstance() == null || Grafo.getInstance().getMisNodos().size() == 0 || Grafo.getInstance().getMisAristas().size() == 0) {
        			JOptionPane.showMessageDialog(null, "Información no disponible aún !!!", "Error de Operación", JOptionPane.ERROR_MESSAGE);
        		} else {
            		ListarConexion listarConex = new ListarConexion();
            		listarConex.setModal(true);
            		listarConex.setVisible(true);
        		}
        	}
        });
        mnNewMenu_3.add(mntmNewMenuItem_10);

        JMenu mnNewMenu_1 = new JMenu(" Cálculo de ruta");
        menuBar.add(mnNewMenu_1);

        JMenuItem mntmNewMenuItem_3 = new JMenuItem("Cálculo Dijkstra");
        mntmNewMenuItem_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if (Grafo.getInstance() == null || Grafo.getInstance().getMisNodos().size() == 0 || Grafo.getInstance().getMisAristas().size() == 0) { 
        			JOptionPane.showMessageDialog(null, "Información no disponible aún !!!", "Error de Operación", JOptionPane.ERROR_MESSAGE); 
        		} else {
            		CalculoDijkstra calcudik = new CalculoDijkstra(false);
            		calcudik.setModal(true);
            		calcudik.setVisible(true);	
        		}
        	}
        });
        mnNewMenu_1.add(mntmNewMenuItem_3);

        JMenuItem mntmNewMenuItem_5 = new JMenuItem("Cálculo Prim");
        mntmNewMenuItem_5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if (Grafo.getInstance() == null || Grafo.getInstance().getMisNodos().size() == 0 || Grafo.getInstance().getMisAristas().size() == 0) { 
        			JOptionPane.showMessageDialog(null, "Información no disponible aún !!!", "Error de Operación", JOptionPane.ERROR_MESSAGE); 
        		} else {
            		CalculoPrim calcuprim = new CalculoPrim();
            		calcuprim.setVisible(true);	
        		}
        	}
        });
        mnNewMenu_1.add(mntmNewMenuItem_5);
        
        JMenuItem mntmNewMenuItem_4 = new JMenuItem("Cálculo  Kruskal");
        mntmNewMenuItem_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if (Grafo.getInstance() == null || Grafo.getInstance().getMisNodos().size() == 0 || Grafo.getInstance().getMisAristas().size() == 0) { 
        			JOptionPane.showMessageDialog(null, "Información no disponible aún !!!", "Error de Operación", JOptionPane.ERROR_MESSAGE); 
        		} else {
            		CalculoKruskal calcukrus = new CalculoKruskal();
            		calcukrus.setModal(true);
            		calcukrus.setVisible(true);	
        		}
        	}
        });
        mnNewMenu_1.add(mntmNewMenuItem_4);

        JMenu mnNewMenu_2 = new JMenu("Optimizacion de Rutas");
        menuBar.add(mnNewMenu_2);
        
        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Cálculo Floyd-Warshall");
        mntmNewMenuItem_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if (Grafo.getInstance() == null || Grafo.getInstance().getMisNodos().size() == 0 || Grafo.getInstance().getMisAristas().size() == 0) { 
        			JOptionPane.showMessageDialog(null, "Información no disponible aún !!!", "Error de Operación", JOptionPane.ERROR_MESSAGE); 
        		} else {
            		VerMatrizAdyacencia calcuFloy = new VerMatrizAdyacencia(grafo,false,true); 
            		calcuFloy.setVisible(true);
        		}
        	}
        });
        mnNewMenu_2.add(mntmNewMenuItem_2);

        JMenuItem mntmNewMenuItem_6 = new JMenuItem("Planificación de Ruta");
        mntmNewMenuItem_6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if (Grafo.getInstance() == null || Grafo.getInstance().getMisNodos().size() == 0 || Grafo.getInstance().getMisAristas().size() == 0) { 
        			JOptionPane.showMessageDialog(null, "Información no disponible aún !!!", "Error de Operación", JOptionPane.ERROR_MESSAGE); 
        		} else {
            		PlanificacionRuta ruta = new PlanificacionRuta();
            		ruta.setVisible(true);
        		}
        	}
        });
        mnNewMenu_2.add(mntmNewMenuItem_6);
        
        JMenu mnNewMenu_4 = new JMenu("Detalles");
        menuBar.add(mnNewMenu_4);
        
        JMenuItem mntmNewMenuItem_7 = new JMenuItem("Ver Matriz Adyacencia");
        mntmNewMenuItem_7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if (Grafo.getInstance() == null || Grafo.getInstance().getMisNodos().size() == 0 || Grafo.getInstance().getMisAristas().size() == 0) { 
        			JOptionPane.showMessageDialog(null, "Información no disponible aún !!!", "Error de Operación", JOptionPane.ERROR_MESSAGE); 
        		} else {
            		VerMatrizAdyacencia matriz = new VerMatrizAdyacencia(grafo,false,false); 
            		matriz.setVisible(true);
        		}
        	}
        });
        mnNewMenu_4.add(mntmNewMenuItem_7);
        
        JMenuItem mntmNewMenuItem_8 = new JMenuItem("Ver Grafo");
        mntmNewMenuItem_8.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if (Grafo.getInstance() == null || Grafo.getInstance().getMisNodos().size() == 0 || Grafo.getInstance().getMisAristas().size() == 0) { 
        			JOptionPane.showMessageDialog(null, "Información no disponible aún !!!", "Error de Operación", JOptionPane.ERROR_MESSAGE); 
        		} else {
            		VerGrafo verGrafo = new VerGrafo();
            		verGrafo.setModal(true);
            		verGrafo.setVisible(true);
        		}
        	}
        });
        mnNewMenu_4.add(mntmNewMenuItem_8);
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
