package com.gri.alex.restaurant.domain.repository;

import java.util.Collection;

public interface ReadOnlyRepository<TE, T> {

    //long Count;

    boolean contains(T id);

    TE get(T id);

    Collection<TE> getAll();
}
