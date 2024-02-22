package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/search")
public class SearchServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> nameList = new ArrayList<String>();
		nameList.add("조훈희");
		nameList.add("김훈희");
		nameList.add("박훈희");
		nameList.add("이훈희");
		
		String inputName = req.getParameter("inputName");
		String searchMessage = null;
		if(nameList.contains(inputName)) {
			searchMessage = String.format("%s은/는 %d번 째 인덱스에 존재합니다.", inputName, nameList.indexOf(inputName));
			req.setAttribute("searchMessage", searchMessage);
			
			String path = "/WEB-INF/views/practice/search_result.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
		}
		else {
			searchMessage = String.format("%s은/는 존재하지 않습니다.", inputName);
			HttpSession session = req.getSession();
			session.setAttribute("searchMessage", searchMessage);
			resp.sendRedirect("/error");
		}
	}
}
