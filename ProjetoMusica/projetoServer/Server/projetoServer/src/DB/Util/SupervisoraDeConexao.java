package DB.Util;

import java.io.*;
import java.net.*;
import java.util.*;

import DB.DAOS.Musicas;
import DB.DBO.Musica;
import Helper.Comunicado;

public class SupervisoraDeConexao extends Thread {
//	private String nick;
	private Parceiro usuario;
	private Socket conexao;
	private Lista<Parceiro> usuarios;
	private Lista<String> estilos;
	private Lista<Comunicado> comunicados;
//	private HashMap<String, Parceiro> usuarios;

	public SupervisoraDeConexao(Socket conexao, Lista<Parceiro> usuarios) throws Exception {
		System.out.println("SUPERVISORA");
		if (conexao == null)
			throw new Exception("Conexao ausente");

		if (usuarios == null)
			throw new Exception("Usuarios ausentes");

		this.conexao = conexao;
		this.usuarios = usuarios;
		System.out.println("SupervisoraDeConexao " + conexao + usuarios);
	}

	public void run() {
		System.out.println(" RUN SupervisoraDeConexao " + conexao + usuarios);
		ObjectInputStream receptor = null;
		try {
			receptor = new ObjectInputStream(this.conexao.getInputStream());
		} catch (Exception err0) {
			return;
		}

		ObjectOutputStream transmissor;
		try {
			transmissor = new ObjectOutputStream(this.conexao.getOutputStream());
		} catch (Exception erro) {
			try {
				receptor.close();
			} catch (Exception falha) {
			} // so tentando fechar antes de acabar a thread

			return;
		}

		try {
			this.usuario = new Parceiro(this.conexao, receptor, transmissor);
		} catch (Exception erro) {
		} // sei que passei os parametros corretos

		try {
			comunicados = new Lista<Comunicado>();
			for (;;) {
				Comunicado comunicado = this.usuario.envie();

				if (comunicado == null || comunicado.getComando().equals("FIM")) {
					this.usuario.receba(new Comunicado("FIC"));
					this.usuario.envie();
				}

				if (comunicado.getComando().equals("CMB")) {
					comunicados = Musicas.getEstilo();
					this.usuario.receba(comunicados.getItem());
//					this.usuario.envie();
//					while (!comunicados.isVazia()) {
//						this.usuario.receba(comunicados.getItem());
//						comunicados.removeItem();
//						this.usuario.envie();
//					}
//					this.usuario.receba(new Comunicado("FIM"));
//					this.usuario.envie();
				} else if (comunicado.getComando().equals("CON")) {
					comunicados = Musicas.getMusica(comunicado.getComplemento1(), comunicado.getComplemento2());
					this.usuario.receba(comunicados.getItem());
				}
			}
		} catch (Exception erro) {
			try {
				transmissor.close();
				receptor.close();
			} catch (Exception falha) {
			} // so tentando fechar antes de acabar a thread

			return;
		}
	}
}