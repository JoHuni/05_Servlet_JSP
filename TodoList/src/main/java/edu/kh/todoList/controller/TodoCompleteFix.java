package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.model.dto.Todo;
import edu.kh.todoList.model.service.TodoListService;
import edu.kh.todoList.model.service.TodoListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/todo/detail/complete")
public class TodoCompleteFix extends HttpServlet{
	TodoListService service = null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			service = new TodoListServiceImpl();
			int index = Integer.parseInt(req.getParameter("index"));

			Todo todo = service.todoDetailView(index);
			boolean detail = service.todoComplete(index);

			if(detail == false) {
				detail = true;
				req.setAttribute("detail", detail); 
				String path = "/WEB-INF/views/todo/detail.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
