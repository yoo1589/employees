<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- http://java.sun.com/jsp/jstl/core : 파일이름을 웹주소로 지정해놓은것임 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>departmentsList</title>

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
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<style>
.my-bgcolor {
	background-color: #5CD1E5
}

</style>

</head>
<body>
	<h1>부서 목록</h1>
	<div>
		<a href="${pageContext.request.contextPath }/">홈으로</a>
	</div>
	<table border="1">
		<thead>
			<tr>
				<th>부서 번호</th>
				<th>부서 이름</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="departments" items="${list}">
			<!--        EL    ^request로 받아와줄값--> 
				<tr>
					<td>${departments.deptNo }</td><!-- 화면에 출력 ELrkqtdmfh $를써주고 var.출력할것-->
					<td>${departments.deptName }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>