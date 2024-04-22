package logico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Grafo {
	
	private ArrayList<Nodo> misNodos;
	private ArrayList<Arista> misAristas;
	private static Grafo grafo = null;
	private static Grafo mst = null;
	
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
	
	public static Grafo getInstanceMST() {
		if (mst == null) {
			mst = new Grafo();
		}
		return mst;
	}
	
	public static void generarMST(ArrayList<Arista> misAristas) {
		
		mst.misNodos = new ArrayList<>();
		mst.misAristas = new ArrayList<>();
		
		ArrayList<Nodo> nodosUnicos = new ArrayList<Nodo>();
		
		for (Arista arista : misAristas) {
			
			Nodo origen = arista.getUbicacionOrigen();
			Nodo destino = arista.getUbicacionDestino();
			
			if (!nodosUnicos.contains(origen)) {
				nodosUnicos.add(origen);
			}
			
			if (!nodosUnicos.contains(destino)) {
				nodosUnicos.add(destino);
			}
		}
		
		mst.misNodos.addAll(nodosUnicos);
		mst.misAristas.addAll(misAristas);
		
		System.out.println("Nodos únicos:");
        for (Nodo nodo : nodosUnicos) {
            System.out.printf("%-15s", nodo.getNombreUbicacion() + "\t");
        }
        System.out.println();
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
	        System.out.printf("%4s ", j + " ");
	    }
	    System.out.println();
	    
	    for (int i = 0; i < filas; i++) {
	        
	    	System.out.print(i + " ");
	        
	        for (int j = 0; j < columnas; j++) {
	        	if (matriz[i][j] == Integer.MAX_VALUE) {
	                System.out.print("INF" + " ");
	            } else {
	            	System.out.printf("%4s ", matriz[i][j] + " ");
	            }
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
	
	public int buscarAristaIndexByCodigo(int codigo) {
		
		int index = -1;
		boolean encontrado = false;
		int i = 0;
		
		while(!encontrado && i < misAristas.size()) {

			if(misAristas.get(i).getCodigo() == codigo) {
				encontrado = true;
				index = i;
			}
			i++;
		}
		return index;
	}
	
	public boolean existeArista(String origen, String destino, int peso, int tiempo) {
		
		boolean existe = false;
		
		for (Arista arista : misAristas) {
	        if ((arista.getUbicacionOrigen().getNombreUbicacion().equalsIgnoreCase(origen) && arista.getUbicacionDestino().getNombreUbicacion().equalsIgnoreCase(destino)) && arista.getPeso() == peso && arista.getTiempo() == tiempo ||
	            (arista.getUbicacionOrigen().getNombreUbicacion().equalsIgnoreCase(destino) &&arista.getUbicacionDestino().getNombreUbicacion().equalsIgnoreCase(origen) && arista.getPeso() == peso && arista.getTiempo() == tiempo)) {
	            existe = true;
	        }
	    }
	    return existe;
	}
	
	public void actualizarArista(int index, Arista nuevoArista) {
		misAristas.set(index, nuevoArista);
	}
	
	public int obtnerNombreMasLargo(Grafo miGrafo) {
		int maxLength = 0;
		for (Nodo nodo : miGrafo.misNodos) {
			int length = nodo.getNombreUbicacion().length();
			if (length > maxLength) {
		    	maxLength = length;
		    }
		}		
		return maxLength;
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
		
		System.out.println("Destino: \t\t\t Distancia Mínima Desde " + ubicacion + ":"); 
		
        for (int i = 0; i < misNodos.size(); i++) {
        	destino = misNodos.get(i).getNombreUbicacion();
        	if (distancia[i] == Integer.MAX_VALUE) {
                System.out.printf("%-30s \t\t %s%n", destino, "   INF");
            } else {
                System.out.printf("%-30s \t\t %d km%n", destino, distancia[i]);
            }
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
	
	//METODOS PRIM//
	
	public ArrayList<Arista> calcularPrim(){
		
		int numN = misNodos.size();
		int numA = 0;
		int [][] matrizAdyacencia = generarMatrizAdyacencia();
		boolean [] visitados = new boolean[numN];
		
		for (int i = 0; i < misNodos.size(); i++) {
			visitados[i] = false;
	    }
		
		ArrayList<Arista> aristasPrim = new ArrayList<Arista>();
		
		visitados[0] = true;
		
		while ( numA < numN - 1) {
			
			int [] minArista = buscarMinArista(matrizAdyacencia,visitados);
			
			int origen = minArista[0];
			int destino = minArista[1];
			int peso = minArista[2]; 
			
			visitados[destino] = true;
			aristasPrim.add(new Arista(misNodos.get(origen), misNodos.get(destino), peso, 0));
			numA++;
		}
		return aristasPrim;
	}
	
	private int[] buscarMinArista(int[][] matrizAdyacencia, boolean[] visitados) {
		
		int numN = matrizAdyacencia.length;
		int[] minArista = new int[]{0, 0, Integer.MAX_VALUE};
		
		for (int i = 0; i < numN; i++) {
			
			if(visitados[i]) {
				
				for (int j = 0; j < numN; j++) {
					
					if (!visitados[j] && matrizAdyacencia[i][j] != 0 && matrizAdyacencia[i][j] < minArista[2]) {
						minArista = new int[]{i, j, matrizAdyacencia[i][j]};
                    }
				}
			}
		}
		
		return minArista;
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
	    System.out.printf("%-30s %-30s %-30s %s%n", "Ruta", "Origen", "Destino", mostrarPeso ? "Peso" : "");

	    for (int i = 0; i < aristas.size(); i++) {
	        Arista arista = aristas.get(i);
	        String ruta = String.valueOf(i + 1);
	        String origen = arista.getUbicacionOrigen().getNombreUbicacion();
	        String destino = arista.getUbicacionDestino().getNombreUbicacion();
	        String peso = arista.getPeso() == Integer.MAX_VALUE ? "INF" : String.valueOf(arista.getPeso());

	        System.out.printf("%-30s %-30s %-30s %s%n", ruta, origen, destino, peso);

	        if (mostrarPeso && arista.getPeso() != Integer.MAX_VALUE) {
	            total += arista.getPeso();
	        }
	    }

	    if (mostrarTotal && mostrarPeso) {
	        System.out.println("Costo Total: " + (total == Integer.MAX_VALUE ? "INF" : total));
	    }
	}
	
	//METODOS PLANIFICACION DE RUTA//
	
	public int [][] generarMatrizAdyacenciaTiempo(){
		
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
	        matrizAdyacencia[destino][origen] = matrizAdyacencia[origen][destino] = arista.getTiempo();
	    }

	    return matrizAdyacencia;
	}
	
	public void imprimirResultadosDijkstraTiempo(int distancia[], String ubicacion){
		
		String destino = "";
		
		System.out.println("Destino: \t\t\t Tiempo Mínimo Desde " + ubicacion + ":"); 
		
        for (int i = 0; i < misNodos.size(); i++) {
        	destino = misNodos.get(i).getNombreUbicacion();
        	if (distancia[i] == Integer.MAX_VALUE) {
                System.out.printf("%-30s \t\t %s%n", destino, "   INF");
            } else {
                System.out.printf("%-30s \t\t %d min%n", destino, distancia[i]);
            }
        }
    }
}
