package TSP_BaB;

import java.util.ArrayList;
import java.util.Iterator;

class PathNode implements Comparable<PathNode> {
    private ArrayList<String> res; // result
    private int visitedVertices; // The number of the visited vertices
    private double totalCost; // 
    private double estimatedCost; // Cota inferior

    PathNode(String vertexToVisit) {
        this.res = new ArrayList<String>();
        this.res.add(vertexToVisit);
        visitedVertices = 1;
        totalCost = 0.0;
       estimatedCost = 1E10;
    }

    PathNode(PathNode parentPathNode) {
        // Constructor copia
    	this.res = new ArrayList<String>(parentPathNode.res);
    	this.visitedVertices=parentPathNode.visitedVertices;
    	this.totalCost= parentPathNode.totalCost;
    	this.estimatedCost=parentPathNode.estimatedCost;
    }

    @Override
    public int compareTo(PathNode p) {
    	return Double.compare(this.getEstimatedCost(), p.getEstimatedCost());
        // El criterio de comparacion es 'estimatedCost' que se correponde con la prioridad
    }

    public ArrayList<String> getRes() {
        return res;
    }

    public void addVertexRes(String v) {
        this.res.add(v);
    }

    public String lastVertexRes() {
        // Devuelve el ultimo vertice que se ha anadido al camino (ultimo elemento de 'res')
    	return res.get(res.size()-1);
	}

    public boolean isVertexVisited(String v) {
    	// Se ha visitado el vertice v si esta actualmente en 'res'. Una sola linea
    	return res.contains(v);
	}

    public int getVisitedVertices() {
        return visitedVertices;
    }

    public void setVisitedVertices(int visitedVertices) {
        this.visitedVertices = visitedVertices;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }
} 
