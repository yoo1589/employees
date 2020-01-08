package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DepartmentsDao;
import vo.Departments;

@WebServlet("/departments/getDepartmentsList")//주소로 받아줄 값을 정해준다
public class GetDepartmentsListServlet extends HttpServlet {
	private DepartmentsDao departmentsDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		departmentsDao = new DepartmentsDao();//departmentsDao를 선언
		List<Departments> list = departmentsDao.selectDepartmentsList();
		//departmentsDao메소드 안에있는 selectDepartmentsList의 값을 list에담아준다
		
		request.setAttribute("list", list);
					//	    ^기존값   ^새로운값  메소드는 속성값을 변경
		
		request.getRequestDispatcher("/WEB-INF/views/departments/departmentsList.jsp").forward(request, response);
	}
}
