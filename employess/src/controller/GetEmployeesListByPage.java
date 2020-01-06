package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EmployeesDao;
import vo.Employees;

@WebServlet("/employees/getEmployeesListByPage")
public class GetEmployeesListByPage extends HttpServlet {
	private EmployeesDao employeesDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	employeesDao = new EmployeesDao();	
	int rowPerPage = 10;
	int currentPage = 1;
	if(request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	List<Employees> list =  employeesDao.selectEmployeesListByPage(currentPage, rowPerPage);
	
	int last = employeesDao.selectEmployeesRowCount();
	System.out.println("last인원 확인코드"+last);
	int lastPage = last/rowPerPage;
	System.out.println("last페이지 확인 코드"+lastPage);
	
	request.setAttribute("list", list);
	request.setAttribute("currentPage", currentPage);
	request.setAttribute("lastPage", lastPage);
	
	request.getRequestDispatcher("/WEB-INF/views/employees/employeesListByPage.jsp").forward(request, response);
	}

}
