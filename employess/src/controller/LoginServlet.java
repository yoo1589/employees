package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmployeesDao;
import vo.Employees;



@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private EmployeesDao employeesDao;
	// LOGIN FORM
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}
	
	// LOGIN ACTION
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		int empNo = Integer.parseInt(request.getParameter("empNo"));
		// 디버깅 ->단위 테스트

		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(empNo);

		Employees employees = new Employees();
		employees.setFirstName(firstName);
		employees.setLastName(lastName);
		employees.setEmpNo(empNo);
		employeesDao = new EmployeesDao();
		String sessionEmpNo = employeesDao.login(employees);
		System.out.println(sessionEmpNo);
		HttpSession session = request.getSession();
		session.setAttribute("sessionEmpNo", sessionEmpNo);

		if(sessionEmpNo == null) { //처음으로 접속이거나 로그인을 하지않으면 반응한다.
			response.sendRedirect(request.getContextPath()+"/login");
		} else {
			response.sendRedirect(request.getContextPath()+"/index");
		}


	}

}
