<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- c == core(if, for문 등) --%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- fn = functions (길이, 자르기, 나누기) --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${todo.title}</title>
	<style>
		.detail{
			white-space: pre-wrap;
		}
	</style>
</head>
<body>
	<ul>
		<li>제목 : ${todo.title}</li>
		<li>완료 여부 : 
			<form action="/todo/detail/complete">
				<c:if test="${todo.complete}">O</c:if>
				<c:if test="${not todo.complete}">X</c:if>
				<button>수정</button>
			</form>
		</li>
		
		<li>등록일 : ${todo.regDate}</li>
		
		<li class="detail">${todo.detail}</li>
	</ul>
</body>
</html>