package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import db.DBHelper;
import vo.Employees;


public class EmployeesDao {
	public String login(Employees employees) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sessionEmpNo = null;
		String sql = "SELECT emp_no,first_name,last_name FROM employees WHERE emp_no = ? AND (first_name = ? AND last_name = ?)";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,employees.getEmpNo());
			stmt.setString(2, employees.getFirstName());
			stmt.setString(3,employees.getLastName());
			rs = stmt.executeQuery();
			if(rs.next()) {
				sessionEmpNo = rs.getString("emp_no");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return sessionEmpNo;
	}
	
	public List<Employees> selectEmployeesListByPage(int currentPage,int rowPerPage){
		List<Employees> list = new ArrayList<Employees>();
		Connection conn =null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees limit ?,?"; // ?몇행부터 ? 몇개부터
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			int startRow = (currentPage - 1) * rowPerPage;// CURRENTPAGE, rowPerPage -> STRATROW? (currentPage - 1)*rowPerPage;
			stmt.setInt(1,startRow);
			stmt.setInt(2,rowPerPage);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Employees employees = new Employees();
				employees.setEmpNo(rs.getInt("emp_no"));
				employees.setBirthDate(rs.getString("birth_date"));
				employees.setFirstName(rs.getString("first_name"));
				employees.setLastName(rs.getString("last_name"));
				employees.setGender(rs.getString("gender"));
				employees.setHireDate(rs.getString("hire_date"));
				list.add(employees);	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}

		return list;
	}
	
	
	public List<Map<String, Object>> selectEmployeesCountGroupByGender(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn =null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT gender, COUNT(gender) cnt FROM employees GROUP BY gender";
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("gender",rs.getString("gender"));
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
	
	
	
	public List<Employees> selectEmployeesListBetween(int begin,int end){
		List<Employees> list = new ArrayList<Employees>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees WHERE emp_no BETWEEN ? AND ? ORDER BY emp_no ASC";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, begin);
			stmt.setInt(2, end);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Employees employees = new Employees();
				employees.setEmpNo(rs.getInt("emp_no"));
				employees.setBirthDate(rs.getString("birth_date"));
				employees.setFirstName(rs.getString("first_name"));
				employees.setLastName(rs.getString("last_name"));
				employees.setGender(rs.getString("gender"));
				employees.setHireDate(rs.getString("hire_date"));
				list.add(employees);
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
		return list;
	}
	
	
	public int selectEmpNo(String str) {
		int empNo = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		if(str.equals("max")) {
			sql =  "SELECT MAX(emp_no) FROM employees ";
		} else if (str.equals("min")) {
			sql =  "SELECT MIN(emp_no) FROM employees ";
		}
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				empNo =rs.getInt(1);
				
			}
		} catch(Exception e) { 
			e.printStackTrace(); 
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return empNo;
	}
	
	public List<Employees> selectEmployeesListOrderBy(String order){
		List<Employees> list = new ArrayList<Employees>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		if(order.equals("asc")) {
			sql =  "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees ORDER BY first_name ASC LIMIT 50";
		} else if (order.equals("desc")) {
			sql =  "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees ORDER BY first_name DESC LIMIT 50";
		}
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Employees employees = new Employees();
				employees.setEmpNo(rs.getInt("emp_no"));
				employees.setBirthDate(rs.getString("birth_date"));
				employees.setFirstName(rs.getString("first_name"));
				employees.setLastName(rs.getString("last_name"));
				employees.setGender(rs.getString("gender"));
				employees.setHireDate(rs.getString("hire_date"));
				list.add(employees);
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
		return list;
	}
	
	
	public int selectEmployeesRowCount() {
		int count = 0;
		Connection conn = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) cnt FROM employees";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
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
		System.out.println(count);
		return count;
	}
	
	
	public List<Employees> selectEmployeesListByLimit(int limit) {
		List<Employees> list = new ArrayList<Employees>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees LIMIT ?";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, limit);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Employees employees = new Employees();
				employees.setEmpNo(rs.getInt("emp_no"));
				employees.setBirthDate(rs.getString("birth_date"));
				employees.setFirstName(rs.getString("first_name"));
				employees.setLastName(rs.getString("last_name"));
				employees.setGender(rs.getString("gender"));
				employees.setHireDate(rs.getString("hire_date"));
				list.add(employees);
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
		return list;
	}
}
