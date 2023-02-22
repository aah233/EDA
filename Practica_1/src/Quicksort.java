import java.util.ArrayList;


public class Quicksort {
    
    public  ArrayList<Player> quicksor(ArrayList<Player> lista) {
        if(lista.size()<=1) return lista;//caso base 
        Player pivote=lista.get(0); // tomamos el primer score como referencia
        ArrayList<Player> izquierda = new ArrayList<Player>();
        ArrayList<Player> derecha = new ArrayList<Player>();
        for(int i=1; i < lista.size();i++){
            if(lista.get(i).getScore()< pivote.getScore()){
                izquierda.add(lista.get(i));
            }else{
                derecha.add(lista.get(i));
            }
        }

     ArrayList<Player> izquierdaAux = quicksor(izquierda);
     ArrayList<Player> derechaAux = quicksor(derecha);
     ArrayList<Player> solucion = combinar(izquierdaAux, pivote, derechaAux);
     return solucion;
     }
     public ArrayList<Player> combinar (ArrayList<Player> izquierdaAux,Player pivote,ArrayList<Player> derechaAux){
        ArrayList<Player> aux = new ArrayList<Player>();
        aux.addAll(izquierdaAux);
        aux.add(pivote);
        aux.addAll(derechaAux);
        return aux;
     }

     public  ArrayList<Player> quicksortMejorado(ArrayList<Player> lista) {
        if(lista.size()<=1) return lista;//caso base 
        Player pivote=lista.get(0); // tomamos el primer score como referencia
        ArrayList<Player> izquierda = new ArrayList<Player>();
        ArrayList<Player> derecha = new ArrayList<Player>();
        for(int i=1; i < lista.size();i++){
            if(lista.get(i).getScore()< pivote.getScore()){
                izquierda.add(lista.get(i));
            }else{
                derecha.add(lista.get(i));
            } 
        }
     ArrayList<Player> izquierdaAux = quicksor(izquierda);
     ArrayList<Player> derechaAux = quicksor(derecha);

     if(derechaAux.size()>10){
         return combinar(null, null, derechaAux);
     }
     ArrayList<Player> solucion = combinar(izquierdaAux, pivote, derechaAux);
     return solucion;
     }
       
}
