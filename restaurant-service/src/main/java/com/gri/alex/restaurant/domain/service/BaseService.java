package com.gri.alex.restaurant.domain.service;

import com.gri.alex.restaurant.domain.repository.Repository;

import java.util.Collection;

public abstract class BaseService<TE, T> extends ReadOnlyBaseService<TE, T> {

    private Repository<TE, T> repository;

    BaseService(Repository<TE, T> repository) {
        super(repository);
        this.repository = repository;
    }

    public void add(TE entity) throws Exception {
        repository.add(entity);
    }

    public Collection<TE> getAll() {
        return repository.getAll();
    }
}
