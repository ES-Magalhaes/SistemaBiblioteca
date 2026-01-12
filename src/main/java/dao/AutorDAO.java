package dao;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Autor;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

public class AutorDAO {
    public void salvarAutor(Autor autor) {
        String sql = "INSERT INTO autor (nome, nacionalidade, estilo) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.setString(3, autor.getEstilo());

            stmt.execute();
            System.out.println("Autor salvo com sucesso.");
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void atualizarAutor(Autor autor) {
        String sql = "UPDATE autor SET nome = ?, nacionalidade = ?, estilo = ? WHERE id_autor = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.setString(3, autor.getEstilo());
            stmt.setInt(4, autor.getId());

            stmt.executeUpdate();
            System.out.println("Autor atualizado com sucesso.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Autor> listarAutores() {
        String sql = "SELECT * FROM autor";
        List<Autor> autores = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();

            while(rset.next()) {
                Autor autor = new Autor();
                autor.setId(rset.getInt("id_autor"));
                autor.setNome(rset.getString("nome"));
                autor.setNacionalidade(rset.getString("nacionalidade"));
                autor.setEstilo(rset.getString("estilo"));
                autores.add(autor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return autores;
    }

    public void deletarAutor(int id) {
        String sql = "DELETE FROM autor WHERE id_autor = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            System.out.println("Autor deletado com sucesso.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
