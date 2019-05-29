package DB.DAOS;

import java.sql.SQLException;

import DB.Util.Lista;
import DB.Util.MeuResultSet;
import DB.Config.*;
import DB.DBO.Musica;

/**
 * DAO Musicas é a classe que faz as determinadas buscas no banco,
 *  de acordo com as requisições do usuário.
 *  
 *  @author Eduardo Oliveira e Matheus Andrey.
 */
public class Musicas {
	
	/**
	 * Método que busca todas as musicas no banco de dados.
	 *
	 * @return todas as musicas existentes no banco de dados.
	 * @throws Exception caso não sejam encontradas musicas,
	 *  ou ocorra um erro ao conectar-se com o banco de dados.
	 */
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

	/**
	 * Método para a pesquisa de musicas no banco de dados,
	 *  consultando-as pelo estilo musical desejado.
	 *
	 * @param estilo Estilo musical.
	 * @return Todas as musicas de determinado estilo musical.
	 * @throws Exception caso não sejam encontradas musicas, 
	 * ou ocorra um erro ao conectar-se com o banco de dados.
	 */
	public static Lista<Musica> getMusicaByEstilo(String estilo) throws Exception {
		Lista<Musica> musicas = new Lista<Musica>();

		try {
			String sql;

			sql = "SELECT * FROM MUSICA WHERE ESTILO = ?";

			Connection.COMANDO.prepareStatement(sql);

			Connection.COMANDO.setString(1, estilo);

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
	
	/**
	 * Método para a pesquisa de musicas no banco de dados, 
	 *  consultando-as pelo estilo musical e pelas informações de pesquisa.
	 *
	 * @param estilo Estilo musical.
	 * @param pesq Informações para a pesquisa.
	 * @return Retorna as musicas que condizem com os parametros solicitados.
	 * @throws Exception caso não sejam encontradas musicas,
	 *  ou ocorra um erro ao conectar-se com o banco de dados.
	 */
	public static Lista<Musica> getMusicaByEstiloEPesq(String estilo, String pesq) throws Exception {
		Lista<Musica> musicas = new Lista<Musica>();

		try {
			String sql;

			sql = "SELECT * FROM MUSICA WHERE ESTILO = ? AND ((TITULO LIKE ?) OR (CANTOR LIKE ?))";

			Connection.COMANDO.prepareStatement(sql);

			Connection.COMANDO.setString(1, estilo);
			Connection.COMANDO.setString(2, "%" + pesq +"%");
			Connection.COMANDO.setString(3, "%" + pesq +"%");

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
	
	/**
	 * Método para a pesquisa de musicas no banco de dados de acordo com as informações
	 *  passadas pelo usuário.
	 *
	 * @param pesq Informações de pesquisa.
	 * @return Retorna as musicas que condizem com as informações da pesquisa.
	 * @throws Exception caso não sejam encontradas musicas,
	 *  ou ocorra um erro ao conectar-se com o banco de dados.
	 */
	public static Lista<Musica> getMusicaPesq(String pesq) throws Exception {
		Lista<Musica> musicas = new Lista<Musica>();

		try {
			String sql;

			sql = "SELECT * FROM MUSICA WHERE (TITULO LIKE ?) OR (CANTOR LIKE ?)";

			Connection.COMANDO.prepareStatement(sql);

			Connection.COMANDO.setString(1, "%" + pesq +"%");
			Connection.COMANDO.setString(2, "%" + pesq +"%");

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
	
	/**
	 * Método para recuperação dos estilos musicais existentes no banco de dados.
	 *
	 * @return Estilos musicais perentes no banco de dados.
	 * @throws Exception caso não sejam encontrados estilos,
	 *  ou ocorra um erro ao conectar-se com o banco de dados. Exception the exception
	 */
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
