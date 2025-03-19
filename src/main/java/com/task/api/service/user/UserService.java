package com.task.api.service.user;

import com.task.api.config.exceptions.UserEmailAlreadyExistsException;
import com.task.api.dto.user.UserRequestDto;
import com.task.api.model.User;
import com.task.api.repository.UserRepository;
import com.task.api.service.SenderMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    SenderMailService senderMailService;

    public User create(UserRequestDto userRequestDto) {

        boolean userEmailAlreadyExists = repository.countByEmail(userRequestDto.email()) > 0;

        if (userEmailAlreadyExists)
            throw new UserEmailAlreadyExistsException();

        BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
        String cryptPass = crypt.encode(userRequestDto.password());

        User user = new User();
        user.setEmail(userRequestDto.email());
        user.setPassword(cryptPass);
        user.setName(userRequestDto.name());

        //senderMailService.sendCreateAccountEmail(user);

        return repository.save(user);

    }

    public Page<User> find(Pageable page) {
        return repository.findAll(page);
    }
}
