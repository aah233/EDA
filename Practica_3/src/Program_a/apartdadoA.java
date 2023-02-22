package Program_a;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Program_c.Objeto2;

public class apartdadoA {
	ArrayList<Objeto> objetos;
	int maximo_peso;
	int matriz_resultado[][];
	
	/**
	 * A partir de un archivo cogemos los datos de los pesos, beneficios y su peso máximo e inicializamos todas las variables.
	 * Debemos recordar que hay que sumar uno tanto a los objetos y peso para que la matriz tiene como primeras columnas 0
	 * @param maximo_peso : peso máximo cogido de los archivos
	 * @param matriz_resultado : matriz resultado obtenida a partir de los parametros anteriores
	 */
	/*
	 * Tenemos los pesos, beneficios y peso maximo
	 * recuerda que la posicion de matriz resultado [0] [o] son todo 0   
	 */
	public apartdadoA() throws FileNotFoundException {
		objetos = this.lectura_Archivos1(); //inicializa todas las variables
		this.matriz_resultado = new int[objetos.size()+1][maximo_peso+1]; //ya que son los objetos que hay mas uno, mas el maximo peso mas 1, ya que las primeras columnas son 0
	}
	/**
	 * Usamos el método iterativo para resolver el problema de la mochila recorriendo todos los elementos.
	 * Para ello recorremos los elementos columna y fila. Si el peso del objeto es menor que el peso máximo de esa columna, 
	 * entonces insertamos el beneficio de introducir ese objeto en la mochila, sino le ponemos la anterior.
	 * En el caso en que el peso sea mayor, le ponemos el anterior.
	 * @param objeto : elementos a recorrer 
	 * @param w : indice para recorrer las filas
	 * @param maximo_peso : peso máximo de los objetos
	 * @param i : indice para recorrer las columnas
	 */
	/*
	 * k van a ser los elementos y j el w , el peso 
	 */
	//No es necesario inicializar a 0, ya que java deja por defecto los enteros a 0
	//array[fila,columna]
	public void ProblemaMochila_Llenado1() {//metodo iterativo
		for(int i = 1;i<this.objetos.size()+1;i++) { //recorremos los elementos  columnas
			Objeto aux = objetos.get(i-1);
			for(int w=1; w<this.maximo_peso+1;w++) { // filas hasta 166 en este caso
				if(aux.getPeso()<=w) { //si el peso del objeto es menor que el peso que el peso maximo de esa columna
					if((aux.getBeneficio()+matriz_resultado[i-1][(w-aux.getPeso()<0)?0:w-aux.getPeso()])> matriz_resultado[i-1][w]) {  //si la resta es -1 devolvemos 0 y aqui comprobamos que el beneficio es menos para insertarlo
						this.matriz_resultado[i][w] = aux.getBeneficio()+matriz_resultado[i-1][(w-aux.getPeso()<0)?0:w-aux.getPeso()];  //insertamos el beneficio de introduccir el objeto
					}else {
						this.matriz_resultado[i][w] = this.matriz_resultado[i-1][w];  //si no le ponemos la anterior
					}//else interno
				}else {  //caso en el que aux.getpeso()>w
					this.matriz_resultado[i][w] = this.matriz_resultado[i-1][w];  //si no le ponemos la anterior
				}
				
			}//bucle que recorre los objetos
		}
	}
	/**
	 * Usamos el método ToString para imprimir el resultado obtenido en la matriz.
	 * Para ello recorremos todos los elementos columna y fila. 
	 * @param i: indice para recorrer las columnas
	 * @param j: indice para recorrer las filas
	 * @param maximo_peso: peso máximo de los objetos
	 * @param matriz_resultado: matriz resultado obtenida
	 */
	public void ToString() {
		for(int i = 0;i<=objetos.size();i++) {
			for(int j = 0;j<=maximo_peso;j++) {
				System.out.print(matriz_resultado[i][j]+" ");
			}
		  System.out.println("\n");
		}
		
	}
	/**
	 * Usamos el método solución para resolver el problema de la mochila.
	 * Mientras que el número de objetos y el peso no sean 0 y no se encuentren en la matriz solucion, añadimos el objeto 
	 * cogemos el peso del siguiente objeto y volvemos a comprobar las condiciones.
	 * Por el contrario, simplemente restamos un elemento.
	 * @param objetos : usamos los métodos de objeto para conocer el número de elementos posibles 
	 * @param i : número de objetos pendientes de recorrer
	 * @param w : peso del objeto
	 * @return sol: retorna un array solución.
	 */
	public ArrayList<Integer> Solucion1(){
		ArrayList<Integer> sol = new ArrayList<Integer>();//array solucion 
	     int i  = this.objetos.size();
	     int w = this.maximo_peso;
	     while(i>0 && w>0) {//mientras que i y w no sean 0, es decir los objetos o el peso 
	    	 if(matriz_resultado[i][w]!= matriz_resultado[i-1][w]) { //si el resultado de la anterior es distinto significa que hay que añadir ese objeto 
	    		 sol.add(i);
	    		 w = w - objetos.get(i-1).getPeso();//el peso del siguiente objeto a explorar 
	    		 i --;
	    	 }else {
	    		  i--;
	    	 }
	     }
		return sol;
	}
	/*
	 * En la lectura de archivos cargamos los archivos 
	 */
	/**
	 * Leemos los archivos y escaneamos peso, beneifcion y peso máximo. Una vez obtenidos los ordenamos y devolvemos la lista obtenida.
	 * @param peso : peso del objeto
	 * @param beneficio : beneficio que proporciona el objeto
	 * @param maximo_peso : peso máximo de los objetos
	 * @return lista : lista obtenida con los datos leidos
	 */
	public ArrayList<Objeto> lectura_Archivos1() throws FileNotFoundException{
		String directorioPeso = System.getProperty("user.dir") + File.separator +"Datasets"+File.separator+"datos"+File.separator+"p07_w.txt";
		String directorioBeneficio = System.getProperty("user.dir") + File.separator +"Datasets"+File.separator+"datos"+File.separator+"p07_p.txt";
		String directorioMax = System.getProperty("user.dir") + File.separator +"Datasets"+File.separator+"datos"+File.separator+"p07_c.txt";
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
