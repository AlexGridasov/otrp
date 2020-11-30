package com.gri.alex.user.domain.repository;

public interface Repository<TE, T> extends ReadOnlyRepository<TE, T> {

    void add(TE entity);

    void remove(T id);

    void update(T id, TE entity);
}
