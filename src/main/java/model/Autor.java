package model;

public class Autor {
    private int id;
    private String nome;
    private String nacionalidade;
    private String estilo;

    public Autor() {}

    public Autor(String nome, String nacionalidade, String estilo) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.estilo = estilo;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getEstilo() {
        return estilo;
    }
    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }
}
