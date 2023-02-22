package TSP_BaB;
/**

 

 * @author: Manuel Megías Briones , Antonio Aranda Hernández, Cristina Peréz Ordoñez

 * @version: 03/06/2022

 */

 
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.io.File;
import java.util.ArrayList;


import edaAuxiliar.Graph;

public class Network implements Graph<String>, Iterable<String> {
	 /**

     * Constructor para la creación del grafo 

     * Contiene una serie de parametros de EDA 1 para la formación del grafo 
     * @param directed si es directo o no 
     * @param adjacencyMap nos devuelve la lista de adyacencia del grafo
     */
	private boolean directed; 	// directed = false (unDirected), directed = true (DiGraph)
	
	private TreeMap<String, TreeMap<String, Double>> adjacencyMap; 
	
	public Network(){
		this.directed = true;
		this.adjacencyMap = new TreeMap<String, TreeMap<String, Double>>();
	}
	
   	public Network(boolean uDOrD) { //uDOrD == unDirected Or Directed
  		this.directed = uDOrD;
		this.adjacencyMap = new TreeMap<String, TreeMap<String, Double>>();
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
  		for (TreeMap<String, Double> itMap : this.adjacencyMap.values())
  			count += itMap.size();
  		return count;
  	} 

  	@Override
  	public boolean containsVertex(String vertex) {
    	return this.adjacencyMap.containsKey(vertex);
  	} 
  	
  	@Override
  	public boolean containsEdge(String v1, String v2) {
    	return this.adjacencyMap.containsKey(v1) && this.adjacencyMap.get(v1).containsKey(v2);
  	} 

  	@Override
  	public double getWeight (String v1, String v2) {
  		TreeMap<String, Double> neighbors = this.adjacencyMap.get(v1);
  		return neighbors == null ? -1 : neighbors.get(v2) == null ? -1 : neighbors.get(v2);
   	} 

  	@Override
  	public double setWeight (String v1, String v2, double w) {
  		TreeMap<String, Double> neighbors = this.adjacencyMap.get(v1);
  		if (neighbors == null) return -1;
  		Double oldWeight = neighbors.get(v2);
  		if (oldWeight == null) return -1;
  		neighbors.put(v2, w);
		return oldWeight;
	}

  	public boolean isAdjacent (String v1, String v2) {
		return (adjacencyMap.containsKey(v1) && adjacencyMap.get(v1).containsKey(v2));
 
	}

  	public boolean addVertex(String vertex) {
        if (this.adjacencyMap.containsKey(vertex)) return false;
        this.adjacencyMap.put(vertex, new TreeMap<String, Double>());
        return true;
  	} 

  	public boolean addEdge(String v1, String v2, double w) {
  		if (!containsVertex(v1) || !containsVertex(v2)) return false;
  		this.adjacencyMap.get(v1).put(v2, w);
       	if (!this.directed) {
        	this.adjacencyMap.get(v2).put(v1, w);
       	}
    	return true;
  	} 

  	public boolean removeVertex(String vertex) {
        if (!containsVertex(vertex)) return false;
        this.adjacencyMap.remove(vertex);
        for (TreeMap<String, Double> aristas : adjacencyMap.values())
			if (aristas.containsKey(vertex))
				aristas.remove(vertex);
				
        this.adjacencyMap.remove(vertex);
        return true;
   	} 

  	public boolean removeEdge (String v1, String v2) {
    	if (!containsEdge(v1,v2)) return false;

    	this.adjacencyMap.get(v1).remove(v2);
    	
    	if (!this.directed) {
        	this.adjacencyMap.get(v2).remove(v1);    		
    	}
    	
    	return true;
  	} 
  	
	@Override
  	public TreeSet<String> vertexSet() {
    	return new TreeSet<String>(this.adjacencyMap.keySet());
  	}
	
  	public TreeSet<String> getNeighbors(String v) {
  		TreeMap<String, Double> neighbors = this.adjacencyMap.get(v);
  		return neighbors == null ? null : new TreeSet<String>(neighbors.keySet()); 
  	}

  	@Override
  	public String toString() {
    	return this.adjacencyMap.toString();
  	}
  	
	@Override
	public Iterator<String> iterator() { //Iterador sobre el conjunto de claves --> orden lexicografico
        return this.adjacencyMap.keySet().iterator();
  	} 
	
	/**Aquí comenzamos la practica de TSP**/


	// return the minimum value of the edges
  	public double minimumEdgeValue() {
  		double minimum = Double.MAX_VALUE;
  		// Devuelve el menor valor de arista del grafo
  		for(Entry<String,TreeMap<String,Double>>d:this.adjacencyMap.entrySet()) {
  			for(Entry<String,Double> h:d.getValue().entrySet()) {
  					if(h.getValue()< minimum) minimum = h.getValue();
  			}
  		}
  		return minimum;
  	}

  	// TSP - BaB - Best-First
	public PathNode TSPBaB(String source) {
		TreeMap<String, Double> neighborMap = adjacencyMap.get(source);
		if (neighborMap == null)
			return null;

		Double minEdgeValue = minimumEdgeValue();

		// Constructor de clase PathNode
		PathNode firstNode = new PathNode(source);

		PriorityQueue<PathNode> priorityQueue = new PriorityQueue<>();

		priorityQueue.add(firstNode);

		PathNode shortestCircuit = null;
		double tiempo_imprimir = 6.00E10;
		double bestCost = Double.MAX_VALUE;
        double tiempo = System.nanoTime();
        double tiempo_llevado;
        int nodos_explorados = 0;
		while(priorityQueue.size() > 0) {
			// Y (PathNode) = menorElemento de la cola de prioridad en funcion de 'estimatedCost'
			PathNode Y = priorityQueue.poll();
			nodos_explorados ++;
			//System.out.print(Y.getVisitedVertices()+" "+ numberOfVertices() +" -> "+priorityQueue.size()+" -> "+Y.lastVertexRes()+" -> "+Y.getRes().toString());
			//System.out.println(" ");
			tiempo_llevado= System.nanoTime()-tiempo;
			//descomenta el if en el grande 
			//if((System.nanoTime()-tiempo )> tiempo_imprimir) {
				tiempo_imprimir +=tiempo_imprimir; 
				System.out.println("Tiempo llevado para "+ nodos_explorados+ " nodos es "+tiempo_llevado);
				System.out.println(((Runtime.getRuntime().freeMemory()/1024)/1024)+" MB disponibles");
			//}
			
			if (Y.getEstimatedCost() >= bestCost) {
				//System.out.println("podo1 "+Y.toString());
				break;//poda
			}
                
			else {
				String from = Y.lastVertexRes();
				// Si el numero de vertices visitados es n
				// y existe una arista que conecte 'from' con sourc
				if ((Y.getVisitedVertices() == numberOfVertices()) &&
					(containsEdge(from, source))) {
					//System.out.println("Aquí he entrado");
					 Y.addVertexRes(source);// Actualizar 'res' en Y añadiendo el vertice 'source'
					 Y.setTotalCost(Y.getTotalCost()+ getWeight(from,source));// Actualizar 'totalCost' en Y con Y.totalCost + weight(from, source)
					if (Y.getTotalCost() < bestCost) {
						//System.out.println("Aquí he entrado dos");
						// Actualizar 'bestCost', 'shortestDistanceCircuit' y 'shortestCircuit'
						bestCost = Y.getTotalCost(); //Actualizamos 'bestCost'
						shortestCircuit = new PathNode(Y); //Actualizamos shortestCircui
					     
					}
				}else {
					// Iterar para todos los vertices adyacentes a from,
					// a cada vertice lo denominamos 'to',vertice 'to' todavia no ha sido visitado en Y
					for( String to : this.adjacencyMap.get(from).keySet()) {
						if (!Y.isVertexVisited(to)) { // hacer uso de la funcion 'isVertexVisited(vertex)' de PathNode
							nodos_explorados++;
							PathNode X = new PathNode(Y); // Uso de constructor copia
							// Anadir 'to' a 'res' en X
							X.addVertexRes(to);
							// Incrementar en 1 los vertices visitados en X
							X.setVisitedVertices(X.getVisitedVertices()+1);
							// Actualizar 'totalCost' en X con Y.totalCost + weight(from, to)
							X.setTotalCost(Y.getTotalCost() + getWeight(from, to));
							// Actualizar 'estimatedCost' en X con X.totalCost + ((nVertices - X.getVisitedVertices() + 1) * minEdgeValue)
							double total = X.getTotalCost() + ((numberOfVertices() - X.getVisitedVertices() + 1) * minEdgeValue);
							X.setEstimatedCost(total);
							if (X.getEstimatedCost() < bestCost) {
								priorityQueue.add(X);
							}else {
								//System.out.println("podo2 "+X.toString());
							}
						}
					  }//for
					}//fin else 
				}
			}
			return shortestCircuit;
	}
	
	public void mostrar_grafo() {
		for(Entry<String,TreeMap<String,Double>>d:this.adjacencyMap.entrySet()) {
  			for(Entry<String,Double> h:d.getValue().entrySet()) {
  					System.out.print(d.getKey().toString()+" "+h.getKey().toString()+" "+h.getValue());
  					System.out.println(" ");
  			}
		}
	}
	 public void load(String filename){
	        String line = "";
	        Scanner scan = null;
	        String tipo="";
	        String directorioEntrada = System.getProperty("user.dir") + File.separator +"src"+File.separator+"dataset"+File.separator;
	        try{
	            scan = new Scanner(new File(directorioEntrada+filename));
	        }catch(Exception e){
	            System.out.println("Error al cargar el archivo --> " + e.getMessage());
	            System.exit(-1);
	        }

	        while(scan.hasNextLine()){
	        	this.directed =false;
	            line = scan.nextLine().trim();
	            if (line.isEmpty()) continue;
	            if (line.startsWith("%")) continue;

	            if (line.toLowerCase().contains("@type")){
	               tipo = "type";
	                continue;
	            }
	            if (line.toLowerCase().contains("@vertex")){
	                tipo = "vertex";
	                continue;
	            }
	            if (line.toLowerCase().contains("@edges")){
	                tipo= "edges";
	                continue;
	            }
	           if(tipo.equals("vertex")) {
	        	   this.addVertex(line);
	           }else if(tipo.equals("edges")){
	        	   String[] words = line.split("[ ]+");
                   this.addEdge(words[0], words[1], Double.parseDouble(words[2]));
	           }
	        }
	    }
 }

