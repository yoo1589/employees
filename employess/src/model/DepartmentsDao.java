package model;

import java.sql.*;
import java.util.*;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import db.DBHelper;
import vo.Departments;

public class DepartmentsDao {
	
	public List<Map<String, Object>> selectGetDepartmentsCountByDeptNo(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn =null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "d.dept_no, d.dept_name ,count(d.dept_no) cnt from dept_emp de inner join departments d on de.dept_no = d.dept_no where de.to_date = '9999-01-01' group by d.dept_no";
		
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("d.dept_no",rs.getString("d.dept_no"));
				map.put("d.dept_name",rs.getString("d.dept_name"));
				map.put("cnt",rs.getInt("cnt"));
				
				list.add(map);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
		
	}
	
	
	public int selectDepartmentsRowCount() {
		int count = 0; //카운트는 0으로 시작
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) cnt FROM departments";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();//실행문
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch(Exception e) { 
			e.printStackTrace(); 
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace(); 
			}
		}
		return count;
	}

	
	public List<Departments> selectDepartmentsList(){//list Departments selectDepartmentsList 선언
		List<Departments> list = new ArrayList<Departments>();//리스트 변수에 ArrayList<Departments> 담아준다 
		
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT dept_no,dept_name FROM departments";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees","root","java1234");
			stmt = conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			//마리아db  안의 department의 no값과 name값을 가져온다
			
			while(rs.next()){
				Departments departments = new Departments();
				//클래스 Departments 를 선언해준다
				departments.setDeptNo(rs.getString("dept_no"));
				//Departments 안에 저장되어있는 no값을 가져온다
				departments.setDeptName(rs.getString("dept_name"));
				//Departments 안에 저장되어있는 n믇값을 가져온다
				list.add(departments);//list에 더해준다
			}
			
		}catch(Exception e) {//예외가 발생시 코드로 오류를 알려준다
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
				//선언한것들을 닫아준다 무조건
				
			}catch(Exception e){//예외가 발생시 코드로 오류를 알려준다
				e.printStackTrace();
				
			}
		}
				
		
		
		return list; 
		
	}

}