package Default;

import java.io.Serializable;

public class Comunicado implements Serializable {
	private String comando, complemento1, complemento2, complemento3, complemento4, complemento5;

	public Comunicado(String comando, String complemento1, String complemento2, String complemento3,
			String complemento4, String complemento5) throws Exception {

		if (this.comando == null || comando.equals(""))
			throw new Exception("Comando ausente");

		if (this.complemento1 == null || complemento1.equals(""))
			throw new Exception("Complemento1 ausente");

		if (this.complemento2 == null || complemento2.equals(""))
			throw new Exception("Complemento2 ausente");

		if (this.complemento3 == null || complemento3.equals(""))
			throw new Exception("Complemento3 ausente");

		if (this.complemento4 == null || complemento4.equals(""))
			throw new Exception("Complemento4 ausente");

		if (this.complemento5 == null || complemento5.equals(""))
			throw new Exception("Complemento5 ausente");

		this.comando = comando;
		this.complemento1 = complemento1;
		this.complemento2 = complemento2;
		this.complemento3 = complemento3;
		this.complemento4 = complemento4;
		this.complemento5 = complemento5;
	}

	// Construtor de comunicado com 4 complementos
	public Comunicado(String comando, String complemento1, String complemento2, String complemento3,
			String complemento4) throws Exception {
		if (this.comando == null || comando.equals(""))
			throw new Exception("Comando ausente");
		if (this.complemento1 == null || complemento1.equals(""))
			throw new Exception("Complemento1 ausente");
		if (this.complemento2 == null || complemento2.equals(""))
			throw new Exception("Complemento2 ausente");
		if (this.complemento3 == null || complemento3.equals(""))
			throw new Exception("Complemento3 ausente");
		if (this.complemento4 == null || complemento4.equals(""))
			throw new Exception("Complemento4 ausente");

		this.comando = comando;
		this.complemento1 = complemento1;
		this.complemento2 = complemento2;
		this.complemento3 = complemento3;
		this.complemento4 = complemento4;
	}

	// Construtor de comunicado com 3 complementos
	public Comunicado(String comando, String complemento1, String complemento2, String complemento3) throws Exception {
		if (this.comando == null || comando.equals(""))
			throw new Exception("Comando ausente");
		if (this.complemento1 == null || complemento1.equals(""))
			throw new Exception("Complemento1 ausente");
		if (this.complemento2 == null || complemento2.equals(""))
			throw new Exception("Complemento2 ausente");
		if (this.complemento3 == null || complemento3.equals(""))
			throw new Exception("Complemento3 ausente");
		this.comando = comando;
		this.complemento1 = complemento1;
		this.complemento2 = complemento2;
		this.complemento3 = complemento3;
	}

	// Construtor de comunicado com 2 complementos
	public Comunicado(String comando, String complemento1, String complemento2) throws Exception {
		if (this.comando == null || comando.equals(""))
			throw new Exception("Comando ausente");
		if (this.complemento1 == null || complemento1.equals(""))
			throw new Exception("Complemento1 ausente");
		if (this.complemento2 == null || complemento2.equals(""))
			throw new Exception("Complemento2 ausente");
		this.comando = comando;
		this.complemento1 = complemento1;
		this.complemento2 = complemento2;
	}

	// Construtor de comunicado com 1 complementos
	public Comunicado(String comando, String complemento1) throws Exception {
		if (this.comando == null || comando.equals(""))
			throw new Exception("Comando ausente");
		if (this.complemento1 == null || complemento1.equals(""))
			throw new Exception("Complemento1 ausente");
		this.comando = comando;
		this.complemento1 = complemento1;
	}

	// Construtor de comunicado com sem complementos
	public Comunicado(String comando) throws Exception {
		if (this.comando == null || comando.equals(""))
			throw new Exception("Comando ausente");
		this.comando = comando;
	}

	public String getComando() {
		return comando;
	}

	public String getComplemento1() throws Exception {
		if (this.complemento1 == null)
			throw new Exception("Complemento1 indisponivel");
		return this.complemento1;
	}

	public String getComplemento2() throws Exception {
		if (this.complemento2 == null)
			throw new Exception("Complemento2 indisponivel");
		return this.complemento2;
	}

	public String getComplemento3() throws Exception {
		if (this.complemento3 == null)
			throw new Exception("Complemento3 indisponivel");
		return this.complemento3;
	}

	public String getComplemento4() throws Exception {
		if (this.complemento4 == null)
			throw new Exception("Complemento4 indisponivel");
		return this.complemento4;
	}

	public String getComplemento5() throws Exception {
		if (this.complemento5 == null)
			throw new Exception("Complemento5 indisponivel");
		return this.complemento5;
	}

	public String toString() {
		String ret = this.comando;
		if (this.complemento1 != null)
			ret = ret + " " + complemento1;
		if (this.complemento2 != null)
			ret = ret + " " + complemento2;
		if (this.complemento3 != null)
			ret = ret + " " + complemento3;
		if (this.complemento4 != null)
			ret = ret + " " + complemento4;
		if (this.complemento5 != null)
			ret = ret + " " + complemento5;
		return ret;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Comunicado comunicado = (Comunicado) obj;
		if (!this.comando.equals(comunicado.comando))
			return false;
		if ((this.complemento1 == null && comunicado.complemento1 != null)
				|| (this.complemento1 != null && comunicado.complemento1 == null))
			return false;
		if (this.complemento1 != null && comunicado.complemento1 != null
				&& !this.complemento1.equals(comunicado.complemento1))
			return false;
		if ((this.complemento2 == null && comunicado.complemento2 != null)
				|| (this.complemento2 != null && comunicado.complemento2 == null))
			return false;
		if (this.complemento2 != null && comunicado.complemento2 != null
				&& !this.complemento2.equals(comunicado.complemento2))
			return false;
		if ((this.complemento3 == null && comunicado.complemento3 != null)
				|| (this.complemento3 != null && comunicado.complemento3 == null))
			return false;
		if (this.complemento3 != null && comunicado.complemento3 != null
				&& !this.complemento3.equals(comunicado.complemento3))
			return false;
		if ((this.complemento4 == null && comunicado.complemento4 != null)
				|| (this.complemento4 != null && comunicado.complemento4 == null))
			return false;
		if (this.complemento4 != null && comunicado.complemento4 != null
				&& !this.complemento4.equals(comunicado.complemento4))
			return false;
		if ((this.complemento5 == null && comunicado.complemento5 != null)
				|| (this.complemento5 != null && comunicado.complemento5 == null))
			return false;
		if (this.complemento5 != null && comunicado.complemento5 != null
				&& !this.complemento5.equals(comunicado.complemento5))
			return false;
		return true;
	}

	public int hashCode() {
		int ret = 1;
		ret = 2 * ret + this.comando.hashCode();
		if (this.complemento1 != null)
			ret = 2 * ret + complemento1.hashCode();
		if (this.complemento2 != null)
			ret = 2 * ret + complemento2.hashCode();
		if (this.complemento3 != null)
			ret = 2 * ret + complemento3.hashCode();
		if (this.complemento4 != null)
			ret = 2 * ret + complemento4.hashCode();
		if (this.complemento5 != null)
			ret = 2 * ret + complemento5.hashCode();
		return ret;
	}

	// como nao ha metodos, alem dos construtores que
	// alterem atributos do this, nao teremos clone e
	// nem construtor de copia
}