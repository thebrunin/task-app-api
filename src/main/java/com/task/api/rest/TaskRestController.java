package com.task.api.rest;

import com.task.api.helper.TaskHelper;
import com.task.api.model.User;
import com.task.api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody TaskHelper taskDto, @AuthenticationPrincipal User user) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(taskDto, user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Page<TaskHelper>> getTasks(@PageableDefault(size=10, sort = {"createdAt"}) Pageable pag) {
        return ResponseEntity.ok(taskService.getTasks(pag));
    }

    @PutMapping("/complete/{taskId}")
    public ResponseEntity<String> completeTask(@PathVariable String taskId, @AuthenticationPrincipal User user) {
        try {
            return ResponseEntity.ok(taskService.completeTask(taskId, user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
