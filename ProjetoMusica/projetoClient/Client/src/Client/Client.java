package Client;

import java.io.*;
import java.net.*;
import javax.swing.*;

import Client.Util.*;
import Client.View.JanelaDeMusicas;
import Helper.Comunicado;

/**
 * A classe Client é a principal classe por parte do cliente ela é responsável por executar todas 
 * as funções necessárias para comunicar o cliente com o servidor.
 * 
 * @author Eduardo Oliveira e Matheus Andrey.
 */
public class Client {
	
	/**
	 * Unico e principal método da classe Client que conecta  cliente ao servidor e 
	 * gera uma interface para que o cliente possa utilizar o programa corretamente.
	 *
	 * @param args Argumentos para conectar o cliente ao servidor.
	 */
	public static void main(String[] args) {
		if (args.length > 2) {
			System.err.println("Uso esperado: java Chat [HOST [PORTA]]\n");
			return;
		}

		Socket conexao = null;
		try {
			String host = "localhost";
			int porta = 12345;

			if (args.length > 0)
				host = args[0];

			if (args.length == 2)
				porta = Integer.parseInt(args[1]);

			conexao = new Socket(host, porta);
		} catch (Exception erro) {
			System.err.println("Indique o servidor e a porta corretos!\n");
			return;
		}

		ObjectOutputStream transmissor = null;
		try {
			transmissor = new ObjectOutputStream(conexao.getOutputStream());
		} catch (Exception erro) {
			System.err.println("Indique o servidor e a porta corretos!\n");
			return;
		}

		ObjectInputStream receptor = null;
		try {
			receptor = new ObjectInputStream(conexao.getInputStream());
		} catch (Exception erro) {
			System.err.println("Indique o servidor e a porta corretos!\n");
			return;
		}

		Parceiro servidor = null;
		try {
			servidor = new Parceiro(conexao, receptor, transmissor);
		} catch (Exception erro) {
			System.err.println("Indique o servidor e a porta corretos!\n");
			return;
		}

		JanelaDeMusicas janelaDeMusica = null;
		try {
			janelaDeMusica = new JanelaDeMusicas(servidor);
		} catch (Exception erro) {
		} // try/catch anterior garante ausencia de erro aqui	
	}
}