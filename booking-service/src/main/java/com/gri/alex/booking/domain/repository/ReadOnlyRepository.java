package com.gri.alex.booking.domain.repository;

import java.util.Collection;

public interface ReadOnlyRepository<TE, T> {

    //long Count;

    boolean contains(T id);

    TE get(T id);

    Collection<TE> getAll();
}
