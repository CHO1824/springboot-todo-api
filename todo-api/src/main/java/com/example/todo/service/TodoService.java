package com.example.todo.service;

import com.example.todo.domain.Todo;
import com.example.todo.dto.TodoDtos;
import com.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository repo;

    public TodoService(TodoRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<Todo> findAll() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Todo findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Todo not found: " + id));
    }

    @Transactional
public Todo create(TodoDtos.CreateReq req) {
    Todo t = new Todo();
    t.setTitle(req.getTitle());
    t.setDescription(req.getDescription());
    t.setDueAt(req.getDueAt());
    return repo.save(t);
}

@Transactional
public Todo update(Long id, TodoDtos.UpdateReq req) {
    Todo t = findById(id);
    if (req.getTitle() != null) t.setTitle(req.getTitle());
    if (req.getDescription() != null) t.setDescription(req.getDescription());
    if (req.getCompleted() != null) t.setCompleted(req.getCompleted());
    if (req.getDueAt() != null) t.setDueAt(req.getDueAt());
    return t;
}


    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
