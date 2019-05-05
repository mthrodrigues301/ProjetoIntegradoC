package DB.Util;

import java.io.*;
import java.net.*;
import java.util.*;

public class SupervisoraDeConexao extends Thread {
//	private String nick;
	private Parceiro usuario;
	private Socket conexao;
	private Lista<Parceiro> usuarios;
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
			for (;;) {
				System.out.println("SupervisoraDeConexao " + conexao + usuarios);
				Comunicado comunicado = this.usuario.envie();

				if (comunicado == null || !comunicado.getComando().equals("FIC"))
					return;
				
				System.out.println("FOR SupervisoraDeConexao " + conexao + usuarios);
//				this.nick = comunicado.getComplemento1();
//
//				if (this.nick == null)
//					return;

//				synchronized (this.usuarios) {
//					if (this.usuarios.get(this.nick) == null) {
//						this.usuarios.put(this.nick, this.usuario);
//						break;
//					}
//				}

				this.usuario.receba(new Comunicado("ERR"));
			}

//			this.usuario.receba(new Comunicado("BOM"));
		} catch (Exception erro) {
//			if (this.usuarios.get(this.nick) != null)
//				this.usuarios.remove(this.nick);

			try {
				transmissor.close();
				receptor.close();
			} catch (Exception falha) {
			} // so tentando fechar antes de acabar a thread

			return;
		}

//		try {
////			synchronized (this.usuarios) {
////				Set<String> nicks = this.usuarios.keySet();
////
////				for (String nick : nicks)
////					if (!nick.equals(this.nick)) {
////						this.usuario.receba(new Comunicado("ENT", nick));
////
////						this.usuarios.get(nick).receba(new Comunicado("ENT", this.nick));
////					}
////			}
//
//			for (;;) {
//				Comunicado comunicado = this.usuario.envie();
//
//				if (comunicado.getComando().equals("ENC")) {
//					String remetente = comunicado.getComplemento1();
//					String destinatario = comunicado.getComplemento2();
//					String texto = comunicado.getComplemento3();
//
//					if (destinatario.equals("TODOS"))
//						synchronized (this.usuarios) {
//							Set<String> nicks = this.usuarios.keySet();
//
//							for (String nick : nicks)
//								if (!nick.equals(this.nick)) {
//									this.usuarios.get(nick).receba(new Comunicado("MSG", remetente, texto));
//								}
//						}
//					else {
//						this.usuarios.get(destinatario).receba(new Comunicado("MSG", remetente, texto));
//					}
//				} else if (comunicado.getComando().equals("SAI"))
//					break;
//			}
//
//			synchronized (this.usuarios) {
//				Set<String> nicks = this.usuarios.keySet();
//
//				for (String nick : nicks)
//					if (!nick.equals(this.nick)) {
//						this.usuarios.get(nick).receba(new Comunicado("FOI", this.nick));
//					}
//
//				this.usuarios.remove(this.nick);
//			}
//
//			this.usuario.adeus();
//		} catch (Exception erro) {
//		} // se algum usuario travou, a supervisora dele cuida
	}
}