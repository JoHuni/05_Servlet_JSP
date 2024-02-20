package edu.kh.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orderPizza")
public class Practice1 extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pizza = req.getParameter("pizza");
		String type = req.getParameter("type");
		String ordererName = req.getParameter("ordererName");
		String ordererAddress = req.getParameter("ordererAddress");
		
		String[] options = req.getParameterValues("opt");
		
		int price = 0;
		switch(pizza) {
			case "치즈피자":
				price += 8900;
				break;
			case "불고기피자":
			case "포테이토피자":
				price += 11900;
				break;
			case "스테이크피자":
				price += 13900;
				break;
		}
		
		if(type.equals("cheesecrust")) {
			price += 3000;
		}
		
		if(options != null) {
			for(String o : options) {
				switch(o) {
					case "치즈오븐크림스파게티":
						price += 6900;
						break;
					case "콜라":
						price += 2000;
						break;
					case "갈릭디핑소스":
						price += 500;
						break;
				}
			}
		}
		resp.setContentType("text/html; charset=utf-8;");
		
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
		sb.append(String.format("<li>피자 : %s</li>", pizza));
		String temp = type.equals("crust") ? "기본" : "치즈 크러스트";
		sb.append(String.format("<li>크러스트 : %s</li>", temp));
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
