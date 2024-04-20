package logico;

public class Arista {
    
	private int codigo;
    private Nodo ubicacionOrigen;
    private Nodo ubicacionDestino;
    private int peso;
    private int tiempo;
	private static int codigoArista = 1;
    
	public Arista(Nodo ubicacionOrigen, Nodo ubicacionDestino, int peso, int tiempo) {
		super();
		this.codigo = codigoArista++;
		this.ubicacionOrigen = ubicacionOrigen;
		this.ubicacionDestino = ubicacionDestino;
		this.peso = peso;
		this.tiempo = tiempo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public Nodo getUbicacionOrigen() {
		return ubicacionOrigen;
	}
	
	public void setUbicacionOrigen(Nodo ubicacionOrigen) {
		this.ubicacionOrigen = ubicacionOrigen;
	}
	
	public Nodo getUbicacionDestino() {
		return ubicacionDestino;
	}
	
	public void setUbicacionDestino(Nodo ubicacionDestino) {
		this.ubicacionDestino = ubicacionDestino;
	}
	
	public int getPeso() {
		return peso;
	}
	
	public void setPeso(int peso) {
		this.peso = peso;
	}

	public static int getCodigoArista() {
		return codigoArista;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
}
