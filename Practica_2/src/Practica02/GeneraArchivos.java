package Practica02;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Para la generación de archivos aleatorios hemos seguido la estructura necesaria para la ejecución de los métodos implementados.
 * Hemos usado clases de java para la escritura del archivo en formato .txt
 * Dado el parámetro n por el usuario, se genera un archivo con n nodos y sus respectivas aristas con pesos aleatorios usando Math.Random con 
 * un valor entre 0 y 1000.
 * @param nombre : nombre para la generación del archivo
 * @param n : entero para representar el número de nodos que vamos a usar en el fichero
 * @param i : nodo origen 
 * @param j : nodo destino 
 * @return booleano true si se ha realizado correctamente el método y false para el caso contrario
 */

public class GeneraArchivos {
	
	public  boolean genArchivo(int n,String nombre) {
		String directorioEntrada = System.getProperty("user.dir") + File.separator +"src"+File.separator+"dataset"+File.separator;
			try(FileWriter fw = new FileWriter(directorioEntrada+nombre+".txt", true);
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw))
				    {
				        out.println("@Type");
				        out.println("Not Directed");
				        out.println("@Vertex");
				        for(int i=1; i<=n; i++) {
				        	out.println(i);
				        }
				        out.println("@Edges");
				        for(int i=1; i<=n;i++) {
				        	for(int j=i+1; j<=n; j++) {		        	
				        		out.println(i +" "+ j+" " + (int) (Math.random()*1000+1));
				        	}
				        }
				        return true;
				} catch (IOException e) {
				    //añadir excepción
					return false;
				}
		}
	
}
