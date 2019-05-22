package DB.Util;

import java.io.*;
import DB.DAOS.*;
import DB.DBO.*;
import java.net.*;
import java.util.*;
import DB.DBO.Musica;
import Helper.Comunicado;

public class SupervisoraDeConexao extends Thread {
//	private String nick;
	private Parceiro usuario;
	private Socket conexao;
	private Lista<Parceiro> usuarios;
	private Lista<Musica> musicas;
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
			}

			return;
		}

		try {
			this.usuario = new Parceiro(this.conexao, receptor, transmissor);
		} catch (Exception erro) {
		}

		try {
			for (;;) {
				System.out.println("SupervisoraDeConexao " + conexao + usuarios);
				Comunicado comunicado = this.usuario.envie();

				if (comunicado.getComando().equals("CON")) {
					try {
						if(comunicado.getComplemento1().equals("TODAS"))
							musicas = Musicas.getAllMusicas();
						else if(!comunicado.getComplemento2().equals("VAZIO"))
							musicas = Musicas.getMusicaPesq(comunicado.getComplemento2());
						else
							musicas = Musicas.getMusicaByEstilo(comunicado.getComplemento1());
						
						for (int i = 1; i <= musicas.getTamanho(); i++) {
							usuario.receba(new Comunicado("MUS", musicas.getItem(i).getTitulo(),
									musicas.getItem(i).getCantor(), musicas.getItem(i).getEstilo(),
									Double.toString(musicas.getItem(i).getPreco()),
									Integer.toString(musicas.getItem(i).getDuracao())));
						}
					} catch (Exception e) {
						usuario.receba(new Comunicado("ERR"));
					}
					usuario.receba(new Comunicado("FIC"));
				} else if (comunicado.getComando().equals("CMB")) {
					try {
						musicas = Musicas.getEstilo();
						for (int i = 1; i <= musicas.getTamanho(); i++) {
							usuario.receba(new Comunicado("MUS", musicas.getItem(i).getTitulo(),
									musicas.getItem(i).getCantor(), musicas.getItem(i).getEstilo(),
									Double.toString(musicas.getItem(i).getPreco()),
									Integer.toString(musicas.getItem(i).getDuracao())));
						}
					} catch (Exception e) {
						usuario.receba(new Comunicado("ERR"));
					}
					usuario.receba(new Comunicado("FIC"));
				}

				if (comunicado == null || !comunicado.getComando().equals("FIC"))
					return;
				
				this.usuario.receba(new Comunicado("ERR"));
			}
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
	}
}