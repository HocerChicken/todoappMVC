package com.demo.todoappMVC.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.todoappMVC.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{
}
