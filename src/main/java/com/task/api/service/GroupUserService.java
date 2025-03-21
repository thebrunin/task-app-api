package com.task.api.service;

import com.task.api.dto.group.GroupRequestDto;
import com.task.api.dto.group.GroupResponseDto;
import com.task.api.model.Group;
import com.task.api.model.User;
import com.task.api.repository.GroupRepository;
import com.task.api.repository.UserRepository;
import com.task.api.service.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
// TODO: REFATORAR TODO O SERVICE QUE FOI COPIADO DO GroupService
@Service
public class GroupUserService {

    @Autowired
    GroupRepository repository;
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> create(GroupRequestDto dto, User user) {
        Group group = this.fromDto(dto);
        group.setCode(UUID.randomUUID().toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(repository.save(group)));
    }

    public Page<GroupResponseDto> findGroups(Pageable pag) {
        return repository.findAll(pag).map(this::toDto);
    }

    private Group fromDto(GroupRequestDto dto) { return ModelMapperUtils.map(dto, Group.class); }

    private GroupResponseDto toDto(Group entity) { return ModelMapperUtils.map(entity, GroupResponseDto.class); }
}

