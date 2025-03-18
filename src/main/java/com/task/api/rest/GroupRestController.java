package com.task.api.rest;

import com.task.api.dto.group.GroupRequestDto;
import com.task.api.dto.group.GroupResponseDto;
import com.task.api.model.User;
import com.task.api.service.GroupService;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
public class GroupRestController {

    @Autowired
    private GroupService groupService;

    @PostMapping
    @Operation(description = "Create a new group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created"),
            @ApiResponse(responseCode = "400", description = "Error on create group")
    })
    public ResponseEntity<?> create(@RequestBody GroupRequestDto groupDto, @AuthenticationPrincipal User user) {
        return groupService.create(groupDto, user);
    }

    @GetMapping("/list")
    @Operation(description = "Get all groups")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks found"),
            @ApiResponse(responseCode = "400", description = "Error on get groups")
    })
    public ResponseEntity<Page<GroupResponseDto>> getTasks(@PageableDefault(size=10, sort = {"createdAt"}) Pageable pag) {
        try {
            return ResponseEntity.ok(groupService.findGroups(pag));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
