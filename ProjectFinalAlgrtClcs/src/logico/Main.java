package logico;

import java.util.ArrayList;
import java.util.Arrays;


public class Main {

	public static void main(String[] args) {
		
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

		//DATOS GENERADOS//
		
		System.out.println("\nMatriz Normal: ");
	    grafo.imprimirMatrizAdyacencia(grafo.generarMatrizAdyacencia());
	    
	    System.out.println();
	    grafo.imprimirAristas(grafo.getMisAristas(), true, true, "Normales");
		
		//Dijkstra// ==> //Gestion de Rutas (Distancia)
	    System.out.println();
	    int[][] matrizAdyacente = grafo.generarMatrizAdyacencia();
	    int[] dist = grafo.calcularDijkstra(matrizAdyacente, grafo.buscarIndexByNombre("Moca"));
	    grafo.imprimirResultadosDijkstra(dist, "Moca");

	    //Kruskal//
	    System.out.println();
	    ArrayList<Arista> aristaKruskal = Grafo.getInstance().calcularKruskal();
		Grafo.getInstanceMST();
		Grafo.generarMST(aristaKruskal); System.out.println();
	    grafo.imprimirAristas(aristaKruskal, true, true, "Kruskal");

	    //Prim//
	    System.out.println();
	    ArrayList<Arista> aristaPrim = Grafo.getInstance().calcularPrim();
		Grafo.getInstanceMST();
		Grafo.generarMST(aristaKruskal); System.out.println();
	    grafo.imprimirAristas(aristaKruskal, true, true, "Prim");

	    //Floyd Warshall//
	    System.out.println();
	    int[][] distanciasFloyd = grafo.calcularFloydWarchall();
	    System.out.println("\nMatriz Floyd Warshall: ");
	    grafo.imprimirMatrizAdyacencia(distanciasFloyd);

		//Gestion de Rutas (Tiempo)
	    System.out.println();
	    int[][] matrizTiempos = grafo.generarMatrizAdyacenciaTiempo();
        int[] distanciasTiempo = grafo.calcularDijkstra(matrizTiempos, grafo.buscarIndexByNombre("Moca"));
        grafo.imprimirResultadosDijkstraTiempo(distanciasTiempo,"Moca");
	}
}

