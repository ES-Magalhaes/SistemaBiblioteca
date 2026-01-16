package model;

public class Livro {

	private int id;
	private String titulo;
	private int anoPublicacao;
	private String isbn;
	private StatusLivro status;
	private int idAutor;
	private String nomeAutor;

	public enum StatusLivro {
		DISPONIVEL("disponivel"), INDISPONIVEL("indisponivel");

		private final String valor;

		StatusLivro(String valor) {
			this.valor = valor;
		}

		public String getValor() {
			return valor;
		}

		public static StatusLivro fromString(String status) {
			for (StatusLivro s : StatusLivro.values()) {
				if (s.name().equalsIgnoreCase(status) || s.valor.equalsIgnoreCase(status)) {
					return s;
				}
			}
			throw new IllegalArgumentException("Status inválido: " + status);
		}
	}

	public Livro() {
	}

	public Livro(String titulo, int anoPublicacao, String isbn, StatusLivro status, int idAutor) {
		this.titulo = titulo;
		this.anoPublicacao = anoPublicacao;
		this.isbn = isbn;
		this.status = status;
		this.idAutor = idAutor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public StatusLivro getStatus() {
		return status;
	}

	public void setStatus(StatusLivro status) {
		this.status = status;
	}

	public int getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}
}
