package Practica02;

import java.io.File;
import java.util.Scanner;

public class RoadNetwork extends Network<String> {

    private enum Secciones {
            Type, Vertex, Edge;
            void load(RoadNetwork net, String linea){
                switch (this){
                case Type: 
                    net.setDirected(linea.toLowerCase().equals("Directed") ? true : false);
                    break;
                case Vertex:
                    net.addVertex(linea);
                    break;
                case Edge:
                    String[] words = linea.split("[ ]+");
                    net.addEdge(words[0], words[1], Double.parseDouble(words[2]));
                    break;
                }

            }
    }

    public void load(String filename){
        String line = "";
        Scanner scan = null;
        Secciones seccion = null; 

        try{
            scan = new Scanner(new File(filename));
        }catch(Exception e){
            System.out.println("Error al cargar el archivo --> " + e.getMessage());
            System.exit(-1);
        }

        while(scan.hasNextLine()){
            line = scan.nextLine().trim();
            if (line.isEmpty()) continue;
            if (line.startsWith("%")) continue;

            if (line.toLowerCase().contains("@type")){
                seccion = Secciones.Type;
                continue;
            }
            if (line.toLowerCase().contains("@vertex")){
                seccion = Secciones.Vertex;
                continue;
            }
            if (line.toLowerCase().contains("@edges")){
                seccion = Secciones.Edge;
                continue;
            }
            seccion.load(this, line);
        }
    }
}