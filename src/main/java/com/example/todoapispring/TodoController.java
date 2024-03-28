package com.example.todoapispring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    private TodoService todoService; // anotherTodoService

    private TodoService todoservice2; // fakeTodoService

    private static List<Todo> todoList;
    // Error message when the todo is not found
    private static final String TODO_NOT_FOUND = "Todo not found";

    public TodoController(
            @Qualifier("anotherTodoService") TodoService todoService,
            @Qualifier("fakeTodoService")  TodoService todoservice2) {

        this.todoService = todoService;
        this.todoservice2 = todoservice2;
        todoList = new ArrayList<>();
        todoList.add(new Todo(1, false, "Todo 1", 1));
        todoList.add(new Todo(2, true, "Todo 2", 2));
    }


    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false) Boolean isCompleted) {
        System.out.println("Incoming query params: " + isCompleted + " " + this.todoService.doSomething());
        return ResponseEntity.ok(todoList);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo) {

        /**
         * we can use this annotation to set the status code @ResponseStatus(HttpStatus.CREATED)
         *
         */
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }


    @GetMapping("/{todoId}")
    public ResponseEntity<?> getTodoById(@PathVariable Long todoId) {
        for (Todo todo : todoList) {
            if (todo.getId() == todoId) {
                return ResponseEntity.ok(todo);
            }
        }
        // HW: Along with 404 status code, try to send a json {message: Todo not found}
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
    }
    /**
     * Api to delete a Todo
     * We can delete a particular todo by passing the id of the todo
     * */
    @DeleteMapping("/{todoId}")
    public ResponseEntity<?> deleteTodoById(@PathVariable Long todoId){
        Todo todoToRemove = null;
        for(Todo todo: todoList){
            if(todo.getId() == todoId){
                todoToRemove = todo;
                break;
            }
        }
        if(todoToRemove != null){
            todoList.remove(todoToRemove);
            String deleteSuccessMessage = "Todo with id " + todoId + " deleted successfully";
            return ResponseEntity.status(HttpStatus.OK).body(deleteSuccessMessage);

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
        }
    }

    /**
     * Api to update a Todo
     * We can update a particular todo by passing the id of the todo
     * @param todoId
     * @RequestBody
     * @return
     * * */
    @PatchMapping("/{todoId}")
    public ResponseEntity<?> updateTodoById(@PathVariable Long todoId, @RequestBody Todo updatedTodo){
        Todo todoToUpdate = null;
        for(Todo todo: todoList){
            if(todo.getId() == todoId){
                todoToUpdate = todo;
                break;
            }
        }
        if(todoToUpdate != null){
            if(updatedTodo.getTitle() != null) {
                todoToUpdate.setTitle(updatedTodo.getTitle());
            }
            if(updatedTodo.getUserId() != 0) {
                todoToUpdate.setUserId(updatedTodo.getUserId());
            }
            todoToUpdate.setCompleted(updatedTodo.isCompleted());
            return ResponseEntity.ok(todoToUpdate);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
        }
    }
}
