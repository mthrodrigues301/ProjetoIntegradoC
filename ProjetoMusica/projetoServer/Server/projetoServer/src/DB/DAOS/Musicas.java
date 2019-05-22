package DB.DAOS;

import java.sql.SQLException;

import DB.Util.Lista;
import DB.Util.MeuResultSet;
import DB.Config.*;
import DB.DBO.Musica;

public class Musicas {

	public static Lista<Musica> getMusicaByTitulo(String titulo) throws Exception {
		Lista<Musica> musicas = new Lista<Musica>();
		try {
			String sql;

			sql = "SELECT * " + "FROM MUSICA WHERE TITULO = ?";

			Connection.COMANDO.prepareStatement(sql);

			Connection.COMANDO.setString(1, titulo);

			MeuResultSet resultado = (MeuResultSet) Connection.COMANDO.executeQuery();

			if (!resultado.first())
				throw new Exception("Nao cadastrado");

			do {
				musicas.insereItem(new Musica(resultado.getString("Titulo"), resultado.getString("Cantor"),
						resultado.getString("Estilo"), resultado.getInt("Duracao"), resultado.getDouble("Preco")));
			} while (resultado.next());
		} catch (SQLException erro) {
			throw new Exception("Erro ao procurar música");
		}

		return musicas;
	}
	
	public static Lista<Musica> getAllMusicas() throws Exception {
		Lista<Musica> musicas = new Lista<Musica>();
		try {
			String sql;

			sql = "SELECT * FROM MUSICA";

			Connection.COMANDO.prepareStatement(sql);

			MeuResultSet resultado = (MeuResultSet) Connection.COMANDO.executeQuery();

			if (!resultado.first())
				throw new Exception("Nao cadastrado");

			do {
				musicas.insereItem(new Musica(resultado.getString("Titulo"), resultado.getString("Cantor"),
						resultado.getString("Estilo"), resultado.getInt("Duracao"), resultado.getDouble("Preco")));
			} while (resultado.next());
		} catch (SQLException erro) {
			throw new Exception("Erro ao procurar música");
		}

		return musicas;
	}

	public static Lista<Musica> getMusicaByCantor(String cantor) throws Exception {
		Lista<Musica> musicas = new Lista<Musica>();

		try {
			String sql;

			sql = "SELECT * " + "FROM MUSICA WHERE CANTOR = ?";

			Connection.COMANDO.prepareStatement(sql);

			Connection.COMANDO.setString(1, cantor);

			MeuResultSet resultado = (MeuResultSet) Connection.COMANDO.executeQuery();

			if (!resultado.first())
				throw new Exception("Nao cadastrado");

			do {
				musicas.insereItem(new Musica(resultado.getString("Titulo"), resultado.getString("Cantor"),
						resultado.getString("Estilo"), resultado.getInt("Duracao"), resultado.getFloat("Preco")));
			} while (resultado.next());
		} catch (SQLException erro) {
			throw new Exception("Erro ao procurar música");
		}

		return musicas;
	}

	public static Lista<Musica> getMusicaByEstilo(String estilo) throws Exception {
		Lista<Musica> musicas = new Lista<Musica>();

		try {
			String sql;

			sql = "SELECT * " + "FROM MUSICA WHERE ESTILO = ?";

			Connection.COMANDO.prepareStatement(sql);

			Connection.COMANDO.setString(1, estilo);

			MeuResultSet resultado = (MeuResultSet) Connection.COMANDO.executeQuery();

			if (!resultado.first())
				throw new Exception("Nao cadastrado");

			do {
				musicas.insereItem(new Musica(resultado.getString("Titulo"), resultado.getString("Cantor"),
						resultado.getString("Estilo"), resultado.getInt("Duracao"), resultado.getFloat("Preco")));
			} while (resultado.next());

		} catch (SQLException erro) {
			throw new Exception("Erro ao procurar música");
		}

		return musicas;
	}
	
	public static Lista<Musica> getMusicaPesq(String pesq) throws Exception {
		Lista<Musica> musicas = new Lista<Musica>();

		try {
			String sql;

			sql = "SELECT * FROM MUSICA WHERE (TITULO LIKE ?) OR (CANTOR LIKE ?) OR (DURACAO LIKE ?) OR (PRECO LIKE ?)";

			Connection.COMANDO.prepareStatement(sql);

			Connection.COMANDO.setString(1, "%" + pesq +"%");
			Connection.COMANDO.setString(2, "%" + pesq +"%");
			Connection.COMANDO.setString(3, "%" + pesq +"%");
			Connection.COMANDO.setString(4, "%" + pesq +"%");

			MeuResultSet resultado = (MeuResultSet) Connection.COMANDO.executeQuery();

			if (!resultado.first())
				throw new Exception("Nao cadastrado");

			do {
				musicas.insereItem(new Musica(resultado.getString("Titulo"), resultado.getString("Cantor"),
						resultado.getString("Estilo"), resultado.getInt("Duracao"), resultado.getFloat("Preco")));
			} while (resultado.next());

		} catch (SQLException erro) {
			throw new Exception("Erro ao procurar música");
		}

		return musicas;
	}
	
	public static Lista<Musica> getEstilo() throws Exception {
		Lista<Musica> estilos = new Lista<Musica>();
		try {
			String sql;

			sql = "SELECT DISTINCT ESTILO FROM MUSICA";

			Connection.COMANDO.prepareStatement(sql);

			MeuResultSet resultado = (MeuResultSet) Connection.COMANDO.executeQuery();

			if (!resultado.first())
				throw new Exception("Nao cadastrado");
			
			do {
				estilos.insereItem(new Musica("null", "null", resultado.getString("Estilo"), 0, 0));
			} while (resultado.next());
		} catch (SQLException erro) {
			throw new Exception("Erro ao recuperar musica");
		}

		return estilos;
	}
}
