package logico;

import java.util.ArrayList;

public class Nodo { 
	
	private int codigo;
	private int valor;
	private ArrayList<Arista> misAristas;
	private String nombreUbicacion;
	private double longuitud;
	private double latitud;
	private static int codigoNodo = 1;
	
	public Nodo(int valor, String nombreUbicacion, double longuitud, double latitud) {
		super();
		this.codigo = codigoNodo++;
		this.valor = valor;
		this.misAristas = new ArrayList<Arista>();
		this.nombreUbicacion = nombreUbicacion;
		this.longuitud = longuitud;
		this.latitud = latitud;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public ArrayList<Arista> getMisAristas() {
		return misAristas;
	}
	
	public void setMisAristas(ArrayList<Arista> misAristas) {
		this.misAristas = misAristas;
	}
	
	public String getNombreUbicacion() {
		return nombreUbicacion;
	}
	
	public void setNombreUbicacion(String nombreUbicacion) {
		this.nombreUbicacion = nombreUbicacion;
	}
	
	public double getLonguitud() {
		return longuitud;
	}
	
	public void setLonguitud(double longuitud) {
		this.longuitud = longuitud;
	}
	
	public double getLatitud() {
		return latitud;
	}
	
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	
	public void eliminarArista(Arista arista) {
		misAristas.remove(arista);
	}
	
	public void insertarArista(Arista arista) {
		misAristas.add(arista);
	}

	public static int getCodigoNodo() {
		return codigoNodo;
	}
}
