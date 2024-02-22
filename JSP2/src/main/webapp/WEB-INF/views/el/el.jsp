<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>EL을 이용해서 request에 세팅된 속성 얻어와 출력하기</h1>
	<ul>
		<li>[작성법] : \${K} </li>
		<li>기본 자료형 : ${score}</li>
		<li>String : ${address}</li>
		<li>
			List 객체 : ${strList}
			<!-- EL 구문에서는 배열/리스트 관계 없이 요소 하나 얻어올 때 [index] 사용 -->
			<ul>
				<li>${strList[0]}</li>
				<li>${strList[1]}</li>
				<li>${strList[2]}</li>
				<li>${strList[3]}</li>
			</ul>
		</li>
		<li>
			DTO : ${book1};
			<ul>
				<%-- \${K.필드명} : 객체의 필드값을 얻어오기 위한 getter 호출 --%>
				<!-- **** EL을 이용해서 필드값을 얻어오려면 getter가 필수 !! ******  -->
				<li>${book1.title}</li> <!-- book1.getTitle()이랑 똑같음 -->
				<li>${book1.writer}</li>
				<li>${book1.price}</li>
			</ul>
		</li>
	</ul>
	
	<hr><hr>
	
    <h1> empty 연산자 </h1>
    <pre>
      - \${empty 객체 | 배열 | 리스트}
  
      - null 또는 빈칸 또는 비어있으면 true
        아니면 false
  
      - EL은 null을 빈칸으로 표현
        -> null == ""(빈칸)  ==  비어있음(출력할게 없어서 빈칸)
    </pre>

    <ul>
        <li> test1 = null; ->   ${empty test1}</li>
        <li> test2 = ""; ->     ${empty test2}</li>
        <li> test3 = null; ->   ${empty test3}</li>
        <li> test4 = new ArrayList<String>(); ->  ${empty test4}</li> 
        <li> test5 = new ArrayList<String>(); + add()->  ${empty test5}</li>
    </ul>
	
	<hr>
	
	<h1>EL을 이용해 파라미터 출력하기</h1>
	<pre>
		- \${param.K} 형식으로 작성
		
		* 테스트 진행 시 쿼리스트링 이용 *
		
		- 쿼리스트링 : 주소에 담긴 값을 나타내는 문자열
			-> 요청주소?K=V&K=V&K=V ~~~
		- form태그 GET 방식 제출
		  또는
		  a태그, JS, 주소창에 직접 작성하는 것도 가능!
	</pre>
	
	<h3>\${param.input} : ${param.input}</h3>
	<h3>\${param.input} : ${param.message}</h3>
	
	<hr><hr>
	
	<h2>
	    <a href="/scope">Servlet/JSP 내장 객체와 범위(Scope)</a>
	</h2>
	
	<pre>
	    Servlet/JSP에는 4종류 범위를 나타낸 내장 객체가 존재한다!
	    -> 각 종류마다 영향을 끼치는 범위가 달라진다
	
	    <h3>1. page (pageContext) : 현재 페이지</h3>
	    -> 현재 JSP에서만 사용 가능한 객체(Servlet X)
	
	    <h3>2. request (요청) == HttpServletRequest</h3>
	    -> 요청 받은 페이지(Servlet/JSP)와
	    위임 받은 페이지(Servlet/JSP)에서 유지되는 객체
	
	    <h3>3. session (입회, 접속)</h3>
	    - session : 서버에 접속한 클라이언트를
	                나타내거나, 관련 정보를 get/set 할 수 있는 객체
	                (session 객체는 서버에서 관리함)
	    
	    - [중요!] session은 브라우저 마다 하나씩 생성된다!!!
	    (새탭, 새창은 아님)
	
	    - [유지 범위]
	    사이트 접속 ~ 브라우저 종료 | 세션 만료
	
	    - session이 유지되는 상태에서는 
	    아무 곳에서나 가져다 사용할 수 있다!
	
	
	    <h3>4. apllication (ServletContext) </h3>
	    - 하나의 웹 애플리케이션 마다 1개만 생성되는 객체
	    (Server를 키면 1개만 생성되는 객체)
	
	    - application 객체는 어디서든 사용 가능
	
	    - [유지 범위]
	    서버 실행 ~ 서버 종료
	
	    <h3>5. 내장 객체의 우선 순위 :  page > request > session > application </h3>
	</pre>
	
	<h2>
        <a href="/find">forward(요청 위임)</a>
    </h2>
    <pre>
  
     - 클라이언트의 요청을 받은 Servlet/JSP에서 응답을 수행하는 것이 아닌
         HttpServletRequest, HttpServletResponse 객체를 
         다른 Servlet/JSP로 넘겨 대신 응답하게 하는 것
  
        - 보통 Servlet에서 요청 처리를 완료한 후  
          응답 화면을 만들 때 JSP로 위임함
  
        - RequestDispatcher 객체를 이용해 
        HttpServletRequest, HttpServletResponse를 
        지정된 Servlet/JSP로 요청 위임
  
        String path = "JSP 파일 경로";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    </pre>
</body>
</html>