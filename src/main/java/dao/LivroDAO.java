package dao;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Livro;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

public class LivroDAO {
	public void salvarLivro(Livro livro) {
		System.out.println("=== salvarLivro CHAMADO ===");
		System.out.println("Titulo: " + livro.getTitulo());
		System.out.println("Ano: " + livro.getAnoPublicacao());
		System.out.println("ISBN: " + livro.getIsbn());
		System.out.println("Status: " + livro.getStatus());
		System.out.println("ID Autor: " + livro.getIdAutor());

		String sql = "INSERT INTO livro (titulo, ano_publicacao, isbn, status, id_autor) VALUES (?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionFactory.createConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, livro.getTitulo());
			stmt.setInt(2, livro.getAnoPublicacao());
			stmt.setString(3, livro.getIsbn());
			stmt.setString(4, livro.getStatus().getValor());
			stmt.setInt(5, livro.getIdAutor());

			stmt.execute();
			System.out.println("Livro salvo com sucesso.");
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

	public List<Livro> listarLivros() {
		String sql = "SELECT l.*, a.nome AS nome_autor FROM livro l INNER JOIN autor a ON l.id_autor = a.id_autor";

		List<Livro> livros = new ArrayList<>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnection();
			stmt = conn.prepareStatement(sql);
			rset = stmt.executeQuery();

			while (rset.next()) {
				Livro livro = new Livro();
				livro.setId(rset.getInt("id_livro"));
				livro.setTitulo(rset.getString("titulo"));
				livro.setAnoPublicacao(rset.getInt("ano_publicacao"));
				livro.setIsbn(rset.getString("isbn"));
				livro.setStatus(Livro.StatusLivro.fromString(rset.getString("status")));
				livro.setIdAutor(rset.getInt("id_autor"));
				livro.setNomeAutor(rset.getString("nome_autor"));
				livros.add(livro);
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
		return livros;
	}

	public void atualizarLivro(Livro livro) {
		String sql = "UPDATE livro SET titulo = ?, ano_publicacao = ?, isbn = ?, status = ?, id_autor = ? WHERE id_livro = ?";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionFactory.createConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, livro.getTitulo());
			stmt.setInt(2, livro.getAnoPublicacao());
			stmt.setString(3, livro.getIsbn());
			stmt.setString(4, livro.getStatus().getValor());
			stmt.setInt(5, livro.getIdAutor());
			stmt.setInt(6, livro.getId());

			stmt.executeUpdate();
			System.out.println("Livro atualizado com sucesso.");
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

	public void deletarLivro(int id) {
		String sql = "DELETE FROM livro WHERE id_livro = ?";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionFactory.createConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.executeUpdate();
			System.out.println("Livro deletado com sucesso.");
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