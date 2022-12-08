package com.demo.todoappMVC.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.todoappMVC.model.Todo;
import com.demo.todoappMVC.model.TodoValidator;
import com.demo.todoappMVC.repository.TodoRepository;

@Service
@Transactional
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private TodoValidator validator;
	
	public List<Todo> findAll(){
		return todoRepository.findAll();
	}
	
	public Todo add(Todo todo) {
		if(validator.isValid(todo)) {
			return todoRepository.save(todo);
		}
		return null;
	}
	
	public void save(Todo product) {
		todoRepository.save(product);
	}
	
	public Todo get(long id) {
		return todoRepository.findById(id).get();
	}
	
	public void delete(long id) {
		todoRepository.deleteById(id);
	}
}
