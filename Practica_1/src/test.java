

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class test {
    @Test
    public void pruebaTratamiento() throws FileNotFoundException,IOException{
        Tratamiento tratamiento_datos = new Tratamiento();
        //insertamos los jugadores, ya tratados
       ArrayList<Player> lista_Jugadores= tratamiento_datos.tratamiento("C:/Users/perez/Desktop/Practica1/NbaStats.csv");
       Quicksort q = new Quicksort();
      long time = System.nanoTime();
      ArrayList<Player> lista =  q.quicksor(lista_Jugadores);
      long time2 = System.nanoTime();
      System.out.println("\nEl tiempo es "+(time2-time)+" nanosegundos.\n");
      int i=0;
      System.out.println("Solución: \n");
      while (i<10) {
      System.out.println( lista.get(i).getPlayerName());
      i++;
      }
      
      long time3 =  System.nanoTime();
      ArrayList<Player> lista2 =  q.quicksor(lista_Jugadores);
      long time4 = System.nanoTime();
      System.out.println("\nEl tiempo del mejorado es "+(time4-time3)+" nanosegundos.\n");
      i=0;
      System.out.println("Solución mejorada: \n");
      while (i<10) {
      System.out.println(lista2.get(i).getPlayerName());
      i++;
      }
       
        long diferenciaTime=(time2-time)-(time4-time3);
        System.out.println("La diferencia entre ambos tiempos es de "+diferenciaTime +" nanosegundos.");
        assertEquals(lista.get(lista.size()-1), lista2.get(lista2.size()-1));
        assertEquals(lista.get(lista.size()-2), lista2.get(lista2.size()-2));
        assertEquals(lista.get(lista.size()-3), lista2.get(lista2.size()-3));
        assertEquals(lista.get(lista.size()-4), lista2.get(lista2.size()-4));
        assertEquals(lista.get(lista.size()-5), lista2.get(lista2.size()-5));
        assertEquals(lista.get(lista.size()-6), lista2.get(lista2.size()-6));
        assertEquals(lista.get(lista.size()-7), lista2.get(lista2.size()-7));
        assertEquals(lista.get(lista.size()-8), lista2.get(lista2.size()-8));
        assertEquals(lista.get(lista.size()-9), lista2.get(lista2.size()-9));
        assertEquals(lista.get(lista.size()-10), lista2.get(lista2.size()-10));
        
    }
}