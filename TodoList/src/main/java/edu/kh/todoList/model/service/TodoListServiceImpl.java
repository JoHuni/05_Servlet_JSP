package edu.kh.todoList.model.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.todoList.model.dao.TodoListDao;
import edu.kh.todoList.model.dao.TodoListDaoImpl;
import edu.kh.todoList.model.dto.Todo;

public class TodoListServiceImpl implements TodoListService{

	// 필드
	private TodoListDao dao = null;
	// 기본 생성자
	public TodoListServiceImpl() throws FileNotFoundException, IOException, ClassNotFoundException {
		// TodoListServiceImpl 객체가 생성될 때
		dao = new TodoListDaoImpl();
	}
	/* todoListFullView()*/
	@Override
	public Map<String, Object> todoListFullView() {
		// 1. 할 일 목록 DAO에서 얻어오기
		List<Todo> todoList = dao.todoListFullView();
		
		// 2. 할 일 목록에서 완료(complete 필드 값이 true)인 요소가 몇 개인지 카운트
		int completeCount = 0;
		
		for(Todo todo : todoList) { // TodoList 순차접근
			// isComplete() == getComplete()랑 같음. boolean식 get
			if(todo.isComplete()) {
				// ture인 경우
				completeCount++;
			}
		}
		
		// 3. todoList, completeCount를 저장할 Map 생성
		// -> 메서드는 반환 타입이 하나로 제한되기 때문에
		// -> 여러 타입을 반환해야 하는 경우 Map으로 묶어서 반환
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("todoList", todoList);
		map.put("completeCount", completeCount);
		 
		// 4. map 반환
		return map;
	}
	/*--------------------------------------------------------*/
	@Override
	public String dateFormat(LocalDateTime regDate) {
		// yyyy-MM-dd HH:mm:ss 형태 날짜 반환
		
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 
		 // regDate에 저장되어있는 날짜 데이터를
		 // formatter에 지정된 형식으로 변경
		 String formattedDateTime = regDate.format(formatter);
		
		return formattedDateTime;
	}
	@Override
	public Todo todoDetailView(int index) {
		
		// 1. DAO에 있는 todoList에서 index번째 요소(Todo) 반환 받기
		//    없으면 null 반환
		Todo todo = dao.todoDetailView(index);
		return todo;
	}

	@Override
	public int todoAdd(String title, String detail) throws FileNotFoundException, IOException {
		// Todo 객체 생성
		Todo todo = new Todo(title, detail, false, LocalDateTime.now());
		// LocalDateTime.now() : 현재 시간 반환
		
		// DAO 메서드 호출 흐 결과 반환 받기
		int index = dao.todoAdd(todo); // 추가된 인덱스 또는 -1 반환
		return index;
	}

	@Override
	public boolean todoComplete(int index) throws FileNotFoundException, IOException {
		// Serivce 메서드가 별도 처리를 하지 않음
		// -> 여기서 아무 것도 구현할 게 없다고 해도 서비스 건너뛰면 안 됨;
		//
		return dao.todoComplete(index);
	}

	@Override
	public boolean todoUpdate(int index, String title, String detail) throws FileNotFoundException, IOException {
		return dao.todoUpdate(index, title, detail);
	}

	@Override
	public String todoDelete(int index) throws FileNotFoundException, IOException {
		Todo todo =  dao.todoDelete(index);
		if(todo == null) {
			return null;
		}
		return todo.getTitle(); // 제목 반환
	}

}
