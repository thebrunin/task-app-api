package com.task.api.rest;

import com.task.api.dto.group.GroupRequestDto;
import com.task.api.dto.group.GroupResponseDto;
import com.task.api.model.User;
import com.task.api.service.GroupService;
import com.task.api.service.GroupUserService;
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/group")
public class GroupRestController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupUserService groupUserService;

    @PostMapping
    @Operation(description = "Create a new group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created"),
            @ApiResponse(responseCode = "400", description = "Error on create group")
    })
    public ResponseEntity<?> create(@RequestBody GroupRequestDto groupDto, @AuthenticationPrincipal User user) {
        try {
            GroupResponseDto response = groupService.create(groupDto, user);

            if (!CollectionUtils.isEmpty(groupDto.users())) {
                // todo vincular grupo ao user
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/list")
    @Operation(description = "Get all groups")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks found"),
            @ApiResponse(responseCode = "400", description = "Error on get groups")
    })
    public ResponseEntity<Page<GroupResponseDto>> getTasks(@PageableDefault(size=10, sort = {"createdAt"}) Pageable pag) {
        try {
            return ResponseEntity.ok(groupService.find(pag));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
