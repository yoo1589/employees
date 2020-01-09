package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TitlesDao;

@WebServlet("/titles/getTitlesListDistinct")
public class GetTitlesListDistinctServlet extends HttpServlet {
	private TitlesDao titlesDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		titlesDao = new TitlesDao();
		List<String> list = titlesDao.selectTitlesListDistinct();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/titles/GetTitlesListDistinct.jsp").forward(request,response);
		
	}

}
