package Practica02;

public class Generar_archivo {
	public static void main (String [ ] args) {
		GeneraArchivos archivo  = new GeneraArchivos();
		archivo.genArchivo(100, "grafo100");
		archivo.genArchivo(100, "grafo500");
		archivo.genArchivo(100, "grafo1000");
	}	
}
