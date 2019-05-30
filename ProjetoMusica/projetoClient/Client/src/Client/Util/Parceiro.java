package DB.Util;

import java.io.*;
import java.net.*;

import Helper.Comunicado;

/**
 * A classe Parceiro é a classe para a criação de um parceiro para a 
 * conexão dele com o servidor.
 */
public class Parceiro {
	private Socket conexao;
	private ObjectInputStream receptor;
	private ObjectOutputStream transmissor;

	/**
	 * Instancia um novo parceiro para sua conexão com o servidor.
	 *
	 * @param conexao Socket de conexão com o servidor.
	 * @param receptor Receptor da informação.
	 * @param transmissor Transmissor da informação
	 * @throws Exception Caso occora um erro ao instanciar o novo parceiro lança-se uma excessão.
	 */
	public Parceiro(Socket conexao, ObjectInputStream receptor, ObjectOutputStream transmissor) throws Exception //se o parrametro nulos
	{
		if (conexao == null)
			throw new Exception("Conexao ausente");

		if (receptor == null)
			throw new Exception("Receptor ausente");

		if (transmissor == null)
			throw new Exception("Transmissor ausente");

		this.conexao = conexao;
		this.receptor = receptor;
		this.transmissor = transmissor;
	}

	/**
	 * Método responsavel por fazer com que o Cliente transmita informações ao Servidor.
	 *
	 * @param x Comunicado a ser transmitido.
	 * @throws Exception Caso ocorra um erro ao transmitir o Comunicado lança-se uma exceção.
	 */
	public void receba(Comunicado x) throws Exception {
		try {
			this.transmissor.writeObject(x);
			this.transmissor.flush();
		} catch (IOException erro) {
			throw new Exception("Erro de transmissao");
		}
	}

	/**
	 * Método que espera o retorno do Servidor com as informações ao Cliente.
	 * 
	 * @return Comunicado a ser recebido pelo Cliente.
	 * @throws Exception Em caso de erro na recepção do comunicado lança-se exceção.
	 */
	public Comunicado envie() throws Exception {
		try {
			return (Comunicado) this.receptor.readObject();
		} catch (Exception erro) {
			throw new Exception("Erro de recepcao");
		}
	}
}