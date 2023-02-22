package Program_d;

public class Objeto3 implements Comparable<Objeto3>{
	int peso;
	int beneficio;
	static int numero = 0;//en este caso creamos una id, ya que queremos que sean el mismo objeto
	int id;
		public Objeto3(int peso,int beneficio) {
			this.peso = peso;
			this.beneficio = beneficio;
			this.id = numero; //vamos a generar una id para cada mochila
			numero++;
		}
		public int getId() {
			return id;
		}
		public int getPeso() {
			return peso;
		}

		public void setPeso(int peso) {
			this.peso = peso;
		}

		public int getBeneficio() {
			return beneficio;
		}

		public void setBeneficio(int beneficio) {
			this.beneficio = beneficio;
		}
         /*
          * Compara el objeto segun el peso, nos va a devolver el objeto mas prometedor, ya que tiene menor peso y mayor beneficio
          */
		@Override
		public int compareTo(Objeto3 o) {
		     if(this.getPeso()<o.getPeso() && this.getBeneficio()>o.getBeneficio()) return -1;
		     if(this.getPeso()>o.getPeso() && this.getBeneficio()>o.getBeneficio()) return 1;
		     return 0;
		}
		
}
