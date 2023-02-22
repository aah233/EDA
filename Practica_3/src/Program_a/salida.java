package Program_a;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class salida {
/**
 * Método para obtener los resultados del problema planteado
 * @param sol : solución del problema
 * @param i: indice para el recorrido de posibles soluciones
 */
	public static void main(String[] args) throws FileNotFoundException {
		double ini = 0;
		double fin = 0;
		double res1 = 0;
		double res2 = 0;
			apartdadoA o = new apartdadoA();
			 o.ProblemaMochila_Llenado1();
			 o.ToString();
				for (int x=0;x<3;x++) {
					ini = System.nanoTime();
			    ArrayList<Integer> sol = o.Solucion1();
				fin = System.nanoTime();
				res1 = fin - ini;
				res2 = res2+res1;
				}
				res2=res2/3;
				System.out.println("Tiempo resultado: "+res2);
			 /*for(int i : sol) {
				 System.out.println(i);
			 }*/
	}
}


