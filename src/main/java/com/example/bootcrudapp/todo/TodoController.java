package com.example.bootcrudapp.todo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    List<Todo> allTodos() {
        List<Todo> todos = this.todoRepository.findAll();
        return todos;
    }

    @PostMapping
    void addTodo(@RequestBody Todo todo) {
        this.todoRepository.save(todo);
    }

    @PutMapping("/{id}")
    void editTodo(@PathVariable long id, @RequestBody Todo todo) {
        Todo myTodo = this.todoRepository.findById(id).get();
        myTodo.setTitle(todo.getTitle());
        this.todoRepository.save(myTodo);
    }

    @DeleteMapping("/{id}")
    void deleteTodo(@PathVariable long id) {
        Todo todo = this.todoRepository.findById(id).get();
        this.todoRepository.delete(todo);
    }
}
