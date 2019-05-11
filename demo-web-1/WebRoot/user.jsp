<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, edu.tjut.Student"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>欢迎登录</h2>
	<table>
		<thead>
			<tr>
				<th>用户名</th>
				<th>密码</th>
			</tr>
		</thead>

		<c:forEach items="${allusers }" var="user" varStatus="status">
			<c:choose>
				<c:when test="${status.index % 2 == 0}">
					<tr style="background-color: olive;">
					<tr>
						<td style="border: 1px solid #000;">${user.username }</td>
						<td style="border: 1px solid #000;">${user.password }</td>
					</tr>
				</c:when>
				<c:when test="${status.index % 2 == 1}">
					<tr>
						<td style="border: 1px solid #000;">${user.username }</td>
						<td style="border: 1px solid #000;">${user.password }</td>
					</tr>
				</c:when>
			</c:choose>
		</c:forEach>
	</table>
</body>
</html>