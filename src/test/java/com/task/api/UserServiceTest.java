package com.task.api;

import com.task.api.dto.UserDto;
import com.task.api.model.User;
import com.task.api.repository.UserRepository;
import com.task.api.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    private BCryptPasswordEncoder crypt;

    @BeforeEach
    void setUp() {
        crypt = new BCryptPasswordEncoder();
    }

    @Test
    public void createUser() {
        UserDto dto = new UserDto("bruno.email@gmail.com", "123456", "Bruno");
        User user = new User();
        user.setEmail(dto.email());
        user.setPassword(crypt.encode(dto.password()));
        user.setName(dto.name());

        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.create(dto);

        assertNotNull(createdUser);
        assertEquals(user.getEmail(), createdUser.getEmail());
        assertEquals(user.getName(), createdUser.getName());
        assertNotEquals(dto.password(), createdUser.getPassword());
    }
}
