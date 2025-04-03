package com.task.api.interfaces;

import com.task.api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudServiceInterface<D, R> {
    public R create(D  dto, User user);
    public Page<R> find(Pageable pag);
}
