package com.task.api.rest;

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
