package com.demo.todoappMVC.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.todoappMVC.model.Todo;
import com.demo.todoappMVC.service.TodoService;

@Controller
public class TodoController {
	
	@Autowired
	private TodoService todoService;

	@GetMapping("/listTodo")
	public String index(Model model) {
		model.addAttribute("todoList", todoService.findAll());		
		return "listTodo";
	}

	@GetMapping("/addTodo")
	public String addTodo(Model model) {
		Todo todo = new Todo();
		model.addAttribute("todo", todo);
		return "addTodo";
	}
	
    @PostMapping("/addTodo")
    public String addTodo(@ModelAttribute Todo todo) {
        return Optional.ofNullable(todoService.add(todo))
                       .map(t -> "redirect:/listTodo") // Trả về success nếu save thành công
                       .orElse("failedAdd"); // Trả về failed nếu không thành công

    }
    
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("todo") Todo todo) {
		todoService.save(todo);
		return "redirect:/listTodo";
	}
    
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("editTodo");
		Todo todo = todoService.get(id);
		mav.addObject("todo", todo);
		return mav;
	}
    
    @RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		todoService.delete(id);
		return "redirect:/listTodo";
	}
    
}
