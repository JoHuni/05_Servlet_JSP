<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>집가고싶다</h1>
	<form action="/todo-search" method="POST">
		할 일 찾기 : <input type="text" name="title">
		<button>제출하기</button>
	</form>
</body>
</html>