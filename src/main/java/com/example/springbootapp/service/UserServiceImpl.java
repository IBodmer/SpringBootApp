package com.example.springbootapp.service;

import com.example.springbootapp.model.User;
import com.example.springbootapp.repo.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("ne po plany"));
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }
    @Transactional
    @Override
    public void update(Long id, User user) {
        User user1 = userRepo.findById(id).orElseThrow(() -> new RuntimeException("ne po plany"));
        user1.setUserName(user.getUserName());
        user1.setLastName(user.getLastName());
        user1.setEmail(user.getEmail());
        user1.setAge(user.getAge());
        userRepo.save(user1);
    }
    @Transactional
    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }
}
