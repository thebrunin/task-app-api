package com.task.api;

import com.task.api.enums.TaskStatus;
import com.task.api.helper.TaskHelper;
import com.task.api.model.Task;
import com.task.api.model.User;
import com.task.api.repository.TaskRepository;
import com.task.api.repository.UserRepository;
import com.task.api.service.TaskService;
import com.task.api.service.util.ModelMapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository repository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskService taskService;
    @Mock
    private ModelMapper modelMapper;

    private ModelMapperUtils modelMapperUtils;

    private User user;
    @BeforeEach
    void setUp() {
        user = new User();
        user.setId("userId");
        user.setEmail("user@example.com");
        user.setPassword("password123");
        user.setName("Brunin");
        modelMapper = new ModelMapper();
        this.modelMapperUtils = new ModelMapperUtils(modelMapper);
    }

    @Test
    void testCreateTaskWithResponsibleUserFound() {
        // Dado
        TaskHelper dto = new TaskHelper();
        dto.setResponsibleUser("responsibleUserId");

        User responsibleUser = new User();
        responsibleUser.setId("responsibleUserId");

        when(userRepository.findById("responsibleUserId")).thenReturn(Optional.of(responsibleUser));
        when(repository.save(any(Task.class))).thenAnswer(i -> i.getArgument(0));

        // Quando
        ResponseEntity<?> createTaskResponse = taskService.create(dto, user);

        // Então
        assertEquals(HttpStatus.CREATED, createTaskResponse.getStatusCode());
        TaskHelper createdTask = (TaskHelper) createTaskResponse.getBody();
        assertNotNull(createdTask);
        assertEquals(user.getId(), createdTask.getUserId());
        assertEquals(TaskStatus.CREATED, createdTask.getStatus());
        verify(repository).save(any(Task.class));
    }

    @Test
    void testCreateTaskWithResponsibleUserNotFound() {
        // Dado
        TaskHelper dto = new TaskHelper();
        dto.setResponsibleUser("nonExistentUserId");

        when(userRepository.findById("nonExistentUserId")).thenReturn(Optional.empty());

        // Quando
        ResponseEntity<?> createTaskResponse = taskService.create(dto, user);

        // Então
        assertEquals(HttpStatus.BAD_REQUEST, createTaskResponse.getStatusCode());
        String errorMessage = (String) createTaskResponse.getBody();
        assertEquals("Usuário responsável não encontrado", errorMessage);
    }

    @Test
    void testCreateTaskWithoutResponsibleUser() {
        // Dado
        TaskHelper dto = new TaskHelper();

        when(repository.save(any(Task.class))).thenAnswer(i -> i.getArgument(0));

        // Quando
        ResponseEntity<?> createTaskResponse = taskService.create(dto, user);
        TaskHelper createdTask = (TaskHelper) createTaskResponse.getBody();

        // Então
        assertNotNull(createdTask);
        assertEquals(user.getId(), createdTask.getUserId());
        assertEquals(TaskStatus.CREATED, createdTask.getStatus());
        verify(repository).save(any(Task.class));
    }

}
