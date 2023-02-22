package Program_d;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Program_a.Objeto;

public class Mochila_voraz {
	ArrayList<Objeto> objetos;
	int maximo_peso;
	ArrayList<Objeto3> objetos_aux;
	/**
	 *  Método para leer e inicializar todas las variables desde los archivos asignandole el peso que tendrá más el valor minimo
	 */
	public Mochila_voraz() throws FileNotFoundException {
		objetos = this.lectura_Archivos1(); //inicializa todas las variables
		objetos_aux = new ArrayList<Objeto3>();
		for(int i=0;i<=objetos.size();i++) {
			for(int j =i; j<i+1;j+=Double.MIN_VALUE) {//j es el peso que tendra por ejemplo en el primer caso 1, mas los decimales que tenga el minimo double
				objetos_aux.add(new Objeto3(j,objetos.get(i).getBeneficio()));
			}
		}
	}
	/**
	 * Metodo solucion, cuando el peso no es igual al peso maximo, usamos la funcion de seleccion (heuristica) generada con el CompareTo
	 * cogemos el primer objeto ya que es el mejor segun la funcion de seleccion
	 * Colocamos su id en el conjunto solucion y lo eliminamos de los posibles o candidatos
	 * Si el peso es igual al maximo peso entonces ya tenemos la solucion.
	 * @return sol : devuelve la solucion encontrada
	 */
	public ArrayList<Integer> Solucion1(){
		ArrayList<Integer> sol = new ArrayList<Integer>();//array solucion, no se inizializa, ya que esta a false por defecto,y con el maximo valor del doble
	    int peso = 0;
	    while(peso!=maximo_peso) {
	      //funcion de seleccion(heuristica), generada con el comapare to 
	    	if(peso+objetos_aux.get(0).getPeso()<=maximo_peso) { //saco el primero ya que es el mejor, segun la funcion de seleccion
	    		sol.add(objetos_aux.get(0).getId());  //colocamos el id en el conjunto solucion
	    		objetos_aux.remove(objetos_aux.get(0));	//lo eliminamos de la lista de candidatos
	    	}else {
	    		peso = maximo_peso; //si llega aqui es que tenemos la solucion , recuerda la heuristica está en el comapareTo
	    	}
	    }
		return sol;
	}
	/**
	 * Leemos los archivos  e inicializamos las variables, se ordenan y se devuelven en una lista.
	 * @return lista : lista de objetos ordenados
	 */
	/*
	 * En la lectura de archivos cargamos los archivos 
	 */
	public ArrayList<Objeto> lectura_Archivos1() throws FileNotFoundException{
		String directorioPeso = System.getProperty("user.dir") + File.separator +"src"+File.separator+"datos"+File.separator+"p02_w.txt";
		String directorioBeneficio = System.getProperty("user.dir") + File.separator +"src"+File.separator+"datos"+File.separator+"p02_p.txt";
		String directorioMax = System.getProperty("user.dir") + File.separator +"src"+File.separator+"datos"+File.separator+"p02_c.txt";
		ArrayList<Objeto> lista = new ArrayList<Objeto>();
		File docMax = new File(directorioMax);
		File docPeso = new File(directorioPeso);
		File docBeneficio = new File(directorioBeneficio);
		Scanner objPeso = new Scanner(docPeso);
		Scanner objBeneficio = new Scanner(docBeneficio);
		Scanner objMax = new Scanner(docMax);
		this.maximo_peso = Integer.parseInt(objMax.nextLine().trim());
		while (objPeso.hasNextLine()) {
			 int peso  = Integer.parseInt(objPeso.nextLine().trim());
			 int beneficio =Integer.parseInt(objBeneficio.nextLine().trim());
			 lista.add(new Objeto(peso,beneficio));
        }
		lista.sort(null);
		//ordenar las colas 
		objBeneficio.close();
		objPeso.close();
		objMax.close();
		return lista;
	}
}
