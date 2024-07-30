package com.task.api.service.user;

import com.task.api.dto.UserDto;
import com.task.api.model.User;
import com.task.api.repository.UserRepository;
import com.task.api.service.SenderMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    SenderMailService senderMailService;

    public User create(UserDto userDto) {

        BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
        String cryptPass = crypt.encode(userDto.password());

        User user = new User();
        user.setEmail(userDto.email());
        user.setPassword(cryptPass);
        user.setName(userDto.name());

        //senderMailService.sendCreateAccountEmail(user);

        return repository.save(user);

    }
}
