package DB.Util;

import java.net.*;

public class AceitadoraDeConexao extends Thread {
	private ServerSocket pedido;
	private Lista<Parceiro> usuarios;

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
