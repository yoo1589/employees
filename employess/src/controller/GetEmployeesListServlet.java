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

@WebServlet("/employees/getEmployeesList")//�ּҰ��� �������ش�
public class GetEmployeesListServlet extends HttpServlet {
	private EmployeesDao employeesDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int limit = 10;
		//������ϰ��Ҽ������� ���� limit���� 10���� �����Ͽ��ش�
		if(request.getParameter("limit") != null) {//���� ����Ʈ���� 0�̾ƴϸ�
			limit = Integer.parseInt(request.getParameter("limit"));
			//          ^���ڿ��� int������ ��ȯ^limit���� �����´�
		}
		//System.out.println("getEmployeesListSevlet param :"+limit);//limit�����
		//����ѹ� ���ְ� Ȯ�� �Ϸ�        
		System.out.println("GetEmployeesListServlet param limit : " + limit);
				
		employeesDao = new EmployeesDao();
		//employeesDao Ŭ������ �����´�
		List<Employees> list = employeesDao.selectEmployeesListByLimit(limit);	
		
		request.setAttribute("list", list);
					//	    ^������   ^���ο  �޼ҵ�� �Ӽ����� ����
		
		request.getRequestDispatcher("/WEB-INF/views/employees/employeesList.jsp").forward(request, response);
	}
}
