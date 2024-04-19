package logico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Grafo {
	//
	private ArrayList<Nodo> misNodos;
	private ArrayList<Arista> misAristas;
	private static Grafo grafo = null;
	
	public Grafo() {
		super();
		this.misNodos = new ArrayList<Nodo>();
		this.misAristas = new ArrayList<Arista>();
	}
	
	public static Grafo getInstance() {
		if (grafo == null) {
			grafo = new Grafo();
		}
		return grafo;
	}
	
	public ArrayList<Nodo> getMisNodos() {
		return misNodos;
	}
	
	public void setMisNodos(ArrayList<Nodo> misNodos) {
		this.misNodos = misNodos;
	}
	
	public ArrayList<Arista> getMisAristas() {
		return misAristas;
	}
	
	public void setMisAristas(ArrayList<Arista> misAristas) {
		this.misAristas = misAristas;
	}
	
	public void insertarNodo(Nodo nodo) {
		misNodos.add(nodo);
	}
	
	public void eliminarNodo(Nodo nodo) {
		
		ArrayList<Arista> tempAristas = new ArrayList<Arista>(nodo.getMisAristas());
		
		for(Arista arista: tempAristas) {
			
			Nodo origen = arista.getUbicacionOrigen();
			Nodo destino = arista.getUbicacionDestino();
			
			origen.eliminarArista(arista);
			destino.eliminarArista(arista);
			
			misAristas.remove(arista);
		}
		misNodos.remove(nodo);
	}
	
	public void actualizarNodo(Nodo nodo, String nombre, double longuitud, double latitud) {
		nodo.setNombreUbicacion(nombre);
	    nodo.setLonguitud(longuitud);
	    nodo.setLatitud(latitud);
	}
	
	public void insertarArista(Arista arista) {
		arista.getUbicacionOrigen().insertarArista(arista);
	    arista.getUbicacionDestino().insertarArista(arista);
		misAristas.add(arista);
	}
	
	public void eliminarArista(Arista arista) {
		arista.getUbicacionOrigen().eliminarArista(arista);
		arista.getUbicacionDestino().eliminarArista(arista);
		misAristas.remove(arista);
	}
	
	
	public int [][] generarMatrizAdyacencia(){
		
		int numN = misNodos.size();
	    int[][] matrizAdyacencia = new int[numN][numN];

	    for (int i = 0; i < numN; i++) {
	        for (int j = 0; j < numN; j++) {
	            matrizAdyacencia[i][j] = 0; //No conectado en tal caso.
	        }
	    }

	    for (Arista arista : misAristas) {
	    	
	    	int origen = misNodos.indexOf(arista.getUbicacionOrigen());
	    	int destino = misNodos.indexOf(arista.getUbicacionDestino());
	        matrizAdyacencia[destino][origen] = matrizAdyacencia[origen][destino] = arista.getPeso();
	    }

	    return matrizAdyacencia;
	}
	
	public void imprimirMatrizAdyacencia(int[][] matriz) {
	    
		int filas = matriz.length;
	    int columnas = matriz[0].length;

	    System.out.println("\nMatriz de Adyacencia:");
	   
	    System.out.print("  ");
	    for (int j = 0; j < columnas; j++) {
	        System.out.print(j + " ");
	    }
	    System.out.println();
	    
	    for (int i = 0; i < filas; i++) {
	        
	    	System.out.print(i + " ");
	        
	        for (int j = 0; j < columnas; j++) {
	            System.out.print(matriz[i][j] + " ");
	        }
	        
	        System.out.println();
	    }
	}
	
	public Nodo buscarNodoByNombre(String nombre) {
	    
		Nodo aux = null;
	    boolean encontrado = false;
	    int i = 0;
	    
	    while (!encontrado && i < misNodos.size()) {
	    	if (misNodos.get(i).getNombreUbicacion().equalsIgnoreCase(nombre)) {
	    		encontrado = true;
	    		aux = misNodos.get(i);
	    	}
	    	i++;
	    }
	    return aux;
	}
	
	public int buscarIndexByNombre(String nombre) {
		
		int index = -1;
		boolean encontrado = false;
		int i = 0;
		
		while(!encontrado && i < misNodos.size()) {
			
			if(misNodos.get(i).getNombreUbicacion().equalsIgnoreCase(nombre)) {
				encontrado = true;
				index = i;
			}
			i++;
		}
		return index;
	}
	
	public Arista buscarAristaByCodigo(String codigo) {
		
		Arista aux = null;
		boolean encontrado = false;
		int i = 0;
		
		while (!encontrado && i < misAristas.size()) {
			
			if (misAristas.get(i).getCodigo() == Integer.parseInt(codigo)) {
				encontrado = true;
				aux = misAristas.get(i);
			}
			i++;
		}
		return aux;
	}
	
	//METODOS DIJKSTRA//
	
    public int[] calcularDijkstra(int matrizAdyacencia[][], int origen)  {
    	
    	int numN = misNodos.size();
        int distancia[] = new int[numN];
        boolean[] visitado = new boolean[numN];
   
        for (int i = 0; i < numN; i++) { 
        	distancia[i] = Integer.MAX_VALUE; //Maxima distancia posible, tiende a infinito segun la teoria.
            visitado[i] = false; 
        } 
   
        distancia[origen] = 0; 
        
        for (int j = 0; j < numN - 1; j++) { 
            int u = minimaDistancia(distancia, visitado); 
            visitado[u] = true; 
            for (int v = 0; v < numN; v++) {
            	if (!visitado[v] && matrizAdyacencia[u][v] != 0 && distancia[u] != Integer.MAX_VALUE && distancia[u] + matrizAdyacencia[u][v] < distancia[v]) {
                	distancia[v] = distancia[u] + matrizAdyacencia[u][v];
                } 
            }    
        }   
        return distancia;
    }
    
	private int minimaDistancia(int[] distancia, boolean[] visitado) {
		
		int min = Integer.MAX_VALUE;
		int min_index = -1;
		
		for (int i = 0; i < misNodos.size(); i++) {
			if(visitado[i] == false && distancia[i] <= min) {
				min = distancia[i];
				min_index = i;
			}
		}
		return min_index;
	}
    
	public void imprimirResultadosDijkstra(int distancia[], String ubicacion){
		
		String destino = "";
		int maxLen = 0;
	    
	    for (Nodo nodo : misNodos) {
	        int len = nodo.getNombreUbicacion().length();
	        if (len > maxLen) {
	        	maxLen = len;
	        }
	    }
	    
	    int maxVal = Integer.MIN_VALUE;
	    for (int i = 0; i < distancia.length; i++) {
	        int val = distancia[i];
	        if (val > maxVal) {
	            maxVal = val;
	        }
	    }
		
		System.out.println("Destino: \t Distancia Mínima Desde " + ubicacion + ":"); 
		
        for (int i = 0; i < misNodos.size(); i++) {
        	destino = misNodos.get(i).getNombreUbicacion();
        	System.out.printf("%-" + maxLen + "s \t\t %" + Math.abs(maxLen-maxVal) + "d km %n", destino, distancia[i]);
        }
    }
	
	//METODOS KRUSKAL//
	
	public ArrayList<Arista> calcularKruskal(){
		
		ArrayList<Arista> aristasKruskal = new ArrayList<Arista>();
		ArrayList<Arista> aristasOrdenadas = new ArrayList<Arista>(misAristas);
		
		Collections.sort(aristasOrdenadas,Comparator.comparing(Arista::getPeso));
		
		int[] padres = new int[misNodos.size()];
		
		for (int i = 0; i < misNodos.size(); i++) {
			padres[i] = i;
		}
		
		for (Arista arista: aristasOrdenadas) {
			
			int origen = buscarIndexByNombre(arista.getUbicacionOrigen().getNombreUbicacion());
			int destino = buscarIndexByNombre(arista.getUbicacionDestino().getNombreUbicacion());
			
			if(encontrar(padres, origen) != encontrar(padres, destino)) {
				aristasKruskal.add(arista);
				unir(padres, origen, destino);
			} 
		}
		
		return aristasKruskal;	
	}
	
	private int encontrar(int[] padres, int nodo) {
        
		if (padres[nodo] != nodo) {
            padres[nodo] = encontrar(padres, padres[nodo]);
        }
		
        return padres[nodo];
    }
	
	private void unir(int[] padres, int nodo1, int nodo2) {
		
		int raiz1 = encontrar(padres, nodo1);
        int raiz2 = encontrar(padres, nodo2);
        
        if (raiz1 != raiz2) {
            padres[raiz1] = raiz2;
        }
	}
	
	public void imprimirAristasKruskal(ArrayList<Arista> aristasKruskal) {
	    
		int total = 0;
		
		System.out.println("Aristas del árbol de expansión mínima (Kruskal):");
		
	    for (int i = 0; i < aristasKruskal.size(); i++) {
	        
	    	Arista arista = aristasKruskal.get(i);
	    	
	        System.out.println("Ruta " + (i + 1) + ": Desde " + arista.getUbicacionOrigen().getNombreUbicacion() + " hasta " + 
	        		arista.getUbicacionDestino().getNombreUbicacion() + ", hay una distancia de: " + arista.getPeso() + " km");
	        
	        total += arista.getPeso();
	    }
	    
	    System.out.println("\nCosto total del árbol de expansión mínima: " + total);
	}
	
	//METODOS PRIM//
	
	public ArrayList<Arista> calcularPrim(){
		
		ArrayList<Arista> aristasPrim = new ArrayList<>();
		boolean [] visitados = new boolean[misNodos.size()];
		int [] minPesos = new int[misNodos.size()];
		
		int [] padres = new int [misNodos.size()];
		
		for (int i = 0; i < misNodos.size(); i++) {
			minPesos[i] = Integer.MAX_VALUE;
	    }
		
		minPesos[0] = 0;
		padres[0] = -1;
		
		for (int i = 0; i < misNodos.size()-1; i++) {
			
			int u = minimoPeso(minPesos,visitados);
			visitados[u] = true;
			
			for (Arista arista : misNodos.get(u).getMisAristas()) {
				
				int v = misNodos.indexOf(arista.getUbicacionDestino());
				int miPeso = arista.getPeso();
				
				if (!visitados[v] && miPeso < minPesos[v]) {
					padres[u] = v;
					minPesos[v] = miPeso;
				}
			}
		}
		
		for (int i = 1; i < misNodos.size(); i++) {
			aristasPrim.add(new Arista(misNodos.get(i), misNodos.get(padres[i]), minPesos[i]));
		}

		return aristasPrim;
	}

	private int minimoPeso(int[] minPesos, boolean[] visitados) {
		
		int minimo = Integer.MAX_VALUE;
	    int minIndex = -1;
	    
	    for (int i = 0; i < minPesos.length; i++) {
	        if (!visitados[i] && minPesos[i] < minimo) {
	            minimo = minPesos[i];
	            minIndex = i;
	        }
	    }
	    
	    return minIndex;
	}
	
	public void imprimirAristasPrim(ArrayList<Arista> aristasPrim) {
	    
		int total = 0;
		
		System.out.println("Aristas del árbol de expansión mínima (Prim):");
		
	    for (int i = 0; i < aristasPrim.size(); i++) {
	        
	    	Arista arista = aristasPrim.get(i);
	    	
	        System.out.println("Ruta " + (i + 1) + ": Desde " + arista.getUbicacionOrigen().getNombreUbicacion() + " hasta " + 
	        		arista.getUbicacionDestino().getNombreUbicacion() + ", hay una distancia de: " + arista.getPeso() + " km");
	        
	        total += arista.getPeso();
	    }
	    
	    System.out.println("\nCosto total del árbol de expansión mínima: " + total);
	}
	
	
	//METODOS FLOYD WARSHALL//
	
	public int [][] calcularFloydWarchall(){
		
		int dist[][] = generarMatrizAdyacencia();
		int numN = misNodos.size();
		
		for (int i = 0; i < numN; i++) {
			
			for(int j = 0; j < numN; j++) {
				
				if (i != j && dist[i][j] == 0) {
					dist[i][j] = Integer.MAX_VALUE; // Tratamos los caminos no directos como infinito.
	            }
			}
		}
		
		for (int k = 0; k < numN; k++) {
			
	        for (int i = 0; i < numN; i++) {
	        	
	            for (int j = 0; j < numN; j++) {
	            	
	                if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE && dist[i][k] + dist[k][j] < dist[i][j]) {
	                    dist[i][j] = dist[i][k] + dist[k][j];
	                }
	            }
	        }
	    }
		
		return dist;
	}
	
	public void imprimirAristas(ArrayList<Arista> aristas, boolean mostrarPeso, boolean mostrarTotal, String algoritmo) {
	    int total = 0;
	    System.out.println("Aristas " + algoritmo + ":");
	    System.out.printf("%-8s %-16s %-16s %s%n", "Ruta", "Origen", "Destino", mostrarPeso ? "Peso" : "");

	    for (int i = 0; i < aristas.size(); i++) {
	        Arista arista = aristas.get(i);
	        String ruta = String.valueOf(i + 1);
	        String origen = arista.getUbicacionOrigen().getNombreUbicacion();
	        String destino = arista.getUbicacionDestino().getNombreUbicacion();
	        String peso = mostrarPeso ? String.valueOf(arista.getPeso()) : "";

	        System.out.printf("%-8s %-16s %-16s %s%n", ruta, origen, destino, peso);

	        if (mostrarPeso) {
	            total += arista.getPeso();
	        }
	    }

	    if (mostrarTotal && mostrarPeso) {
	        System.out.println("Costo Total: " + total);
	    }
	}

	
}
