package TSP_BaB;

public class Prueba_TSP {

	public static void main(String[] args) {
		Network road = new Network();
		 road.load("graphSpainTSP");
		//road.mostrar_grafo();
	PathNode prueba = road.TSPBaB("Almeria");
	for(String res:prueba.getRes()) {
		System.out.println(res);
		}
	System.out.println(prueba.getTotalCost());
	}

}
