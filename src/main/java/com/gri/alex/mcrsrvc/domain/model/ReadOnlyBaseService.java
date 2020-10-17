package com.gri.alex.mcrsrvc.domain.model;

public abstract class ReadOnlyBaseService<TE, T> {

    private final ReadOnlyRepository<TE, T> repository;

    ReadOnlyBaseService(ReadOnlyRepository<TE, T> repository) {
        this.repository = repository;
    }
}
