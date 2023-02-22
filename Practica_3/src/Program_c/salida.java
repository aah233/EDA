package Program_c;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class salida {
	/**
	 * Método para obtener los resultados del problema planteado
	 * @param sol : solución del problema
	 * @param i: indice para el recorrido de posibles soluciones
	 */
	public static void main(String[] args) throws FileNotFoundException {
			apartdadoC o = new apartdadoC();
			 o.ProblemaMochila_Llenado2();
			 o.ToString();
			  ArrayList<Integer> sol = o.Solucion2();
			 for(int i : sol) {
				 System.out.println(i);
			 }
	}
}


