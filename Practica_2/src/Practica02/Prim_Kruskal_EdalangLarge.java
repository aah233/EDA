package Practica02;

import java.io.File;
import java.util.ArrayList;

public class Prim_Kruskal_EdalangLarge {
	public static void main (String [ ] args) {
		String directorioEntrada = System.getProperty("user.dir") + File.separator +"src"+File.separator+"dataset"+File.separator;
		RoadNetwork r = new RoadNetwork();
		System.out.println("Grafo de Edalang");
    	r.load(directorioEntrada+"graphEDAlandLarge.txt");
    	ArrayList<Arista> MST2=r.Prim("1");
    	System.out.println("Algoritmo de Prim");
    	for(Arista a : MST2) {
    		System.out.println("Origen "+a.origen+" Destino "+a.destino+" Peso "+a.peso);
    	}
    	System.out.println("\n");
    	System.out.println("Algoritmo de Kruskal");
    	ArrayList<Arista> MST=r.KruskalPriority();
    	for(Arista a : MST) {
    		System.out.println("Origen "+a.origen+" Destino "+a.destino+" Peso "+a.peso);
    	}
    	System.out.println("\n");
    	System.out.println("Algoritmo de Kruskal Priority Queque");
    	ArrayList<Arista> MST4=r.KruskalPriority();
    	for(Arista a : MST4) {
    		System.out.println("Origen "+a.origen+" Destino "+a.destino+" Peso "+a.peso);
    	}
	}
}
