package com.example.todo.web;

import com.example.todo.domain.Todo;
import com.example.todo.dto.TodoDtos;
import com.example.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
public List<TodoDtos.Res> list() {
    return service.findAll().stream()
            .map(this::toRes)
            .collect(Collectors.toList());
}


    @GetMapping("/{id}")
    public TodoDtos.Res get(@PathVariable Long id) {
        return toRes(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoDtos.Res create(@Valid @RequestBody TodoDtos.CreateReq req) {
        return toRes(service.create(req));
    }

    @PatchMapping("/{id}")
    public TodoDtos.Res update(@PathVariable Long id, @Valid @RequestBody TodoDtos.UpdateReq req) {
        return toRes(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    private TodoDtos.Res toRes(Todo t) {
        return new TodoDtos.Res(
                t.getId(), t.getTitle(), t.getDescription(),
                t.isCompleted(), t.getDueAt(),
                t.getCreatedAt(), t.getUpdatedAt()
        );
    }
}
