package Default;

import java.io.*;

public class Labirinto {
	public static void main(String[] args) {

		BufferedReader in = null;
		BufferedWriter out = null;

		Arquivo arquivo = new Arquivo();

		do {
			try {
				try {
					System.out.print("Digite o caminho do arquivo: ");
					arquivo.setCaminhoArquivoEntrada(Teclado.getUmString());
				}catch(Exception ex) {
					throw new Exception("Erro ao ler o arquivo ou caminho entrada invalido! Tente novamente ...");
				}
				
				try {
					System.out.print("Digite o caminho da saida do arquivo: ");
					arquivo.setCaminhoArquivoSaida(Teclado.getUmString());
				}catch(Exception ex) {
					throw new Exception("Erro ao ler caminho entrada saida! Tente novamente ...");
				}
				
				in = arquivo.lerArquivo();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		} while (arquivo.isValidCriarArquivo());

		try {
			arquivo.carregarArquivo(in);

			if ((arquivo.getEntrada() && arquivo.getQtdEntrada() == 1) && (arquivo.getSaida())
					&& arquivo.getQtdSaida() == 1)
				System.out.println("Arquivo valido, existe entrada e saida!");
			else
				throw new Exception("Arquivo invalido! Deve conter uma entrada e uma saida");
			
			System.out.println("Quantidade de linhas: " + arquivo.getQtdLinhas());

			System.out.println("Quantidade de colunas na primeira linhas: " + arquivo.getQtdPrimeiraColuna());

			System.out.println("Quantidade de colunas nas demais linhas: " + arquivo.getQtdColunas());
			
			System.out.println();
			
			arquivo.carregarLabirinto();
			arquivo.progressivo();				
			arquivo.escreverArquivo();
			
			in.close();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}