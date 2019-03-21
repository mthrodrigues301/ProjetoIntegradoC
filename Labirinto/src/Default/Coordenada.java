package Default;

public class Coordenada {
	private int linha, coluna;

	public Coordenada(int i, int j) {
		this.linha = 0;
		this.coluna = 0;
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