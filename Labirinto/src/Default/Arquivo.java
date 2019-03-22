package Default;

import java.io.BufferedReader;
import java.io.FileReader;

public class Arquivo {

	private int qtdTotalLinhas = 0, qtdLinhas = 0, qtdPrimeiraColuna = 0, qtdColunas = 0;
	private String caminhoArquivo;
	private BufferedReader ArquivoGerado;
	private boolean entrada = false, saida = false;
	private Coordenada atual;
	private int qtdEntrada = 0, qtdSaida = 0;
	private String linhaAtual;
	private char[][] labirinto;
	private Pilha<Coordenada> caminho, adjacentes;
	private Pilha<Pilha<Coordenada>> possibilidades;

	public void setCaminho(String caminho) throws Exception {
		if (caminho == null)
			throw new Exception("Erro!");

		this.caminhoArquivo = caminho;
	}

	public void setQtdTotalLinhas(String linhas) throws Exception {
		try {
			this.qtdTotalLinhas = Integer.parseInt(linhas);
		} catch (NumberFormatException erro) {
			throw new Exception("A quantidade de linhas do labirinto deve ser um valor inteiro!");
		}
	}

	public void setQtdLinhas(int qtdLinhas) {
		this.qtdLinhas += qtdLinhas;
	}

	public void setQtdEntrada(int qtdEntrada) {
		this.qtdEntrada += qtdEntrada;
	}

	public void setQtdSaida(int qtdSaida) {
		this.qtdSaida += qtdSaida;
	}

	public void setQtdPrimeiraColuna(int qtdPrimeiraColuna) {
		this.qtdPrimeiraColuna = qtdPrimeiraColuna;
	}

	public String getCaminho() {
		return this.caminhoArquivo;
	}

	public int getQtdTotalLinhas() {
		return this.qtdTotalLinhas;
	}

	public int getQtdLinhas() {
		return this.qtdLinhas;
	}

	public int getQtdEntrada() {
		return this.qtdEntrada;
	}

	public int getQtdSaida() {
		return this.qtdSaida;
	}

	public int getQtdColunas() {
		return this.qtdColunas;
	}

	public int getQtdPrimeiraColuna() {
		return this.qtdPrimeiraColuna;
	}

	public boolean getEntrada() {
		return this.entrada;
	}

	public boolean getSaida() {
		return this.saida;
	}

	public boolean isValidCriarArquivo() {
		if (this.ArquivoGerado != null)
			return false;

		return true;
	}

	public BufferedReader lerArquivo() throws Exception {
		this.ArquivoGerado = new BufferedReader(new FileReader(this.caminhoArquivo));

		if (this.isValidCriarArquivo())
			throw new Exception("Erro!");

		return this.ArquivoGerado;
	}

	public void carregarArquivo(BufferedReader arquivo) throws Exception {
		try {
			this.setQtdTotalLinhas(arquivo.readLine());

			this.linhaAtual = arquivo.readLine();
			if (isValidLinhas() == true) {
				this.setQtdPrimeiraColuna(this.linhaAtual.length());
				this.setQtdLinhas(1);
				this.verificaEntradaSaida(this.getQtdLinhas());
				this.verificaBuracos(this.getQtdLinhas());
			}

			while ((this.linhaAtual = arquivo.readLine()) != null) {
				if (this.isValidColunas(this.linhaAtual.length()) && this.isValidLinhas()) {
					this.qtdColunas = this.linhaAtual.length();
				} else
					throw new Exception("Arquivo invalido! " + this.getQtdLinhas() + " linha do labirinto");

				this.setQtdLinhas(1);
				this.verificaEntradaSaida(this.getQtdLinhas());
				this.verificaBuracos(this.getQtdLinhas());
			}

			arquivo.close();
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	public void carregarLabirinto() throws Exception {
		BufferedReader in = null;

		try {
			in = this.lerArquivo();

			this.setQtdTotalLinhas(in.readLine());

			labirinto = new char[this.getQtdTotalLinhas()][this.getQtdColunas()];

			for (int i = 0; i < this.getQtdTotalLinhas(); i++) {
				this.linhaAtual = in.readLine();
				for (int j = 0; j < this.getQtdColunas(); j++) {
					if (this.linhaAtual.charAt(j) == 'E') {
						this.atual = new Coordenada(i, j);
					}
					labirinto[i][j] = this.linhaAtual.charAt(j);
				}
			}

			in.close();
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	public void progressivo() {
		int qtdLinhas = this.getQtdLinhas();
		int qtdColunas = this.getQtdColunas();
		int total = qtdLinhas * qtdColunas;
		try {
			this.possibilidades = new Pilha<Pilha<Coordenada>>(total);
			this.caminho = new Pilha<Coordenada>(total);
			this.adjacentes = new Pilha<Coordenada>(3);

			if ((this.labirinto[this.atual.getLinha() - 1][this.atual.getColuna()] == ' '
					|| this.labirinto[this.atual.getLinha() - 1][this.atual.getColuna()] == 'S')
					&& this.atual.getLinha() > 0) {
				this.adjacentes.guarde(new Coordenada((this.atual.getLinha() - 1), this.atual.getColuna()));
			} else if ((this.labirinto[this.atual.getLinha() + 1][this.atual.getColuna()] == ' '
					|| this.labirinto[this.atual.getLinha() + 1][this.atual.getColuna()] == 'S')
					&& this.atual.getLinha() < this.getQtdTotalLinhas()) {
				this.adjacentes.guarde(new Coordenada((this.atual.getLinha() + 1), this.atual.getColuna()));
			} else if ((this.labirinto[this.atual.getLinha()][this.atual.getColuna() + 1] == ' '
					|| this.labirinto[this.atual.getLinha() + 1][this.atual.getColuna()] == 'S')
					&& this.atual.getColuna() > 0) {
				this.adjacentes.guarde(new Coordenada(this.atual.getLinha(), (this.atual.getColuna() - 1)));
			} else if ((this.labirinto[this.atual.getLinha()][this.atual.getColuna() - 1] == ' '
					|| this.labirinto[this.atual.getLinha() + 1][this.atual.getColuna()] == 'S')
					&& this.atual.getColuna() < this.getQtdColunas()) {
				this.adjacentes.guarde(new Coordenada(this.atual.getLinha(), (this.atual.getColuna() + 1)));
			}

		} catch (Exception ex) {

		}
	}

	private boolean isValidColunas(int tamanhoColuna) {
		if (tamanhoColuna == this.qtdPrimeiraColuna)
			return true;

		return false;
	}

	private boolean isValidLinhas() {
		for (int i = 0; i < this.linhaAtual.length(); i++) {
			if (this.linhaAtual.charAt(i) == '#' || this.linhaAtual.charAt(i) == 'E' || this.linhaAtual.charAt(i) == 'S'
					|| this.linhaAtual.charAt(i) == ' ')
				continue;
			else
				return false;
		}

		return true;
	}

	private void verificaEntradaSaida(int linha) throws Exception {
		if (linha > this.qtdTotalLinhas)
			throw new Exception("Arquivo invalido! Linhas do labirinto nao coincidem com o total.");

		if (linha == 1 || linha == this.qtdTotalLinhas) {
			for (int i = 0; i < this.linhaAtual.length(); i++) {
				if (this.linhaAtual.charAt(i) == 'E') {
					this.entrada = true;
					this.setQtdEntrada(1);
				}

				if (this.linhaAtual.charAt(i) == 'S') {
					this.saida = true;
					this.setQtdSaida(1);
				}
			}
		} else {
			for (int i = 0; i < this.linhaAtual.length(); i++) {
				if (i == 0) {
					if (this.linhaAtual.charAt(i) == 'E') {
						this.entrada = true;
						this.setQtdEntrada(1);
					}

					if (this.linhaAtual.charAt(i) == 'S') {
						this.saida = true;
						this.setQtdSaida(1);
					}
				} else if (i == this.linhaAtual.length()) {
					if (this.linhaAtual.charAt(i) == 'E') {
						this.entrada = true;
						this.setQtdEntrada(1);
					}

					if (this.linhaAtual.charAt(i) == 'S') {
						this.saida = true;
						this.setQtdSaida(1);
					}
				}
			}
		}
	}

	private void verificaBuracos(int linha) throws Exception {
		if (linha > this.qtdTotalLinhas)
			throw new Exception("Arquivo invalido! Linhas do labirinto nao coincidem com o total.");

		if (linha == 1 || linha == this.qtdTotalLinhas) {
			for (int i = 0; i < this.linhaAtual.length(); i++) {
				if (this.linhaAtual.charAt(i) == ' ') {
					throw new Exception("Labirinto invalido! Existe buraco nas extremidades!");
				}
			}
		} else {
			for (int i = 0; i < this.linhaAtual.length(); i++) {
				if (i == 0) {
					if (this.linhaAtual.charAt(i) == ' ') {
						throw new Exception("Labirinto invalido! Existe buraco nas extremidades!");
					}
				} else if (i == this.linhaAtual.length()) {
					if (this.linhaAtual.charAt(i) == ' ') {
						throw new Exception("Labirinto invalido! Existe buraco nas extremidades!");
					}
				}
			}
		}
	}
}