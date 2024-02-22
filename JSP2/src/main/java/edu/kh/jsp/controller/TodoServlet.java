package edu.kh.jsp.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.util.List;

import edu.kh.jsp.model.dto.Todo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/todo-search")
public class TodoServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		String path = "/WEB-INF/views/todo/todo_result.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
		
		String title = req.getParameter("title");
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try{
			fis = new FileInputStream("/io_test/TodoList.dat");
			ois = new ObjectInputStream(fis);
			List<Todo> todoList = (List<Todo>) ois.readObject();
			req.setAttribute("todoList", todoList);
			if(todoList.contains(title)) {
				String message = todoList.toString();
			}
			else {
				String message = String.format("%s는 존재하지 않습니다", title);
				resp.sendRedirect("/todo/error");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
