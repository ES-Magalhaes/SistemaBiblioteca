package model;

public class Livro {
    private int id;
    private String titulo;
    private int anoPublicacao;
    private String isbn;
    private StatusLivro status;
    private int idAutor;

    public enum StatusLivro {
        DISPONIVEL ("disponivel"),
        INDISPONIVEL ("indisponivel");

        private String valor;

        StatusLivro(String valor) {
            this.valor = valor;
        }
        public String getValor() {
            return valor;
        }
    }

    public Livro() {}

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
}