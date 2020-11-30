package com.gri.alex.user.domain.service;

import com.gri.alex.user.domain.model.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public interface UserService {

    void add(User user) throws Exception;

    void update(String id, User user) throws Exception;

    void delete(String id) throws Exception;

    User findById(String id) throws Exception;

    Collection<User> findByName(String name) throws Exception;

    Collection<User> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;
}
