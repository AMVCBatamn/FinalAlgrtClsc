package visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logico.Arista;
import logico.Grafo;
import logico.Nodo;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class DibujarGrafo extends JPanel {

	private Grafo grafo;
	private Nodo selected;
	private Point mouseCursor;
	private static int escala = 10;
	private static int mid = 10;
	
	public DibujarGrafo(Grafo grafo) {
		
		if (grafo != null) {
	        this.grafo = grafo;
	    } else {
	        
	    	if (grafo == null) {
	    		JOptionPane.showMessageDialog(null, "Información no disponible aún !!!", "Error de Creación de Grafo Virtual", JOptionPane.ERROR_MESSAGE);
	    		System.exit(0);
	    	}
	    }
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				selected = null;
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				selected = getNodoAt(e.getPoint());
				if (selected != null) {
					mouseCursor = new Point();
				}
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (selected != null) {
					selected.setLonguitud((e.getPoint().x - mouseCursor.x) / mid);
					selected.setLatitud((e.getPoint().y - mouseCursor.y) / mid);
					repaint();
				}
			}
		});
	}
	
	private Nodo getNodoAt(Point p) {
		
		Nodo aux = null;
	    boolean encontrado = false;
	    int i = 0;

	    while (!encontrado && i < grafo.getMisNodos().size()) {
	        
	    	Nodo nodo = grafo.getMisNodos().get(i);
	        double x = nodo.getLonguitud() * 10;
	        double y = nodo.getLatitud() * 10;
	        
	        if (p.distance(new Point((int) x, (int) y)) <= 25) {
	        	aux = nodo;
	            encontrado = true;
	        }
	        i++;
	    }

	    return aux;
	}
	
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarGrafo((Graphics2D) g);
    }
	
	private void dibujarGrafo(Graphics2D g2d) {
		
        for (Arista arista : grafo.getMisAristas()) {
        	g2d.setColor(Color.BLACK);

            double x1 = arista.getUbicacionOrigen().getLonguitud()*mid;
            double y1 = arista.getUbicacionOrigen().getLatitud()*mid;

            double x2 = arista.getUbicacionDestino().getLonguitud()*mid;
            double y2 = arista.getUbicacionDestino().getLatitud()*mid;

            g2d.draw(new Line2D.Double(x1, y1, x2, y2));
            
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,14));
            String peso = String.valueOf(arista.getPeso());
            
            g2d.drawString(peso, (int)(x1 + x2)/2, (int)(y1 +y2)/2);
        }
        
        for (Nodo nodo : grafo.getMisNodos()) {
        	g2d.setColor(Color.LIGHT_GRAY);

            double x = nodo.getLonguitud()*mid;
            double y = nodo.getLatitud()*mid;

            Ellipse2D circle = new Ellipse2D.Double((int)x-mid, (int)y-mid, 2*escala, 2*escala);

            g2d.fill(circle);
            
            g2d.setColor(Color.BLACK);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,14));
            
            g2d.drawString(nodo.getNombreUbicacion(), (int) x, (int) y);
        }
    }
	
	public static void main(String[] args) {
        JFrame frame = new JFrame("Dibujar Grafo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400); 
        frame.setLocationRelativeTo(null); 
        
        ///Prueba!!!!!!!
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

        grafo.insertarArista(arista1);
        grafo.insertarArista(arista2);
        grafo.insertarArista(arista3);
        
        DibujarGrafo dibujo = new DibujarGrafo(null);
        frame.getContentPane().add(dibujo); 

        frame.setVisible(true);
    }
}

