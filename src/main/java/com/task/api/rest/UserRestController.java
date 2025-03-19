package com.task.api.rest;

import com.task.api.dto.user.UserRequestDto;
import com.task.api.model.User;
import com.task.api.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping
    @Operation(description = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Error on create user")
    })
    public ResponseEntity<?> create(@RequestBody @Validated UserRequestDto userRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/list")
    @Operation(description = "Find all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "400", description = "Error on find users")
    })
    public ResponseEntity<Page<User>> getUsers(@PageableDefault(size=10, sort = {"name"}) Pageable pag) {
        var page = userService
                .find(pag);
        return ResponseEntity.ok(page);
    }
}
