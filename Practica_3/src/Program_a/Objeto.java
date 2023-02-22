package Program_a;

public class Objeto implements Comparable<Objeto>{
	int peso;
	int beneficio;
		public Objeto(int peso,int beneficio) {
			this.peso = peso;
			this.beneficio = beneficio;
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
		public int compareTo(Objeto o) {
		     if(this.getPeso()<o.getPeso()) return -1;
		     if(this.getPeso()>o.getPeso()) return 1;
		     return 0;
		}
		
}
