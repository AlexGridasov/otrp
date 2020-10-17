package com.gri.alex.mcrsrvc.domain.model;

import java.util.Collection;

public interface ReadOnlyRepository<E, T> {

    boolean contains(T id);

    E get(T id);

    Collection<E> getAll();
}
