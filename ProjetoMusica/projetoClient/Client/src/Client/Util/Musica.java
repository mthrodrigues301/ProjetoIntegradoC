package Client.Util;

/**
 * A classe Musica é responsável por escrever novas musicas, 
 * ou manipular as informações das mesmas.
 * 
 * @author Eduardo Oliveira e Matheus Andrey
 */
public class Musica implements Cloneable, Comparable<Musica> {
	private int duracao;
	private String titulo, artista, estilo;
	private double preco;

	/**
	 * Valida se as informações da música está dentro do padrão definido.
	 *
	 * @param titulo Titulo da música.
	 * @param artista Banda/Cantor da música.
	 * @param estilo Estilo musical.
	 * @param duracao Duração total da música em segundos.
	 * @param preco Preço da música em Reais.
	 * @return true Caso as informações estejam corretas retorna Verdadeiro;
	 */
	public static boolean valida(String titulo, String artista, String estilo, int duracao, double preco) {
		if (titulo == null || titulo.length() > 80)
			return false;
		if (artista == null || artista.length() > 60)
			return false;
		if (estilo == null || estilo.length() > 30)
			return false;
		if (duracao < 0 || duracao > 500)
			return false;
		if (preco < 0 || preco > 9.99)
			return false;

		return true;
	}

	/**
	 * Instancia uma nova música caso a mesma possua dados validos.
	 *
	 * @param titulo Titulo da música.
	 * @param artista Banda/Cantor(a) da música.
	 * @param estilo Estilo da música.
	 * @param duracao Duração total da música em segundos.
	 * @param preco Preço da musica em Reais.
	 * @throws Exception Em caso de erro na validação dos dados lança uma exceção.
	 */
	public Musica(String titulo, String artista, String estilo, int duracao, double preco) throws Exception {
		if (!Musica.valida(titulo, artista, estilo, duracao, preco))
			throw new Exception("Musica Invalida!");

		this.duracao = duracao;
		this.titulo = titulo;
		this.artista = artista;
		this.estilo = estilo;
		this.preco = preco;
	}

	/**
	 * Método que busca o titulo de determinada música.
	 *
	 * @return Titulo da musica.
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Método que define um novo titulo para determinada música.
	 *
	 * @param titulo Titulo da música que será definido.
	 * @throws Exception Caso o novo titulo da música seja inválido lança uma exceção.
	 */
	public void setTitulo(String titulo) throws Exception {
		if (!Musica.valida(titulo, artista, estilo, duracao, preco))
			throw new Exception("Titulo inválido!");
		this.titulo = titulo;
	}

	/**
	 * Método que busca o nome da banda/cantor(a) de determinada música.
	 *
	 * @return Nome da Banda/Cantor da música.
	 */
	public String getArtista() {
		return artista;
	}

	/**
	 * Método que define um novo cantor(a)/banda para determinada música.
	 *
	 * @param artista Nome da banda/cantor(a) que será definida.
	 * @throws Exception Caso o nome da nova banda/cantor da música seja inválido lança uma exceção.
	 */
	public void setArtista(String artista) throws Exception {
		if (!Musica.valida(titulo, artista, estilo, duracao, preco))
			throw new Exception("Artista inválido!");
		this.artista = artista;
	}

	/**
	 * Método que busca o estilo musical de determinada música.
	 *
	 * @return Estilo musical.
	 */
	public String getEstilo() {
		return estilo;
	}

	/**
	 * Método que define um novo estilo musical para determinada música.
	 *
	 * @param estilo Estilo musical que será definido.
	 * @throws Exception Caso o novo estilo musical não seja válido lança uma exceção.
	 */
	public void setEstilo(String estilo) throws Exception {
		if (!Musica.valida(titulo, artista, estilo, duracao, preco))
			throw new Exception("Estilo inválido");
		this.estilo = estilo;
	}

	/**
	 * Método que busca o tempo de duração de determinada música.
	 *
	 * @return o tempo de duração da música.
	 */
	public int getDuracao() {
		return duracao;
	}

	/**
	 * Método que define uma nova duração para determinada música.
	 *
	 * @param duracao Tempo de duração da musica que será definido.
	 * @throws Exception Caso o novo tempo de duração da música esteja errado lança uma exceção.
	 */
	public void setDuracao(int duracao) throws Exception {
		if (!Musica.valida(titulo, artista, estilo, duracao, preco))
			throw new Exception("Duracao Inválida");
		this.duracao = duracao;
	}

	/**
	 * Método que busca o preço de determinada música.
	 *
	 * @return Preço da música.
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * Método que define um novo preço para determinada música.
	 *
	 * @param preco Preço da música a ser definido.
	 * @throws Exception Caso o novo preço da música seja inválido lança uma excessão.
	 */
	public void setPreco(double preco) throws Exception {
		if (!Musica.valida(titulo, artista, estilo, duracao, preco))
			throw new Exception("Preco Inválido!");
		this.preco = preco;
	}
	/**
	 * Método que passa as informações da música para formato de texto.
	 * 
	 * @return Retorna as informações da música em formato de texto.
	 */
	public String toString() {
		return (this.duracao / 60 < 10 ? "0" : "") + this.duracao / 60 + ":" + (this.duracao % 60 < 10 ? "0" : "")
				+ this.duracao % 60 + " " + this.titulo + " - " + this.artista + " - " + this.estilo + "   R$: "
				+ this.preco;
	}
	/**
	 * Método que compara a igualdade da música atual com outra música.
	 * 
	 * @return true Caso ambos Objetos sejam iguais retorna Verdadeiro
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Musica musica = (Musica) obj;

		if (this.titulo != musica.titulo)
			return false;
		if (this.artista != musica.artista)
			return false;
		if (this.estilo != musica.estilo)
			return false;
		if (this.duracao != musica.duracao)
			return false;
		if (this.preco != musica.preco)
			return false;

		return true;
	}
	/**
	 * Método que gera um valor hash code para determinada música.
	 * 
	 * @return Retorna um valor hash code de determinada música.
	 */
	public int hashCode() {
		int ret = 999;

		ret = ret * 5 + new Integer(this.titulo).hashCode();
		ret = ret * 5 + new Integer(this.artista).hashCode();
		ret = ret * 5 + new Integer(this.estilo).hashCode();
		ret = ret * 5 + new Integer(this.duracao).hashCode();
		ret = ret * 5 + new Integer((int) this.preco).hashCode();

		return ret;
	}

	/**
	 * Método que clona uma música ja existente.
	 *
	 * @param modelo Molde com parametros de uma musica ja existente.
	 * @throws Exception caso os dados da musica molde estejam incorretos lança uma exceção. 
	 */
	public Musica(Musica modelo) throws Exception {
		if (modelo == null)
			throw new Exception("Modelo Inválido!");

		this.titulo = modelo.titulo;
		this.artista = modelo.artista;
		this.estilo = modelo.estilo;
		this.duracao = modelo.duracao;
		this.preco = modelo.preco;
	}
	/**
	 * Método que copia e gera uma música igual a outra já existente.
	 * 
	 * @return Retorna uma música igual a outra já existente.
	 */
	public Object clone() {
		Musica ret = null;
		try {
			ret = new Musica(this);
		} catch (Exception ex) {
		}
		return ret;
	}
	/**
	 * Método que compara a música atual com outra para ver se ambas coincidem.
	 * 
	 * @return Retorna "0" caso as músicas sejam iguais.
	 */
	public int compareTo(Musica musica) {
		if (this.titulo.length() < musica.titulo.length())
			return -999;
		if (this.titulo.length() > musica.titulo.length())
			return 999;
		if (this.artista.length() < musica.artista.length())
			return -999;
		if (this.artista.length() > musica.artista.length())
			return 999;
		if (this.estilo.length() < musica.estilo.length())
			return -999;
		if (this.estilo.length() > musica.estilo.length())
			return 999;
		if (this.duracao < musica.duracao)
			return -999;
		if (this.duracao > musica.duracao)
			return 999;
		if (this.preco < musica.preco)
			return -999;
		if (this.preco > musica.preco)
			return 999;

		return 0;
	}
}