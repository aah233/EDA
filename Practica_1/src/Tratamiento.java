import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Tratamiento {
    public ArrayList<Player> tratamiento(String archivo) throws FileNotFoundException,IOException{
        String SEPARATOR=";";
        ArrayList<Player> lista = new ArrayList<Player>();
        Player aux;
       BufferedReader    br =new BufferedReader(new FileReader("C:/Users/perez/Desktop/Practica1/NbaStats.csv"));
           String line = br.readLine();
           line = br.readLine();//esta se posiciona en la ultima posicion
           while (line!=null) {
              String [] fields = line.split(SEPARATOR);
              if(fields[8].equals(""))fields[8]= "0";
              if(fields[7].equals("")) fields[7]="0";
              fields[7] =  fields[7].replace(",", ".");
            // Player jugador = new Player((fields[2].isEmpty())?" ": fields[2], (fields[6].isEmpty())?" ": fields[6], (fields[4].isEmpty())?" ": fields[4],(fields[7].isEmpty())?0.0:Double.parseDouble(fields[7]), (fields[8].isEmpty())?0:Integer.parseInt(fields[8]));
              //comprobamos que el jugador existe en la lista, si se encuentra en la ultima posicion
              //el aux hay que quitsarlo de aqui ya qe da una excepcion
              Player jugador = new Player(fields[2],fields[6],fields[4],Double.parseDouble(fields[7]),Integer.parseInt(fields[8]));
              if(lista.isEmpty()){
                lista.add(jugador);
                line = br.readLine();
                continue;
              }
              aux = lista.get(lista.size()-1);
              if(jugador.getPlayerName().equals(aux.getPlayerName())){
                if(!jugador.getPositions().get(jugador.getPositions().size()-1).equals(aux.getPositions().get(jugador.getPositions().size()-1))){
                  aux.getPositions().add(jugador.getPositions().get(0));
                  jugador.setPositions(aux.getPositions());
                }
                if(!jugador.getTeams().get(jugador.getTeams().size()-1).equals(aux.getTeams().get(jugador.getTeams().size()-1))){
                  aux.getTeams().add(jugador.getTeams().get(0));
                  jugador.setPositions(aux.getPositions());
                }
                aux.tratamiento_score(jugador);
                //if segundo
              }else{
                lista.add(jugador);
              }//if primero
              line = br.readLine();
           }//while
           br.close();
          
       return lista; 
       
    } 
   
     
}
//si existe 
//comprobar si el equipo y la posicion son los mismos no hace nada 
// si no anade el equipo a la array list , y anade la posicion a la lista 
// y con el score saca el de los dos lo suma y hace la media 