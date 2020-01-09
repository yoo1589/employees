<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>사원 목록(10명씩 페이징 되ㅇ어있음)</h1>
<div>
		<a href="${pageContext.request.contextPath}/">홈으로</a>
</div>
<!-- 테이블  -->
	<table border="1">
		<thead>
			<tr>
				<th>사원 번호</th>
				<th>사원 생일</th>
				<th>사원 성</th>
				<th>사원 이름</th>
				<th>사원 성별</th>
				<th>입사 날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="employees" items="${list}">
				<tr>
					<td>${employees.empNo }</td>
					<td>${employees.birthDate }</td>
					<td>${employees.firstName }</td>
					<td>${employees.lastName }</td>
					<td>${employees.gender }</td>
					<td>${employees.hireDate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<!-- 이전  -->
	<c:if test="${currentPage > 1 }"> <!-- if(currentPage > 1) -->
	<a href="${pageContext.request.contextPath }/employees/getEmployeesListByPage?currentPage=${currentPage-1}">이전</a>
	</c:if>
	
	<c:if test="${currentPage < lastPage}">
	<a href="${pageContext.request.contextPath }/employees/getEmployeesListByPage?currentPage=${currentPage+1}">다음</a>
	</c:if>

<!-- 다음  -->

</body>
</html>