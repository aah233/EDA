package Practica02;

public class Arista implements Comparable<Arista>{
	/**
	 * @param origen : nodo origen  
	 * @param destino : nodo destino
	 * @param peso : peso
     */
    String origen;
    String destino;
    double peso;
	public Arista(String origen, String destino, Double peso) {
    	this.origen = origen;
    	this.destino = destino;
    	this.peso = peso;
    }
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public String ToString() {
		return "Origen "+this.origen+" Destino "+this.destino+" Peso "+this.peso;
	}
	/**
	 * sobreescritura del metodo compareTo de Object, necesesario para la implementación de la cola de prioridad
	 * @return : int 
     */
    @Override
	public int compareTo(Arista o) {
		if(this.getPeso()>o.getPeso()) return 1;
		if(this.getPeso()<o.getPeso()) return -1;
		return 0;
	}
    @Override
    public boolean equals(Object o) {
    	Arista a = (Arista ) o;
    	if(this.getDestino().equals(a.getDestino()) && this.getOrigen().equals(a.getOrigen()) && this.getPeso()==a.getPeso() ) return true;
    	return false;
    }
    
}
