package DB.Util;

import java.net.*;
/**
 * A classe AceitadoraDeConexao é uma classe que tenta fazer a conexão do cliente com o servidor.
 */
public class AceitadoraDeConexao extends Thread {
	private ServerSocket pedido;
	private Lista<Parceiro> usuarios;

	/**
	 * Método que faz uma ponte para a comunicação do cliente com o servidor.
	 *
	 * @param porta Porta de conexão ao servidor.
	 * @param usuarios Cliente.
	 * @throws Exception Caso ocorra um erro ao comunicar o cliente ao servidor 
	 * lança-se uma exceção
	 */
	public AceitadoraDeConexao(int porta, Lista<Parceiro> usuarios) throws Exception {
		if (usuarios == null)
			throw new Exception("Usuarios ausentes");

		this.usuarios = usuarios;

		try {
			this.pedido = new ServerSocket(porta);
		} catch (Exception erro) {
			throw new Exception("Porta invalida");
		}
	}

	/**
	 * Método que instância uma nova thread para cada cliente tornando-os independentes.
	 */
	public void run() {
		for (;;) {
			Socket conexao = null;
			try {
				conexao = this.pedido.accept();
			} catch (Exception erro) {
				continue;
			}
			
			SupervisoraDeConexao supervisoraDeConexao = null;
			try {
				supervisoraDeConexao = new SupervisoraDeConexao(conexao, usuarios);
			} catch (Exception erro) {
			} // sei que passei parametros corretos para o construtor
			supervisoraDeConexao.start();
		}
	}
}
