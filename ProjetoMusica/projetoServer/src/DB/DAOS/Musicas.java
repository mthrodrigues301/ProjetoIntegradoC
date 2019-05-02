package DB.DAOS;

import java.sql.SQLException;

import DB.BDSQLServer;
import DB.MeuResultSet;
import DB.BDO.Musica;

public class Musicas {

    public static Musica getMusica (int codigo) throws Exception
    {
    	Musica musica = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM MUSICAS " +
            	  "WHERE Id = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);
            
            BDSQLServer.COMANDO.setInt (1, codigo);
            
            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
				throw new Exception("Nao cadastrado");

			musica = new Musica(resultado.getInt("Id"), resultado.getString("Titulo"), 
					resultado.getString("Cantor"), resultado.getString("Estilo"),
					resultado.getInt("Duracao"), resultado.getDouble("Preco"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar livro");
        }

        return musica;
    }
    
    public static MeuResultSet getMusica () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM LIVROS";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar livros");
        }

        return resultado;
    }
}
