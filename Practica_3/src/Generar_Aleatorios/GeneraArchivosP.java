package Generar_Aleatorios;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Para la generaci�n de archivos aleatorios hemos seguido la estructura necesaria para la ejecuci�n de los m�todos implementados.
 * Hemos usado clases de java para la escritura del archivo en formato .txt
 * Dado el directorio, se genera un archivo con un n�mero aleatorio entre 0 y 10000 y a su vez, se generan otros dos archivos que reciben como par�metro max.
 * @param max : n�mero aleatorio que recibe para la generaci�n de los nuevos valores aleatorios del archivo
 * @return booleano true si se ha realizado correctamente el m�todo y false para el caso contrario
 */

public class GeneraArchivosP {
	
	public static  boolean genArchivoP(int max) {
		String directorioEntrada = System.getProperty("user.dir") + File.separator +"src"+File.separator+"dataset"+File.separator;
			try(FileWriter fw = new FileWriter(directorioEntrada+"w"+".txt", true);
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw))
				    {
						for(int i=0;i<max;i++) {
							out.println(Math.random()*10000+1);
						}
							
					 return true;
							
				} catch (IOException e) {
				    //a�adir excepci�n
					return false;
				}
			
			
		}
	
}
