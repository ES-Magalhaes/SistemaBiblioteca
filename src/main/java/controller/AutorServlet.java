package controller;

import com.google.gson.Gson;
import dao.AutorDAO;
import model.Autor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/autores")
public class AutorServlet extends HttpServlet {
	private AutorDAO autorDAO;
	private Gson gson;

	@Override
	public void init() {
		autorDAO = new AutorDAO();
		gson = new Gson();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Autor> autores = autorDAO.listarAutores();
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(gson.toJson(autores));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		Autor autor = gson.fromJson(reader, Autor.class);
		
		autorDAO.salvarAutor(autor);
		
		resp.setStatus(HttpServletResponse.SC_CREATED);
	}

}
