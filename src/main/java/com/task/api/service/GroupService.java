package com.task.api.service;

import com.task.api.dto.group.GroupRequestDto;
import com.task.api.dto.group.GroupResponseDto;
import com.task.api.interfaces.CrudServiceInterface;
import com.task.api.model.Group;
import com.task.api.model.User;
import com.task.api.repository.GroupRepository;
import com.task.api.service.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class GroupService implements CrudServiceInterface<GroupRequestDto, GroupResponseDto> {

    @Autowired
    GroupRepository repository;

    @Override
    @Transactional
    public GroupResponseDto create(GroupRequestDto dto, User user) {
        Group group = this.fromDto(dto);
        group.setCode(UUID.randomUUID().toString());

        var groupDto = toDto(repository.save(group));

        if(groupDto != null)
            throw new RuntimeException("testando transactional");

        return groupDto;
    }

    @Override
    public Page<GroupResponseDto> find(Pageable pag) {
        return repository.findAll(pag).map(this::toDto);
    }

    private Group fromDto(GroupRequestDto dto) { return ModelMapperUtils.map(dto, Group.class); }

    private GroupResponseDto toDto(Group entity) { return new GroupResponseDto(entity); }
}

