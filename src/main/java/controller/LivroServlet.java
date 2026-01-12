package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dao.LivroDAO;
import model.Livro;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/livros")
public class LivroServlet extends HttpServlet {
	private LivroDAO livroDAO;
	private Gson gson;

	@Override
	public void init() {
		livroDAO = new LivroDAO();
		gson = new Gson();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Livro> livros = livroDAO.listarLivros();

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(gson.toJson(livros));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		
		JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

	    Livro livro = new Livro();
	    livro.setTitulo(json.get("titulo").getAsString());
	    livro.setAnoPublicacao(json.get("anoPublicacao").getAsInt());
	    livro.setIsbn(json.get("isbn").getAsString());

	    String statusStr = json.get("status").getAsString();
	    livro.setStatus(Livro.StatusLivro.fromString(statusStr));

	    livro.setIdAutor(json.get("idAutor").getAsInt());

	    livroDAO.salvarLivro(livro);

	    resp.setStatus(HttpServletResponse.SC_CREATED);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idParam = req.getParameter("id");
		if (idParam == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().print("ID do Livro é obrigatório");
			return;
		}
		
		int id = Integer.parseInt(idParam);
		BufferedReader reader = req.getReader();
		Livro livro = gson.fromJson(reader, Livro.class);
		livro.setId(id);
		livroDAO.atualizarLivro(livro);
		
		resp.setStatus(HttpServletResponse.SC_OK);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String idParam = req.getParameter("id");
		if (idParam == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().print("ID do Livro é obrigatório");
			return;
		}
		
		int id = Integer.parseInt(idParam);
		livroDAO.deletarLivro(id);
		
		resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}

}