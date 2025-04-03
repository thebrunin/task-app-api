package com.task.api.service;

import com.task.api.dto.task.TaskResponseDto;
import com.task.api.enums.TaskStatus;
import com.task.api.dto.task.TaskRequestDto;
import com.task.api.interfaces.CrudServiceInterface;
import com.task.api.model.Task;
import com.task.api.model.User;
import com.task.api.repository.TaskRepository;
import com.task.api.repository.UserRepository;
import com.task.api.service.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Service
public class TaskService  implements CrudServiceInterface<TaskRequestDto, TaskResponseDto> {
    @Autowired
    TaskRepository repository;
    @Autowired
    UserRepository userRepository;

    @Override
    public TaskResponseDto create(TaskRequestDto dto, User user) throws RuntimeException{
        if (!ObjectUtils.isEmpty(dto.responsibleUser())) {
            User responsibleUser = userRepository.findById(dto.responsibleUser()).orElse(null);
            if (responsibleUser == null) {
                throw new RuntimeException("Usuário responsável não encontrado");
            }
        }
        Task task = this.fromDto(dto);
        task.setUserId(user.getId());
        task.setStatus(TaskStatus.CREATED);
        task.setCreatedAt(LocalDateTime.now());

        return toDto(repository.save(task));
    }

    @Override
    public Page<TaskResponseDto> find(Pageable pag) {
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

    private Task fromDto(TaskRequestDto dto) { return ModelMapperUtils.map(dto, Task.class); }

    private TaskResponseDto toDto(Task entity) { return new TaskResponseDto(entity); }
}

