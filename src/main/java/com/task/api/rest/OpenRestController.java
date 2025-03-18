package com.task.api.rest;

import com.task.api.model.User;
import com.task.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/open")
public class OpenRestController {

    @GetMapping("/status")
    public String getStatus() {
        return "Servidor online - ".concat(LocalDateTime.now().toString());
    }
}
