<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>employeesCountBygender</title>
</head>
<body>
	<h1>사원 목록</h1>
	<div>
		<a href="${pageContext.request.contextPath}/">홈으로</a>
	</div>
	<table border="1">
		<thead>
			<tr>
				<th>사원 성별</th>
				<th>사원수</th>
			
			</tr>
		</thead>
		<tbody>
			
			<d:forEach var="map" items="${list}">
				<tr>
					<td>${map.gender}</td>	
					<td>${map.cnt}</td>
					
				</tr>
			</d:forEach>
		</tbody>
	</table>
</body>
</html>