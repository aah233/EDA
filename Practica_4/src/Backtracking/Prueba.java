package Backtracking;

import java.util.Queue;
import java.util.Stack;

public class Prueba {

	public static void main(String[] args) {
		double t1=0;
		Practica_Final hola = new Practica_Final("graphEDAlandLarge.txt");
		hola.Backtracking(0);
		System.out.println(hola.soluciones_backtracking.size());
		 System.out.println();
		Stack<Integer> solu = hola.soluciones_backtracking.get(3);
		System.out.println(solu.size());
		//Queue<Integer> o = hola.adyacentes(0);
		//System.out.println(o.poll());
		//System.out.println(o.poll());
		//System.out.println(o.poll());
		//Stack<Integer> ol = hola.soluciones_backtracking.get(0);
		
		//while(!ol.isEmpty()) {
			//System.out.println(ol.pop());
		//}
		
	}

}
