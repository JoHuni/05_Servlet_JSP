package edu.kh.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/** Servlet 작성 순서
 * 1) HttpServlet 상속 받기
 * 2) @WebServlet() 어노테이션 작성하기
 */
// @WebServlet("/ex2") : <servlet>, <servlet-mapping> 태그를 컴파일시 추가하라는 어노테이션

// 1) 현재 클래스를 Servlet으로 등록 -> <servlet>
// 2) 등록된 Servlet과 () 내 요청 주소 매핑 -> <serlvet-mapping>
// 3) doGet() 또는 doPost() 오버라이딩 -> 요청 method에 따라 수행
// 4) 필요한 로직 처리
//	- 파라미터 얻어오기
//	- 필요한 요청 처리 구문
// 5) 응답 형태 지정 + 응답 스트림 얻어오기
// 6) 스트림을 통해서 응답 데이터(html 코드) 출력하기

@WebServlet("/ex2")
public class ExampleServlet2 extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파라미터 얻어오기
		String chicken = req.getParameter("chicken");	
		String type = req.getParameter("type");		// 뼈 / 순살
		String ordererName = req.getParameter("ordererName");
		String ordererAddress = req.getParameter("ordererAddress");
		
		// String[] req.getParameterValues("name 속성값")
		// -> 같은 name 속성 값을 가지는 값을 모두 모아
		// 하나의 String[]로 만들어 반환하는 메서드
		String[] options = req.getParameterValues("opt");
		
		// - 아무 것도 선택하지 않으면 null
		// - 하나라도 선택하면 String[] 반환
		System.out.println("[서버] : /ex2 요청됨");
		
		// ----------------------------------------------------------
		
		// 선택한 치킨, 뼈/순살, 옵션에 따라 달라지는 가격 계산하기
		int price = 0;
		switch(chicken) {
			case "후라이드":
				price += 10000;
				break;
			case "양념":
				price += 11000;
				break;
			case "마늘":
			case "간장":
				price += 12000;
				break;			
		}
		if(type.equals("boneless")) {
			price += 2000;
		}
		
		// 선택된 옵션이 있을 경우
		if(options != null) {
			for(String opt : options) { // 배열에 저장된 모든 값을 0번부터 끝까지 다 순차접근
				switch(opt) {
					case "치킨무":
						price += 500; 
						break;
					case "콜라":
						price += 2000; 
						break;
					case "치즈볼":
						price += 3000; 
						break;						
				}
			}
		}// price 계산 끝
		
		// 1) 응답하는 문서(데이터)의 형식과 문자 인코딩 지정
		resp.setContentType("text/html; charset=utf-8;");
		// 2) 출력용 스트림 얻어오기
		PrintWriter out = resp.getWriter();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>");
		sb.append(String.format("%s님 주문 영수증", ordererName));
		sb.append("</title>");
		
		sb.append("<body>");
		
		sb.append("<h3>");
		sb.append("주문자명 : ");
		sb.append(ordererName);
		sb.append("</h3>");
		sb.append(String.format("<h3>주소 : %s<h3>", ordererAddress));
		
		sb.append("<ul>");
		sb.append(String.format("<li>치킨 : %s</li>", chicken));
		String temp = type.equals("bone") ? "뼈" : "순살";
		sb.append(String.format("<li>뼈/순살 : %s</li>", temp));
		if(options != null) {
			sb.append("<li>");
			sb.append("선택한 옵션 : ");
			for(String opt : options) {
				sb.append(opt + " ");
			}
			sb.append("</li>");
		}
		sb.append("</ul>");
		sb.append(String.format("<h3>합계 : %d원</h3>", price));
		sb.append("</body>");
		
		sb.append("</head>");
		sb.append("</html>");
		
		out.print(sb.toString());
	}
}
