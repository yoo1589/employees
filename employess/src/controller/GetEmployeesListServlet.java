package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EmployeesDao;
import model.SalariesDao;
import vo.Employees;

@WebServlet("/employees/getEmployeesList")//주소값을 설정해준다
public class GetEmployeesListServlet extends HttpServlet {
	private EmployeesDao employeesDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int limit = 10;
		//다출력하게할순없으니 변수 limit값을 10으로 설정하여준다
		if(request.getParameter("limit") != null) {//만약 리미트값이 0이아니면
			limit = Integer.parseInt(request.getParameter("limit"));
			//          ^문자열을 int값으로 전환^limit값을 가져온다
		}
		//System.out.println("getEmployeesListSevlet param :"+limit);//limit값출력
		//출력한번 해주고 확인 완료        
		System.out.println("GetEmployeesListServlet param limit : " + limit);
				
		employeesDao = new EmployeesDao();
		//employeesDao 클래스를 가져온다
		List<Employees> list = employeesDao.selectEmployeesListByLimit(limit);	
		
		request.setAttribute("list", list);
					//	    ^기존값   ^새로운값  메소드는 속성값을 변경
		
		request.getRequestDispatcher("/WEB-INF/views/employees/employeesList.jsp").forward(request, response);
	}
}
