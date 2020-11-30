package com.gri.alex.user.domain.service;

import com.gri.alex.user.common.DuplicateUserException;
import com.gri.alex.user.common.InvalidUserException;
import com.gri.alex.user.domain.model.entity.User;
import com.gri.alex.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Service("userService")
public class UserServiceImpl extends BaseService<User, String>
        implements UserService {

    private UserRepository<User, String> userRepository;

    @Autowired
    public UserServiceImpl(UserRepository<User, String> userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) throws Exception {
        if (userRepository.containsName(user.getName())) {
            Object[] args = {user.getName()};
            throw new DuplicateUserException("duplicateUser", args);
        }
        if (user.getName() == null || "".equals(user.getName())) {
            Object[] args = {"User with null or empty name"};
            throw new InvalidUserException("invalidUser", args);
        }
        super.add(user);
    }

    @Override
    public Collection<User> findByName(String name) throws Exception {
        return userRepository.findByName(name);
    }


  @Override
  public void update(String id, User user) throws Exception {
    userRepository.update(id, user);
  }

    @Override
    public void delete(String id) {
        userRepository.remove(id);
    }

    @Override
    public User findById(String id) {
        return userRepository.get(id);
    }

    @Override
    public Collection<User> findByCriteria(Map<String, ArrayList<String>> name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
