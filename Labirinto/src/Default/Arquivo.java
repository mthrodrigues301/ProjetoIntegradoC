package Default;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Arquivo {

	private int qtdTotalLinhas = 0, qtdLinhas = 0, qtdPrimeiraColuna = 0, qtdColunas = 0;
	private String caminhoArquivoEntrada, caminhoArquivoSaida;
	private BufferedReader ArquivoGerado;
	private boolean entrada = false, saida = false;
	private Coordenada atual;
	private int qtdEntrada = 0, qtdSaida = 0;
	private String linhaAtual;
	private char[][] labirinto;
	private Pilha<Coordenada> caminho, adjacentes, inverso;
	private Pilha<Pilha<Coordenada>> possibilidades;

	public void setCaminhoArquivoEntrada(String caminhoArquivoEntrada) throws Exception {
		if (caminhoArquivoEntrada == null)
			throw new Exception("Erro!");

		this.caminhoArquivoEntrada = caminhoArquivoEntrada;
	}
	
	public void setCaminhoArquivoSaida(String caminhoArquivoSaida) throws Exception {
		if (caminhoArquivoSaida == null)
			throw new Exception("Erro!");
		
		this.caminhoArquivoSaida = caminhoArquivoSaida;
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

	public String getCaminhoArquivoEntrada() {
		return this.caminhoArquivoEntrada;
	}
	
	public String getCaminhoArquivoSaida() {
		return this.caminhoArquivoSaida;
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
		this.ArquivoGerado = new BufferedReader(new FileReader(this.getCaminhoArquivoEntrada()));

		if (this.isValidCriarArquivo())
			throw new Exception("Erro!");

		return this.ArquivoGerado;
	}
	
	public void escreverArquivo() throws Exception {
		try {
			int now = LocalDateTime.now().getSecond();
			BufferedWriter arquivoSaida =  new BufferedWriter(new FileWriter(this.getCaminhoArquivoSaida()+"ArquivoSaida_"+now+".txt"));

			for(int i = 0; i < this.qtdLinhas; i++) {
				for(int k = 0; k < this.qtdColunas; k++) {
					arquivoSaida.write(this.labirinto[i][k]);
				}
				arquivoSaida.write("\n");
			}
			
			arquivoSaida.close();
		}catch(Exception ex) {
			throw new Exception("Erro ao escrever o arquivo de saida!");
		}
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

	public void progressivo() throws Exception{
		int qtdLinhas = this.getQtdLinhas();
		int qtdColunas = this.getQtdColunas();
		int total = qtdLinhas * qtdColunas;

		try {
			this.possibilidades = new Pilha<Pilha<Coordenada>>(total);
			this.caminho = new Pilha<Coordenada>(total);

			while (this.labirinto[this.atual.getLinha()][this.atual.getColuna()] != 'S') {
				while (this.labirinto[this.atual.getLinha()][this.atual.getColuna()] != '*'
						&& (this.labirinto[this.atual.getLinha()][this.atual.getColuna()] == ' '
								|| this.labirinto[this.atual.getLinha()][this.atual.getColuna()] == 'E'
								|| this.labirinto[this.atual.getLinha()][this.atual.getColuna()] == 'S')) {					
					this.adjacentes = new Pilha<Coordenada>(3);
					
					// VALIDAR ACIMA
					if (this.atual.getLinha() > 0
							&& (this.labirinto[this.atual.getLinha() - 1][this.atual.getColuna()] == ' '
									|| this.labirinto[this.atual.getLinha() - 1][this.atual.getColuna()] == ' ')) {
						this.adjacentes.guarde(new Coordenada((this.atual.getLinha() - 1), this.atual.getColuna()));
					}
					// VALIDAR ABAIXO
					if (this.atual.getLinha() < this.getQtdTotalLinhas()
							&& (this.labirinto[this.atual.getLinha() + 1][this.atual.getColuna()] == ' '
									|| this.labirinto[this.atual.getLinha() + 1][this.atual.getColuna()] == 'S')) {
						this.adjacentes.guarde(new Coordenada((this.atual.getLinha() + 1), this.atual.getColuna()));
					}
					// VALIDAR ESQUERDA
					if (this.atual.getColuna() > 0
							&& (this.labirinto[this.atual.getLinha()][this.atual.getColuna() - 1] == ' '
									|| this.labirinto[this.atual.getLinha()][this.atual.getColuna() - 1] == 'S')) {
						this.adjacentes.guarde(new Coordenada(this.atual.getLinha(), (this.atual.getColuna() - 1)));
					}
					// VALIDAR DIRETA
					if (this.atual.getColuna() < this.getQtdColunas()
							&& (this.labirinto[this.atual.getLinha()][this.atual.getColuna() + 1] == ' '
									|| this.labirinto[this.atual.getLinha()][this.atual.getColuna() + 1] == 'S')) {
						this.adjacentes.guarde(new Coordenada(this.atual.getLinha(), (this.atual.getColuna() + 1)));
					}
					
					if(this.labirinto[this.atual.getLinha()][this.atual.getColuna()] != 'E') {
						this.labirinto[this.atual.getLinha()][this.atual.getColuna()] = '*';
					}
					if (!this.adjacentes.isVazia()) {
						this.atual.setCoordenada(this.adjacentes.getValor());
						
						if (this.labirinto[this.atual.getLinha()][this.atual.getColuna()] == 'S') {
							this.adjacentes.jogueForaValor();
							break;
						}
						
						if(this.caminho.isCheia() || this.caminho.isCheia())
							throw new Exception("Não existe caminho que leve da entrada para a saida!");
						
						this.caminho.guarde(new Coordenada(this.atual.getLinha(), this.atual.getColuna()));
						this.adjacentes.jogueForaValor();
						this.possibilidades.guarde(this.adjacentes);
					}
				}

				if (this.labirinto[this.atual.getLinha()][this.atual.getColuna()] == 'S')
					break;

				regressivo();
			}

			this.inverso = new Pilha<Coordenada>(total);

			while (!this.caminho.isVazia()) {
				this.inverso.guarde(this.caminho.getValor());
				this.caminho.jogueForaValor();
			}

			while (!this.inverso.isVazia()) {
				System.out.print(" " + this.inverso.getValor());
				this.inverso.jogueForaValor();
			}
		} catch (Exception ex) {
			throw new Exception("Não existe caminho que leve da entrada para a saida!");
		}
	}

	private void regressivo() throws Exception{
		try {
			this.adjacentes = new Pilha<Coordenada>(3);

			while (this.adjacentes.isVazia()) {
				if(this.caminho.isVazia() || this.caminho.isVazia())
					throw new Exception("Não existe caminho que leve da entrada para a saida!");
					
				this.labirinto[this.atual.getLinha()][this.atual.getColuna()] = ' ';
				this.caminho.jogueForaValor();
				this.atual.setCoordenada(this.caminho.getValor());
				this.adjacentes = this.possibilidades.getValor();
				this.possibilidades.jogueForaValor();
			}
			
			this.atual = this.adjacentes.getValor();
			this.caminho.guarde(new Coordenada(this.atual.getLinha(), this.atual.getColuna()));
			
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
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
				} else {
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