package com.example.todoapispring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    private static List<Todo> todoList;
    // Error message when the todo is not found
    private static final String TODO_NOT_FOUND = "Todo not found";

    public TodoController() {
        todoList = new ArrayList<>();
        todoList.add(new Todo(1, false, "Todo 1", 1));
        todoList.add(new Todo(2, false, "Todo 2", 2));
    }


    @GetMapping
    public ResponseEntity<List<Todo>> getTodos() {
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
     * API to delete a Todo
     * We can delete a particular todo using its ID as it is unique for every todo.
     */
    @DeleteMapping("/{todoId}")
    public ResponseEntity<?> deleteTodoById(@PathVariable Long todoId) {
        Todo todoToRemove = null;
        for(Todo todo : todoList) {
            if(todo.getId() == todoId) {
                todoToRemove = todo;
                break;
            }
        }

        if(todoToRemove != null) {
            todoList.remove(todoToRemove);
            String deleteSuccessMessage = "Todo deleted successfully";
            return ResponseEntity.status(HttpStatus.OK).body(deleteSuccessMessage);
        } else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
        }
    }

    /**
     * API to update an existing Todo using its ID
     * @param todoId
     * @param title
     * @param isCompleted
     * @param userId
     * @return
     */
    @PatchMapping("/{todoId}")
    ResponseEntity<?> updateTodoById(@PathVariable Long todoId, @RequestParam(required = false) String title, @RequestParam(required = false) Boolean isCompleted, Integer userId) {
        for(Todo todo : todoList) {
            if(todo.getId() == todoId) {
                if(title != null) {
                    todo.setTitle(title);
                }
                if(isCompleted != null) {
                    todo.setCompleted(isCompleted);
                }
                if(userId != null) {
                    todo.setUserId(userId);
                }

                return ResponseEntity.ok(todo);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
    }

}
