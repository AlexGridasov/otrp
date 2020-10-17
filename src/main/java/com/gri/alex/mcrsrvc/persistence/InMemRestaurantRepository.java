package com.gri.alex.mcrsrvc.persistence;

import com.gri.alex.mcrsrvc.domain.model.Restaurant;
import com.gri.alex.mcrsrvc.domain.model.RestaurantRepository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemRestaurantRepository implements RestaurantRepository<Restaurant, String> {

    private final Map<String, Restaurant> entities;

    public InMemRestaurantRepository() {
        entities = new ConcurrentHashMap<>();
    }

    @Override
    public boolean containsName(String name) {
        return entities.containsKey(name);
    }

    @Override
    public void add(Restaurant entity) {
        entities.put(entity.getName(), entity);
    }

    @Override
    public void remove(String id) {
        entities.remove(id);
    }

    @Override
    public void update(Restaurant entity) {
        if (entities.containsKey(entity.getName())) {
            entities.put(entity.getName(), entity);
        }
    }

    @Override
    public boolean contains(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Restaurant> getAll() {
        return entities.values();
    }

    @Override
    public Restaurant get(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
