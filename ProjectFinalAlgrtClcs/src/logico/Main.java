package logico;

import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		
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
		
		
		// Calcular y mostrar resultados de Dijkstra
	    int[] dist = grafo.calcularDijkstra(grafo.generarMatrizAdyacencia(), grafo.buscarIndexByNombre("Moca"));
	    grafo.imprimirResultadosDijkstra(dist, "Moca");

	    // Calcular y mostrar resultados de Kruskal
	    ArrayList<Arista> aristasKruskal = grafo.calcularKruskal();
	    grafo.imprimirAristas(aristasKruskal, true, true, "Kruskal");

	    // Calcular y mostrar resultados de Prim
	    ArrayList<Arista> aristasPrim = grafo.calcularPrim();
	    grafo.imprimirAristas(aristasPrim, true, true, "Prim");

	    // Calcular y mostrar resultados de Floyd Warshall
	    int[][] distanciasFloyd = grafo.calcularFloydWarchall();
	    System.out.println("\nMatriz Floyd Warshall: ");
	    grafo.imprimirMatrizAdyacencia(distanciasFloyd);

		
	    System.out.println("\nMatriz Normal: ");
	    grafo.imprimirMatrizAdyacencia(grafo.generarMatrizAdyacencia());
	    System.out.println();
	    
	    grafo.imprimirAristas(grafo.getMisAristas(), true, true, "Normales");
		
		
		
		
		
		/*
		
		
		///ANTES DE ELIMINAR EL NODO B:
		System.out.println("ANTES DE ELIMINAR EL NODO B:\n");
		System.out.println("Nodos/Ubicaciones en el grafo:");
		
		for (Nodo nodo : grafo.getMisNodos()) {
		    System.out.println("Ubicación: " + nodo.getNombreUbicacion() + " con código: " + nodo.getCodigo() + " y con valor: " + nodo.getValor());
		}
		
		System.out.println("\nAristas/Conexiones en el grafo:");
		for (Arista a: grafo.getMisAristas()) {
			System.out.println("Origen " + a.getUbicacionOrigen().getNombreUbicacion() +
		                       " destino " + a.getUbicacionDestino().getNombreUbicacion() +
		                       " con distancia de " + a.getPeso());
		}

		System.out.println("\n\n\n");
		
		//PROBANDO DIJKSTRA:
		int [] dist = grafo.calcularDijkstra(grafo.generarMatrizAdyacencia(), grafo.buscarIndexByNombre("Moca"));
		grafo.imprimirResultadosDijkstra(dist, "Moca");
		
		///IMPLEMENTAR LUEGO PARA LAS DOS SELLECCIONES DEL VISUAL 
		int distDest = dist[grafo.buscarIndexByNombre("Santiago")];
		System.out.println("\nSolo distancia a un destino: "+ distDest + "\n\n\n");

		
		// KRUSKAL
        ArrayList<Arista> aristasKruskal = grafo.calcularKruskal();  ///Luego hagabra un metodo para imprimir.

        System.out.println("Aristas del árbol de expansión mínima (Kruskal):");
        for (Arista arista : aristasKruskal) {
            System.out.println("Origen " + arista.getUbicacionOrigen().getNombreUbicacion() +
                    " destino " + arista.getUbicacionDestino().getNombreUbicacion() +
                    " con distancia de " + arista.getPeso());
        }
		
		
		
		
        System.out.println("\n\n\n");
		
        
        
        //PRIM
        
        ArrayList<Arista> aristasPrim = grafo.calcularPrim();
        grafo.imprimirAristasPrim(aristasPrim);
        
        
        
        
		
		//MATRIZ DE ADYACENCIA CON PESO:
		grafo.imprimirMatrizAdyacencia(grafo.generarMatrizAdyacencia());

	
		
		///DESPUES DE ELIMINAR EL NODO B:
		grafo.eliminarNodo(B);
		
		
		System.out.println("\n\n\nDESPUES DE ELIMINAR EL NODO B:\n");
		System.out.println("Nodos/Ubicaciones en el grafo:");
		
		for (Nodo nodo : grafo.getMisNodos()) {
		    System.out.println("Ubicación: " + nodo.getNombreUbicacion() + " con código: " + nodo.getCodigo() + " y con valor: " + nodo.getValor());
		}
		
		System.out.println("\nAristas/Conexiones en el grafo:");
		for (Arista arista: grafo.getMisAristas()) {
			System.out.println("Origen " + arista.getUbicacionOrigen().getNombreUbicacion() +
		                       " destino " + arista.getUbicacionDestino().getNombreUbicacion() +
		                       " con distancia de " + arista.getPeso());
		}
		
		
		//MATRIZ DE ADYACENCIA CON PESO:
		grafo.imprimirMatrizAdyacencia(grafo.generarMatrizAdyacencia());
		
	*/
	    
	}
	
}
