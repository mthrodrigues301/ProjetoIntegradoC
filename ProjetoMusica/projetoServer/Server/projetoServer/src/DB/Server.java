package DB;

import DB.Util.*;
/**
 * A classe Server é a principal classe da parte do servidor do nosso programa, 
 * nela é possivel abrir um servidor para a conexão dos clientes.
 * 
 * @author Eduardo Oliveira e Matheus Andrey
 *
 */
public class Server {
	
	/**
	 * Método principal e unico método da classe Server que inicia um servidor local estável, 
	 * permitindo a entrada de clientes.
	 *
	 * @param args Argumentos utilizados para a abertura do servidor.
	 * @throws Exception Em caso de erro na abertura do servidor lança-se uma exceção 
	 * condizente com o erro.
	 */
	public static void main(String[] args) throws Exception {
		if (args.length > 1) {
			System.err.println("Uso esperado: java Servidor [PORTA]\n");
			return;
		}

		int porta = 12345;

		if (args.length == 1)
			porta = Integer.parseInt(args[0]);

		Lista<Parceiro> usuarios = new Lista<Parceiro>();

		AceitadoraDeConexao aceitadoraDeConexao = null;
		try {
			aceitadoraDeConexao = new AceitadoraDeConexao(porta, usuarios);
			aceitadoraDeConexao.start();

		} catch (Exception erro) {
			System.err.println("Escolha uma porta liberada para uso!\n");
			return;
		}

		for (;;) {
			System.out.println("O servidor esta ativo! Para desativa-lo,");
			System.out.println("use o comando \"desativar\"\n");
			System.out.print("> ");

			String comando = null;
			try {
				comando = Teclado.getUmString();
			} catch (Exception erro) {
			}
				System.out.println("O servidor foi desativado!\n");
				System.exit(0);
			} else
				System.err.println("Comando invalido!\n");
		}
}}