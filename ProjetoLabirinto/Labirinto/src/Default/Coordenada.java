package Default;

public class Coordenada {
	private int linha, coluna;

	public Coordenada(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	public void setCoordenada(Coordenada coordenada) {
		this.linha = coordenada.getLinha();
		this.coluna = coordenada.getColuna();
	}
	public void setLinha(int linha) {
		this.linha = linha;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public int getLinha() {
		return this.linha;
	}

	public int getColuna() {
		return this.coluna;
	}

	public String toString() {
		return "(" + this.linha + "," + this.coluna + ")";
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (this.getClass() != obj.getClass())
			return false;

		return true;
	}
	
	public int hashCode() {
		int ret = 13;

		ret *= 11 + new Integer(this.linha).hashCode();
		ret *= 11 + new Integer(this.coluna).hashCode();

		return ret < 0 ? -ret : ret;
	}
}