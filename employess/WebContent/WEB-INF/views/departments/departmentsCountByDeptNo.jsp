<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>employeesCountBygender</title>
</head>
<body>
	<h1>부서별 사원수 공사중</h1>
	<div>
		<a href="${pageContext.request.contextPath}/">홈으로</a>
	</div>
	<table border="1">
		<thead>
			<tr>
				<th>부서 넘버</th>
				<th>부서 이름</th>
				<th>사원수</th>
			</tr>
		</thead>
		<tbody>
			
			<d:forEach var="map" items="${list}">
				<tr>
					<td>${map.d.dept_no}</td>	
					<td>${map.d.dept_name}</td>
					<td>${map.cnt}</td>
				</tr>
			</d:forEach>
		</tbody>
	</table>
</body>
</html>