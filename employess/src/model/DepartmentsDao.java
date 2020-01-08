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
		int count = 0; //ī��Ʈ�� 0���� ����
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) cnt FROM departments";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();//���๮
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

	
	public List<Departments> selectDepartmentsList(){//list Departments selectDepartmentsList ����
		List<Departments> list = new ArrayList<Departments>();//����Ʈ ������ ArrayList<Departments> ����ش� 
		
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT dept_no,dept_name FROM departments";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees","root","java1234");
			stmt = conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			//������db  ���� department�� no���� name���� �����´�
			
			while(rs.next()){
				Departments departments = new Departments();
				//Ŭ���� Departments �� �������ش�
				departments.setDeptNo(rs.getString("dept_no"));
				//Departments �ȿ� ����Ǿ��ִ� no���� �����´�
				departments.setDeptName(rs.getString("dept_name"));
				//Departments �ȿ� ����Ǿ��ִ� n�̰��� �����´�
				list.add(departments);//list�� �����ش�
			}
			
		}catch(Exception e) {//���ܰ� �߻��� �ڵ�� ������ �˷��ش�
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
				//�����Ѱ͵��� �ݾ��ش� ������
				
			}catch(Exception e){//���ܰ� �߻��� �ڵ�� ������ �˷��ش�
				e.printStackTrace();
				
			}
		}
				
		
		
		return list; 
		
	}

}