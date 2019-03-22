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
}