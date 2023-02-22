package Backtracking;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;


public class Practica_Final {
	    //declaracion de variables 
	    HashMap<String, Integer> VE = null; //aqui vamos a almacenar la ciudad y  un entero aleatorio
	    HashMap<Integer,String> VF;
	    int matriz_adyacencia[][];
	    int peso = 0;
	    int nodo_explorado = 0;
	    double tiempo_imprimir = 6.05E10;
	    double tiempo_llevado;
	    double tiempo  = System.nanoTime();
	    ArrayList<Stack<Integer>> soluciones_backtracking;
	    Stack<Integer> solucion_parcial; //esto sera la solucion y una posicion ultima para el peso acumulado, stack saca el ultimo que ha entrado
	    public Practica_Final(String filename) {
			VE =new HashMap<String,Integer>(); //la inicializamos
			VF = new HashMap<Integer,String>();
			matriz_adyacencia = new int[1053][1053];
			solucion_parcial = new Stack<Integer>();
			soluciones_backtracking = new ArrayList<Stack<Integer>>();
			this.load(filename);
		}
	   public int peso(Stack<Integer> solucion) {
		   int peso  = 0;
        	for(int i =0; i<solucion.size()-1;i++) {
        		peso +=matriz_adyacencia[solucion.get(i)][solucion.get(i+1)];
        	}
        	return peso;
	    }
	    public void Mostrar_Matriz() {
	    		for (int i = 0; i < 1053; i++) {
					for (int j = 0; j < matriz_adyacencia.length; j++) {
						System.out.print(matriz_adyacencia[i][j]+" ");
					}
					System.out.println();
				}
	    }
		/**
		 * Cargamos el archivo, e inicializamos las variables
		 * @param int nodo: recibe el integer del nodo
		 * de la matriz de adyacencia, obtenemos los adyacentes a ese nodo para saber las opciones 
		 * @return : un array con los adyacentes
		 */
		
		public Queue<Integer> adyacentes(int nodo) {
			Queue<Integer> Iniciar = new LinkedList<Integer>();
			for(int i = 0; i <=1052;i++ ) 
				if(matriz_adyacencia[nodo][i]>0) Iniciar.add(i);
	      return Iniciar;
		}
		//método que comprueba si dos nodos son adyacentes
		public boolean sonAdyacentes(int nodo,int nodocomprobar) {
				if(matriz_adyacencia[nodo][nodocomprobar] > 0) return true;
	      return false;
		}
		public void Backtracking(int nodo) {//método recursivo
			solucion_parcial.push(nodo);
			if(solucion_parcial.size() == this.VE.size()) {
				if(this.sonAdyacentes(solucion_parcial.firstElement(), nodo)) {
					//si son adyacentes lo añade si no no lo añade
					soluciones_backtracking.add(this.solucion_parcial);
					for(int i:this.solucion_parcial)System.out.print(VF.get(i)+" -- ");
					System.out.print(this.peso(solucion_parcial));
					System.out.println(" ");
				}
				this.solucion_parcial.pop();
				return;//cuando haga el break , salgo de la solucion 
			}
			Queue<Integer> IniciarOpciones = this.adyacentes(nodo); //para saber cuando ha llegado al final, saca el primero que entro //32 bits 
			while(!IniciarOpciones.isEmpty()) {
				int nueva_opcion = IniciarOpciones.poll(); //sacariamos el primero de la cola
				nodo_explorado++;
				//if((System.nanoTime()-tiempo )> tiempo_imprimir) {
					tiempo_imprimir +=tiempo_imprimir; 
					System.out.println("Tiempo llevado para "+ nodo_explorado+ " nodos es "+(System.nanoTime()-tiempo));
					System.out.println(((Runtime.getRuntime().freeMemory()/1024)/1024)+" MB disponibles");
				//}
				
				if(!this.solucion_parcial.contains(nueva_opcion)) {
						Backtracking(nueva_opcion);
					}
				}//si ya lo contiene no hago nada
			this.solucion_parcial.pop();//vuelta atrás cuando no sea hoja
		}
		//end backtracking
		
		/**
		 * Cargamos el archivo, e inicializamos las variables
		 * @param : si es vertex, inserta en el HasMap el pais con un numero de posicion de 0 hasta n
		 * @param : si es edges, rellena la matriz de adyacencia con el valor de ir de esa ciudad a la otra
		 */
		 public void load(String filename){
			 String directorioEntrada = System.getProperty("user.dir") + File.separator +"src"+File.separator+"dataset"+File.separator;
		        String line = "";
		        Scanner scan = null;
		        try{
		            scan = new Scanner(new File(directorioEntrada+filename));
		        }catch(Exception e){
		            System.out.println("Error al cargar el archivo --> " + e.getMessage());
		            System.exit(-1);
		        }
		        int posicion = 0;
		        String categoria ="";
		        while(scan.hasNextLine()){
		            line = scan.nextLine();
		            if (line.isEmpty()) continue;
		            if (line.startsWith("%")) continue;

		            if (line.toLowerCase().contains("@type")){
		                continue;
		            }
		            
		            if (line.toLowerCase().contains("@vertex")){
		            	categoria = "@vertex";
		                continue;
		            }
		            if (line.toLowerCase().contains("@edges")){
		                categoria = "@edges";
		                continue;
		            }
		            //aqui leemos según la categoria
		            if(categoria.equals("@vertex")) {
		            	 this.VE.put(line, posicion);  //insertamos el nodo
		            	 this.VF.put(posicion, line);
			             posicion++; //y sumamos la posición
		            }else if(categoria.equals("@edges")) {
		            	String [] nodos = line.split(" ");
		            	if(nodos[0] == null) continue;
		                matriz_adyacencia[this.VE.get(nodos[0])][this.VE.get(nodos[1])] = Integer.parseInt(nodos[2]); //meto en la matriz de adyacencia su peso correspondiente
		                matriz_adyacencia[this.VE.get(nodos[1])][this.VE.get(nodos[0])] = Integer.parseInt(nodos[2]); //tiene que ser en ambos sentidos
		            }
		        }
		       
		        }
		    }//load
