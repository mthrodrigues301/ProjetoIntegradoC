package Default;

public class Pilha<X> {
	private Object[] vetor;
	private int ultimo;

	public Pilha(int tamanho) throws Exception {
		if (tamanho < 1)
			throw new Exception("Tamanho invalido");

		this.ultimo = -1;
		this.vetor = new Object[tamanho];
	}

	// push
	public void guarde(X valor) throws Exception {
		if (valor == null)
			throw new Exception("Valor ausente");

		if (this.ultimo == this.vetor.length - 1)
			throw new Exception("Nao cabe mais");

		this.ultimo++;
		this.vetor[this.ultimo] = valor;
	}

	// pop
	public void jogueForaValor() throws Exception {
		if (this.ultimo == -1)
			throw new Exception("Nada guardado");

		this.vetor[this.ultimo] = null;
		this.ultimo--;
	}

	// peek
	public X getValor() throws Exception {
		if (this.ultimo == -1)
			throw new Exception("Nada guardado");

		return (X) this.vetor[this.ultimo];
	}

	// isEmpty
	public boolean isVazia() {
		return this.ultimo == -1;
	}

	// isFull
	public boolean isCheia() {
		return this.ultimo == this.vetor.length - 1;
	}

	// size
	public int getQuantos() {
		return this.ultimo + 1;
	}

	public String toString() {
		return (this.ultimo + 1) + " elemento(s)"
				+ (this.ultimo != -1 ? ", sendo o ultimo " + this.vetor[this.ultimo] : "");
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (this.getClass() != obj.getClass())
			return false;

		if (this.ultimo != ((Pilha<X>) obj).ultimo)
			return false;

		for (int i = 0; i <= this.ultimo; i++)
			if (!this.vetor[i].equals(((Pilha<X>) obj).vetor[i]))
				return false;

		return true;
	}

	public int hashCode() {
		int ret = 13;

		for (int i = 0; i <= ultimo; i++) {
			ret *= 11 + this.vetor[i].hashCode();
		}

		ret *= 11 + new Integer(this.ultimo).hashCode();

		return ret < 0 ? -ret : ret;
	}
}