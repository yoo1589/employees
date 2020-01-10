package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DepartmentsDao;


@WebServlet("/departments/getDepartmentsCountByDeptNo")
public class GetDepartmentsCountByDeptNoServlet extends HttpServlet {
	private DepartmentsDao departmentsDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		departmentsDao = new DepartmentsDao();
		List<Map<String, Object>> list = departmentsDao.selectGetDepartmentsCountByDeptNo();
		System.out.println(list);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/views/departments/departmentsCountByDeptNo.jsp").forward(request, response);
	}


}
