package DB.Config;

import DB.Util.MeuPreparedStatement;

public class Connection {
	public static final MeuPreparedStatement COMANDO;

	static {
		MeuPreparedStatement comando = null;

		try {
			comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver",
					"jdbc:sqlserver://fs5:1433;databasename=poo1913", "poo1913", "Inckh1");
		} catch (Exception erro) {
			System.err.println("Problemas de conexao com o BD");
			System.exit(0); // aborta o programa
		}

		COMANDO = comando;
	}
}