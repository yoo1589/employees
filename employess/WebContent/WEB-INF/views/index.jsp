<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head>

<style>
	 li {
        display: inline;
        border: 1px solid #bcbcbc;
        padding: 10px;
      }
      a:link {color:green; text-decoration:none;}
      a:visited {color:teal; text-decoration:none;}
	  a:hover {color:red; text-decoration:underline;}
	  a:active {color:yellow; text-decoration:underline;}
	
	br {
		size : 10pt;
	}
</style>

<body>
	<!-- 
		1. 디렉티브 	- %@ 	- x
		2. 선언 		- %! 	- 사용x
		3. 스크립트릿 	- % 	- jstl(사용자 생성한 태그)
		4. 표현식 		- %= 	- el
	 -->
<div class = "container">

	<div style = "aglign : center">
	
	 <h1>Index</h1>
	 
	 <hr width = "100%" color = "blue" size = "1">
	 
	<h2>테이블 정보</h2>
	
	<table class = "table tablr-horver">
	
	<c:if test="${sessionLoginEmpNo != null }">
	<a href="${pageContext.request.contextPath }/logout"> 로그아웃</a>
	<!-- Controller -> /LogoutServlet -->
	</c:if>
	<div>
		<table border="1">
			<thead>
				<tr>
					<th>테이블 이름</th>
					<th>전체 행의수</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>departments</td>
					<td>${departmentsRowCount}</td>
				</tr>
				<tr>
					<td>employees</td>
					<td>${employeesRowCount}</td>
				</tr>
				<tr>
					<td>dept_manager</td>
					<td>${deptManagerRowCount}</td>
				</tr>
				<tr>
					<td>dept_emp</td>
					<td>${deptEmpRowCount}</td>
				</tr>
				<tr>
					<td>titles</td>
					<td>${titlesRowCount}</td>
				</tr>
				<tr>
					<td>salaries</td>
					<td>${salariesRowCount}</td>
				</tr>
			</tbody>
		</table>
	<hr width = "100%" color = "blue" size = "1">
	</div>
	
	<!-- WEB APP 네비게이션 -->
	
	<div class = "container">
		<ul>
			<li style = "color : red"><a href="${pageContext.request.contextPath }/departments/getDepartmentsList">부서 목록</a></li>
			<!-- 
				request.getContextPath() -> /emp-class (프로젝트명)
			-->
			<br><br>
			<li><a href="${pageContext.request.contextPath }/employees/getEmployeesList">사원 목록(limit 10)</a></li>
			<li>
				사원목록 - 
				<a href="${pageContext.request.contextPath }/employees/getEmployeesListOrderBy?order=asc">오름차순(limit 50)</a> 
				<a href="${pageContext.request.contextPath }/employees/getEmployeesListOrderBy?order=desc">내림차순(limit 50)</a>
			</li>
			<br><br>
			<li>
			<a href="${pageContext.request.contextPath}/titles/getTitlesListDistinct">업무(Distinct)</a>
			</li>
			<br><br>
			<li>
			<a href="${pageContext.request.contextPath}/salaries/getSalariesStatistics">연봉 통계값(count, sum , avg, max, std)</a>
			</li>
		<br><br>
			<li>
			<a href="${pageContext.request.contextPath}/employees/getEmployeesCountByGender">사원 수(성별 group by gender) </a>
			</li>
				<br><br>
			
			<!-- 
				url : /departments/getDepartmentsCountByDeptNo
				contoller: getDepartmentsCountByDeptNoServlet.class
				model: DepartmentsDao.selectGetDepartmentsCountByDeptNo()
				views: /WEB-INF/views/departments/departmentsCountByDeptNo.jsp
			 -->
			
			<li>
			<a href="${pageContext.request.contextPath }/employees/getEmployeesListByPage">사원 목록 페이징(10명씩)</a>
			</li>
			<br><br>
			
		</ul>
	</div>
	
	<hr width = "100%" color = "blue" size = "1">
	
	<div>
		<form method="post" action="${pageContext.request.contextPath}/employees/getEmployeesListBetween">
			<input type="number" name="begin">~<input type="number" name="end">
			<button type="submit">사원 목록(between ... and...)</button>
			
			(${minEmpNo} ~ ${maxEmpNo})
			
		</form>
	</div>
	
	
	<div>
		표현식 - employees table row count : <%=request.getAttribute("employeesRowCount") %>
		<br>
		EL - employees table row count : ${employeesRowCount}
	</div>
</body>
</html>