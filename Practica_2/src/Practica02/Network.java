package Practica02;
/**

 * Se han implementado los dos algoritmos de Prim y Kruskal , y también una mejora de Kruskal con cola de prioridad

 * @author: Manuel Megías Briones , Antonio Aranda Hernández, Cristina Peréz Ordoñez

 * @version: 14/04/2022

 */

 
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;


import edaAuxiliar.Graph;

public class Network<Vertex extends Comparable<Vertex>> implements Graph<Vertex>, Iterable<Vertex> {
	 /**

     * Constructor para la creación del grafo 

     * Contiene una serie de parametros de EDA 1 para la formación del grafo 
     * @param directed si es directo o no 
     * @param adjacencyMap nos devuelve la lista de adyacencia del grafo
     */
	private boolean directed; 	// directed = false (unDirected), directed = true (DiGraph)
	
	private TreeMap<Vertex, TreeMap<Vertex, Double>> adjacencyMap; 
	
	public Network(){
		this.directed = true;
		this.adjacencyMap = new TreeMap<Vertex, TreeMap<Vertex, Double>>();
	}
	
   	public Network(boolean uDOrD) { //uDOrD == unDirected Or Directed
  		this.directed = uDOrD;
		this.adjacencyMap = new TreeMap<Vertex, TreeMap<Vertex, Double>>();
	} 

  	public void setDirected(boolean uDOrD) {
  		this.directed = uDOrD;
  	}

  	public boolean getDirected() {
  		return this.directed;
  	}

  	@Override
  	public boolean isEmpty() {
    	return this.adjacencyMap.isEmpty();
  	} 

  	@Override
  	public void clear() {
		this.adjacencyMap.clear();
	}

  	@Override
  	public int numberOfVertices() {
    	return this.adjacencyMap.size();
  	} 

  	@Override
  	public int numberOfEdges() {
  		int count = 0;
  		for (TreeMap<Vertex, Double> itMap : this.adjacencyMap.values())
  			count += itMap.size();
  		return count;
  	} 

  	@Override
  	public boolean containsVertex(Vertex vertex) {
    	return this.adjacencyMap.containsKey(vertex);
  	} 
  	
  	@Override
  	public boolean containsEdge(Vertex v1, Vertex v2) {
    	return this.adjacencyMap.containsKey(v1) && this.adjacencyMap.get(v1).containsKey(v2);
  	} 

  	@Override
  	public double getWeight (Vertex v1, Vertex v2) {
  		TreeMap<Vertex, Double> neighbors = this.adjacencyMap.get(v1);
  		return neighbors == null ? -1 : neighbors.get(v2) == null ? -1 : neighbors.get(v2);
   	} 

  	@Override
  	public double setWeight (Vertex v1, Vertex v2, double w) {
  		TreeMap<Vertex, Double> neighbors = this.adjacencyMap.get(v1);
  		if (neighbors == null) return -1;
  		Double oldWeight = neighbors.get(v2);
  		if (oldWeight == null) return -1;
  		neighbors.put(v2, w);
		return oldWeight;
	}

  	public boolean isAdjacent (Vertex v1, Vertex v2) {
		return (adjacencyMap.containsKey(v1) && adjacencyMap.get(v1).containsKey(v2));
 
	}

  	public boolean addVertex(Vertex vertex) {
        if (this.adjacencyMap.containsKey(vertex)) return false;
        this.adjacencyMap.put(vertex, new TreeMap<Vertex, Double>());
        return true;
  	} 

  	public boolean addEdge(Vertex v1, Vertex v2, double w) {
  		if (!containsVertex(v1) || !containsVertex(v2)) return false;
  		this.adjacencyMap.get(v1).put(v2, w);
       	if (!this.directed) {
        	this.adjacencyMap.get(v2).put(v1, w);
       	}
    	return true;
  	} 

  	public boolean removeVertex(Vertex vertex) {
        if (!containsVertex(vertex)) return false;
        this.adjacencyMap.remove(vertex);
        for (TreeMap<Vertex, Double> aristas : adjacencyMap.values())
			if (aristas.containsKey(vertex))
				aristas.remove(vertex);
				
        this.adjacencyMap.remove(vertex);
        return true;
   	} 

  	public boolean removeEdge (Vertex v1, Vertex v2) {
    	if (!containsEdge(v1,v2)) return false;

    	this.adjacencyMap.get(v1).remove(v2);
    	
    	if (!this.directed) {
        	this.adjacencyMap.get(v2).remove(v1);    		
    	}
    	
    	return true;
  	} 
  	
	@Override
  	public TreeSet<Vertex> vertexSet() {
    	return new TreeSet<Vertex>(this.adjacencyMap.keySet());
  	}
	
  	public TreeSet<Vertex> getNeighbors(Vertex v) {
  		TreeMap<Vertex, Double> neighbors = this.adjacencyMap.get(v);
  		return neighbors == null ? null : new TreeSet<Vertex>(neighbors.keySet()); 
  	}

  	@Override
  	public String toString() {
    	return this.adjacencyMap.toString();
  	}
  	/**
     * Hemos usado una cola de prioridad ya que una de las exigencias del algoritmo es que las aristas esten ordenadas 
     * y por ello el uso de dicha cola. Que se ordena por la sobreescritura del método CompareTo(Object) que ordena la clase Arista según su peso 
     * Tambien incluye un for para la inserción de cada una de las aristas dentro de la cola de prioridad, que no hemos encontrado mejor solución para la inserccción de las mismas
     * @param PriorityQueque<Arista> D :  
     * @param n : entero con el numero de nodos
     * @param Arista aux : que nos genera guarda justo la Arista que vamos a estudiar
     * @param Arista aux_reverso : para eliminar los nodos de repetición(de mismo sentido, pero en orden inverso) 
     * @return ArrayList<Arista> MST : conjunto solución formado por las aristas que lo componen.
     */
	public ArrayList<Arista> KruskalPriority(){
		PriorityQueue<Arista> D = new PriorityQueue<Arista>();
		for(Entry<Vertex, TreeMap<Vertex, Double>> d:this.adjacencyMap.entrySet()) {
			for(Entry<Vertex,Double> j:d.getValue().entrySet())
			D.add(new Arista(d.getKey().toString(),j.getKey().toString(),j.getValue()));
		}
		ArrayList<Arista> MST = new ArrayList<Arista>();
	    int n = this.adjacencyMap.keySet().size();
	    Arista aux;
	    Arista aux_reverso;
	    while(MST.size()<n-1) {
	    	aux = D.poll();
	    	aux_reverso = new Arista(aux.destino,aux.origen,aux.getPeso());
	    	D.remove(aux_reverso);
	    	if(MST.contains(aux)) continue;
	    	MST.add(aux);
	    	
	    }
	    return MST;
	}
	/**
     *@param Hemos usado un TreeSet<Arista> tambien nos lo va a ordenar según el compareTo de la clase Arista, es decir, por peso
     *Los demás parámetros son iguales que los de la implementación anterior
     * @return ArrayList<Arista> MST : conjunto solución formado por las aristas que lo componen.
     */
	public ArrayList<Arista> Kruskal(){
		TreeSet<Arista> D = new TreeSet<Arista>(); 
		for(Entry<Vertex, TreeMap<Vertex, Double>> d:this.adjacencyMap.entrySet()) {
			for(Entry<Vertex,Double> j:d.getValue().entrySet())
			D.add(new Arista(d.getKey().toString(),j.getKey().toString(),j.getValue()));
		}
		ArrayList<Arista> MST = new ArrayList<Arista>();
	    int n = this.adjacencyMap.keySet().size();
	    Arista aux;
	    Arista aux_reverso;
	    while(MST.size()<n-1) {
	    	aux = D.pollFirst();
	    	if(MST.contains(aux)) continue;
	    	MST.add(aux);
	    	if(aux!=null) {
	    		aux_reverso = new Arista(aux.destino,aux.origen,aux.getPeso());
	    		D.remove(aux_reverso);
	    	}
	    	
	    }
	    return MST;
	}
	/**
	 * En ese algoritmo usamos un TreeMap en donde almacenamos el vertice y su peso. enemos el V_menos_S en donde,
	 * Primero preparamos el fuente dentro de D y obtenemos todos los adyacentes, y a continuación se obtiene de todos ellos el menor,
	 * Y se vuelve a preparar ese nodo para que en la siguiente vuelta escoger de nuevo el menor, hasta que la lista de V menos S este vacia. 
     * @param   TreeMap<Vertex, Double> D :  Par de arista y peso 
     * @param   ArrayList<Arista> V_menos_S: conjunto de vertices menos el fuente 
     * @return ArrayList<Arista> MST : conjunto solución formado por las aristas que lo componen.
     */
	public ArrayList<Arista> Prim(Vertex fuente){
	    TreeMap<Vertex, Double> D = new TreeMap<Vertex,Double>(); 
	    ArrayList<Arista> MST = new ArrayList<Arista>(); 
	    ArrayList<Vertex> V_menos_S = new ArrayList<Vertex>();
	    V_menos_S.addAll(this.adjacencyMap.keySet()); 
	    V_menos_S.remove(fuente);
	    for(Vertex vertice :V_menos_S) 
	        if(this.isAdjacent(fuente, vertice)) 
	            D.put(vertice, this.getWeight(fuente, vertice));
	    Entry<Vertex,Double>auxiliar = null;
	    while(!V_menos_S.isEmpty()) {
	        auxiliar = null;
	        for(Entry<Vertex,Double> par:D.entrySet()){
	                if(V_menos_S.contains(par.getKey())) {
	                     if(auxiliar==null || auxiliar.getValue()>par.getValue()) auxiliar = par;
	                }
	        }
	        if(auxiliar != null){
	            MST.add(new Arista(fuente.toString(),auxiliar.getKey().toString(),auxiliar.getValue()));
	            fuente = auxiliar.getKey();
	            V_menos_S.remove(fuente);
	               for(Vertex v:V_menos_S) {
	                if(this.isAdjacent(fuente, v)) {
	                      D.put(v, this.getWeight(fuente, v));
	                 }//fin if1
	             }//fin for
	        } else
	            break;
	    }//fin while
	    return MST;
	}

	public Vertex VerticeAleatorio(Network<Vertex> grafo) {
		Set<Vertex> lista_vertices = grafo.vertexSet();
		int n = (int) Math.random() *(lista_vertices.size()-0);
		int i = 0;
		for(Vertex v:lista_vertices) {
			if(i==n) return v;
				i++;
		}
		return null;
	}
	
	@Override
	public Iterator<Vertex> iterator() { //Iterador sobre el conjunto de claves --> orden lexicografico
        return this.adjacencyMap.keySet().iterator();
  	} 

  	
 }

