package visual;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

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
	private boolean verPeso = true;
	
	public DibujarGrafo(Grafo grafo, boolean verPeso) {
		
		
		if (grafo != null) {
	        this.grafo = grafo;
	        this.verPeso = verPeso;
	        
	    } else {
	    	
	    	JOptionPane.showMessageDialog(null, "Información no disponible aún !!!", "Error de Creación de Grafo Virtual", JOptionPane.ERROR_MESSAGE);
	    	System.exit(0);	
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
					Point newPoint = e.getPoint();
					int newX = e.getPoint().x - mouseCursor.x;
			        int newY = e.getPoint().y - mouseCursor.y;

			        newX = Math.max(25, Math.min(newX, getWidth() - 25));
			        newY = Math.max(25, Math.min(newY, getHeight() - 25));

			        selected.setLonguitud(newX / mid);
			        selected.setLatitud(newY / mid);
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
	        
	        int ajusteX = (int)Math.max(mid, Math.min(x, getWidth() - mid));
            int ajusteY = (int)Math.max(mid, Math.min(y, getHeight() - mid));
	        
	        if (p.distance(new Point(ajusteX, ajusteY)) <= 25) {
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
            
            String valor = "";
            
            if (verPeso) {
            	valor = String.valueOf(arista.getPeso()); 
            	if (arista.getPeso() == Integer.MAX_VALUE) {
            		valor = "INF";
            	}
            } else {
            	valor = String.valueOf(arista.getTiempo());
            }
            
            g2d.drawString(valor, (int)(x1 + x2)/2, (int)(y1 +y2)/2);
        }
		
        for (Nodo nodo : grafo.getMisNodos()) {
        	g2d.setColor(Color.LIGHT_GRAY);

            double x = nodo.getLonguitud()*mid;
            double y = nodo.getLatitud()*mid;
            
            int ajusteX = (int)Math.max(mid, Math.min(x, getWidth() - mid));
            int ajusteY = (int)Math.max(mid, Math.min(y, getHeight() - mid));	
            
            Ellipse2D circle = new Ellipse2D.Double(ajusteX-mid, ajusteY-mid, 2*escala, 2*escala);

            g2d.fill(circle);
            
            g2d.setColor(Color.BLACK);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,14));
            
            g2d.drawString(nodo.getNombreUbicacion(),ajusteX,ajusteY);
        }
    }	
}

