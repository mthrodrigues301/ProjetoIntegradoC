package DB.DAOS;

import java.sql.SQLException;

import DB.Util.*;
import Helper.Comunicado;
import DB.Config.Connection;
import DB.DBO.*;

public class Musicas {

	public static Musica getMusica() throws Exception {
		Musica musica = null;
		try {
			String sql;

			sql = "SELECT * FROM MUSICA";

			Connection.COMANDO.prepareStatement(sql);

			MeuResultSet resultado = (MeuResultSet) Connection.COMANDO.executeQuery();

			if (!resultado.first())
				throw new Exception("Nao cadastrado");

			musica = new Musica(resultado.getInt("Id"), resultado.getString("Titulo"), resultado.getString("Cantor"),
					resultado.getString("Estilo"), resultado.getInt("Duracao"), resultado.getFloat("Preco"));

		} catch (SQLException erro) {
			throw new Exception("Erro ao recuperar musica");
		}

		return musica;
	}

	public static Musica getMusica(int codigo) throws Exception {
		Musica musica = null;

		try {
			String sql;

			sql = "SELECT * FROM MUSICA WHERE Id = ?";

			Connection.COMANDO.prepareStatement(sql);

			Connection.COMANDO.setInt(1, codigo);

			MeuResultSet resultado = (MeuResultSet) Connection.COMANDO.executeQuery();

			if (!resultado.first())
				throw new Exception("Nao cadastrado");

			musica = new Musica(resultado.getInt("Id"), resultado.getString("Titulo"), resultado.getString("Cantor"),
					resultado.getString("Estilo"), resultado.getInt("Duracao"), resultado.getDouble("Preco"));
		} catch (SQLException erro) {
			throw new Exception("Erro ao procurar musica");
		}

		return musica;
	}

	public static Lista<Comunicado> getMusica(String estilo, String titulo) throws Exception {
		Lista<Comunicado> comunicado = new Lista<Comunicado>();
		try {
			String sql;

			sql = "SELECT * FROM MUSICA WHERE ESTILO LIKE ? AND TITULO LIKE ?";

			Connection.COMANDO.prepareStatement(sql);

			Connection.COMANDO.setString(1, "%" + estilo + "%");
			Connection.COMANDO.setString(2, "%" + titulo + "%");

			MeuResultSet resultado = (MeuResultSet) Connection.COMANDO.executeQuery();

			if (!resultado.first())
				throw new Exception("Nao cadastrado");

			while (resultado.next()) {
				comunicado.insereItem(new Comunicado("MUS", resultado.getString("Titulo"),
						resultado.getString("Cantor"), resultado.getString("Estilo"),
						Integer.toString(resultado.getInt("Duracao")), Double.toString(resultado.getDouble("Preco"))));
			}
		} catch (SQLException erro) {
			throw new Exception("Erro ao procurar musica");
		}

		return comunicado;
	}

	public static Lista<Comunicado> getEstilo() throws Exception {
		Lista<Comunicado> estilos = new Lista<Comunicado>();
		try {
			String sql;

			sql = "SELECT DISTINCT ESTILO FROM MUSICA";

			Connection.COMANDO.prepareStatement(sql);

			MeuResultSet resultado = (MeuResultSet) Connection.COMANDO.executeQuery();

			if (!resultado.first())
				throw new Exception("Nao cadastrado");
			
			while (resultado.next()) {
				estilos.insereItem(new Comunicado("EST", resultado.getString("Estilo")));
			}

		} catch (SQLException erro) {
			throw new Exception("Erro ao recuperar musica");
		}

		return estilos;
	}
}
