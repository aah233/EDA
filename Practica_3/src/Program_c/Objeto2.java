package Program_c;

public class Objeto2 implements Comparable<Objeto2>{
	int peso;
	int beneficio;
	int id;//en este caso creamos una id, ya que queremos que sean el mismo objeto
		public Objeto2(int peso,int beneficio,int id) {
			this.peso = peso;
			this.beneficio = beneficio;
			this.id = id;
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
          * Compara el objeto segun el peso
          */
		@Override
		public int compareTo(Objeto2 o) {
		     if(this.getPeso()<o.getPeso()) return -1;
		     if(this.getPeso()>o.getPeso()) return 1;
		     return 0;
		}
		
}
