package com.task.api.service;

import com.task.api.enums.TaskStatus;
import com.task.api.helper.DefaultResponseHelper;
import com.task.api.helper.TaskHelper;
import com.task.api.model.Task;
import com.task.api.model.User;
import com.task.api.repository.TaskRepository;
import com.task.api.repository.UserRepository;
import com.task.api.service.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskService {
    @Autowired
    TaskRepository repository;
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> create(TaskHelper dto, User user) {
        if (dto.getResponsibleUser() != null && !dto.getResponsibleUser().isEmpty()) {
            User responsibleUser = userRepository.findById(dto.getResponsibleUser()).orElse(null);
            if (responsibleUser == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário responsável não encontrado");
            }
        }
        Task task = this.fromDto(dto);
        task.setUserId(user.getId());
        task.setStatus(TaskStatus.CREATED);
        task.setCreatedAt(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(repository.save(task)));
    }

    public Page<TaskHelper> getTasks(Pageable pag) {
        return repository.findAll(pag).map(this::toDto);
    }

    public String completeTask(String taskId, User user) {
        Task task = repository.findById(taskId).orElse(null);
        if (task == null) {
            throw new IllegalArgumentException("Tarefa não encontrada");
        }

        task.setStatus(TaskStatus.COMPLETED);
        repository.save(task);

        return "Tarefa concluída com sucesso!";
    }

    private Task fromDto(TaskHelper dto) { return ModelMapperUtils.map(dto, Task.class); }

    private TaskHelper toDto(Task entity) { return ModelMapperUtils.map(entity, TaskHelper.class); }
}

