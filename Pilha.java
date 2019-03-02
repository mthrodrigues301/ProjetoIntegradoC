public class Pilha {
	private Object[] pilha; // vetor de objetos
	private int topo; // indicador de topo

	// construtor
	public Pilha(int qtde) {
		//indica que está vazia, pois o topo é a primeira posição
		this.topo = 0;
		//criando uma pilha com a quantidade de posições informada
		this.pilha = new Object[qtde];
	}
	
	// push
	public boolean empilhar(Object valor) {
		if (!this.pilhaCheia()) {
			this.pilha[this.topo] = valor;
			this.topo++;
			return true;
		}
		return false;
	}
	
	// pop
	public Object desempilhar() {
		if (this.pilhaVazia()) {
			return null;
		}
		this.topo--;
		return this.pilha[this.topo];
	}

	// isEmpty
	public boolean pilhaVazia() {
		if (this.topo == 0) {
			return true;
		}
		return false;
	}
	
	// isFull
	public boolean pilhaCheia() {
		if (this.topo > this.pilha.length - 1) {
			return true;
		}
		return false;
	}

	// peek
	public Object exibeTopo() {
		if (this.pilhaVazia()) {
			return null;
		}
		return this.pilha[this.topo - 1];
	}
	
	// size
	public int quantosElementos() {
		return this.topo;
	}
}