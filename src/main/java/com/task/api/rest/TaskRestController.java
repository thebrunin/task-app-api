package com.task.api.rest;

import com.task.api.helper.TaskHelper;
import com.task.api.model.User;
import com.task.api.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(description = "Create a new task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created"),
            @ApiResponse(responseCode = "400", description = "Error on create task")
    })
    public ResponseEntity<?> create(@RequestBody TaskHelper taskDto, @AuthenticationPrincipal User user) {
        return taskService.create(taskDto, user);
    }

    @GetMapping("/list")
    @Operation(description = "Get all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks found"),
            @ApiResponse(responseCode = "400", description = "Error on get tasks")
    })
    public ResponseEntity<Page<TaskHelper>> getTasks(@PageableDefault(size=10, sort = {"createdAt"}) Pageable pag) {
        try {
            return ResponseEntity.ok(taskService.getTasks(pag));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/complete/{taskId}")
    @Operation(description = "Mark a task as completed")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task completed"),
            @ApiResponse(responseCode = "400", description = "Error on complete task")
    })
    public ResponseEntity<String> completeTask(@PathVariable String taskId, @AuthenticationPrincipal User user) {
        try {
            return ResponseEntity.ok(taskService.completeTask(taskId, user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
