package Generar_Aleatorios;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Para la generación de archivos aleatorios hemos seguido la estructura necesaria para la ejecución de los métodos implementados.
 * Hemos usado clases de java para la escritura del archivo en formato .txt
 * Dado el directorio, se genera un archivo con un número aleatorio entre 0 y 10000 y a su vez, se generan otros dos archivos que reciben como parámetro max.
 * @param max : número aleatorio que representa el valor máximo a obtener entre los valores aleatorios de los archivos p y w
 * @return booleano true si se ha realizado correctamente el método y false para el caso contrario
 */

public class GeneraArchivos {
	
	public  boolean genArchivoC() {
		String directorioEntrada = System.getProperty("user.dir") + File.separator +"src"+File.separator+"dataset"+File.separator;
			try(FileWriter fw = new FileWriter(directorioEntrada+"c"+".txt", true);
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw))
				    {
						int max=(int)(Math.random()*10000+1);
						out.println(max);
						GeneraArchivosW.genArchivoW(max);	
						GeneraArchivosP.genArchivoP(max);	
					 return true;
							
				} catch (IOException e) {
				    //añadir excepción
					return false;
				}
			
			
		}
	
}
