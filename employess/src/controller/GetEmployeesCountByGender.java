package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EmployeesDao;


@WebServlet("/employees/getEmployeesCountByGender")
public class GetEmployeesCountByGender extends HttpServlet {
	private EmployeesDao employeesDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		employeesDao = new EmployeesDao();
		List<Map<String, Object>> list = employeesDao.selectEmployeesCountGroupByGender();
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/employees/getEmployeesCountByGender.jsp").forward(request, response);
	}


}
