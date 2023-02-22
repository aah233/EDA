package Program_c;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class apartdadoC {
	ArrayList<Objeto2> objetos;
	int maximo_peso;
	int matriz_resultado[][];
	ArrayList<Objeto2> objetos_aux;//objetos con los pesos doubles
	/**
	 * Lee los archivos para inicializar todas las variables , añade en la matriz resultado el tamaño de las filas y columnas 
	 * teniendo en cuenta que son 64 bits para un doble y uno más por los 0
	 * Entramos en el bucle para asignar los valores de los objetos con los pesos más pequeños y teniendo en cuenta que no se pueden repetir lo sobjetos
	 * Introducimos los pesos y sumamos el minimo peso más el peso y obtenemos que con el mismo beneficio ahora tenemos distintos pesos 
	 */
	/*
	 * Tenemos los pesos, beneficios y peso maximo
	 * recuerda que la posicion de matriz resultado [0] [o] son todo 0   
	 */
	public apartdadoC() throws FileNotFoundException {
		objetos = this.lectura_Archivos2(); //inicializa todas las variables
		objetos_aux = new ArrayList<Objeto2>();
		//por que son 64 bits para un double y mas uno de los 0 
		this.matriz_resultado = new int[objetos.size()][maximo_peso*64+1]; //multiplicamos por 2^6 = 64
		//un bucle para darle valor a los objetos, con los pesos mas pequeños, pero como los objetos no se pueden repetir
		//en el introduccimos los pesos con el el i mas la suma del minimo beneficio y damos lugar a que no solo un objeto sea el que se meta si no mitad de muchos, y tenemos en cuenta que el beneficio es el mismo 
		for(int i=0;i<=objetos.size();i++) {
			for(int j =i; j<i+1;j+=Double.MIN_VALUE) {//j es el peso que tendra por ejemplo en el primer caso 1, mas los decimales que tenga el minimo double
				objetos_aux.add(new Objeto2(j,objetos.get(i).getBeneficio(),i));
			}
		}
	}
	/**
	 * Método iterativo que se diferencia del anterior por la creación de tanto objetos como valores máximos tengamos
	 * Recorremos los elementos y sumamos el valor minimo de double. Cuando el peso del objeto es menoer que el peso máximo de esa coolumna
	 * insertamos el beneficio de introducir el objeto. Sino ponemos el anterior.
	 * Si el peso es mayor entonces directamente insertamos el anterior.
	 * @param i : indice para recorrer las columnas
	 * @param w : peso 
	 * @param aux : objeto auxiliar para realizar las comparaciones
	 */
	/*
	 * aqui en vez de que tenga en cuenta cada uno de los objetos , creamos tantos objetos como valores maximos haya   
	 */
	public void ProblemaMochila_Llenado2() {//metodo iterativo
		for(int i = 1;i<this.objetos.size()+1;i++) { //recorremos los elementos  columnas
			Objeto2 aux = objetos.get(i-1);
			for(int w=1; w<this.maximo_peso+1;w++) { // va sumando el valor minimo de un double
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
	 * Bucle para transformar la matriz resultado e imprimirla
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
	 * Buscamos la solucion al problema. Para ello, se crea un array solucion que mientras que  los obejtos y el peso sean mayores que 0
	 * mira si el resultado anterior es distinto del actual y si es así, añade el objeto a la solucion
	 * si son iguales, resta un objeto y realizar el bucle
	 * @return sol : solucion 
	 */
	public ArrayList<Integer> Solucion2(){
		ArrayList<Integer> sol = new ArrayList<Integer>();//array solucion 
	     int i  = this.objetos_aux.size();
	     int w = this.maximo_peso;
	     while(i>0 && w>0) {//mientras que i y w no sean 0, es decir los objetos o el peso 
	    	 if(matriz_resultado[i][w]!= matriz_resultado[i-1][w]) { //si el resultado de la anterior es distinto significa que hay que añadir ese objeto 
	    		 sol.add(objetos_aux.get(i-1).getId());
	    		 w = w - objetos.get(i-1).getPeso();//el peso del siguiente objeto a explorar 
	    		 i --;
	    	 }else {
	    		  i--;
	    	 }
	     }
		return sol;
	}
	/**
	 * Leemos los archivos para cargar los datos de peso maximo, peso y objeto en la lista que se devuelve ordenada.
	 * @return lista: devuelve la lista de los objetos que ha sacado de la lectura de los ficheros
	 */
	/*
	 * En la lectura de archivos cargamos los archivos 
	 */
	public ArrayList<Objeto2> lectura_Archivos2() throws FileNotFoundException{
			String directorioPeso = System.getProperty("user.dir") + File.separator +"src"+File.separator+"datos"+File.separator+"p02_w.txt";
			String directorioBeneficio = System.getProperty("user.dir") + File.separator +"src"+File.separator+"datos"+File.separator+"p02_p.txt";
			String directorioMax = System.getProperty("user.dir") + File.separator +"src"+File.separator+"datos"+File.separator+"p02_c.txt";
			ArrayList<Objeto2> lista = new ArrayList<Objeto2>();
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
				 lista.add(new Objeto2(peso,beneficio,0));//0 porque aqui el id nos da igual, es una forma de castearlo
	        }
			lista.sort(null);
			//ordenar las colas 
			objBeneficio.close();
			objPeso.close();
			objMax.close();
			return lista;
		}
	}
