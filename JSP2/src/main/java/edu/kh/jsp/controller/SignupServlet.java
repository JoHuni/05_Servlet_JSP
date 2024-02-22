package edu.kh.jsp.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	// a태그 요청(get) 처리
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// forward -> 요청에 따른 응답 화면이 존재함
		String path = "/WEB-INF/views/redirect/signup.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String pwCheck = req.getParameter("pwCheck");
		
		// pw, pwCheck가 일치하면 "(id) 회원 가입 성공!!!"
		// 일치하지 않으면 "비밀번호 불일치"
		// 메시지를 message 변수에 저장
		
		String message = null;
		if(pw.equals(pwCheck)) {
			message = id + "회원가입 성공!";
		}
		else {
			message = "비밀번호 불일치";
		}
		
		// Servlet 코드 수행이 완료된 후
		// - 원래 존재하던 페이지로 이동하거나
		// - 응답할 화면이 별도로 존재하지 않는 경우
		// - **** redirect **** 수행
		resp.sendRedirect("/signup");
		// - redirect는 무조건 GET 방식으로 요청
		
		// req.setAttribute("message", message); -> 안 됨
		// -> redirect(재요청) 수행시
		// 이전 요청이 담긴 request 객체가 삭제되고
		// 새로운 요청이 담긴 request 객체가 다시 생성
		// -> 이전 request에 message가 세팅되었기 때문에
		// 새로운 request에는 존재하지 않음 -> 출력 X
		// [해결 방법]
		// Session 객체를 이용하면 해결 가능
		// 왜? -> session은 redirect를 하건 말건
		// -> 세션이 만료되거나(정해놓은 시간) 브라우저를 종료하는게 아니면 유지됨
		
		HttpSession session = req.getSession(); // session 객체 얻어오기
		session.setAttribute("message", message);
	}
}
