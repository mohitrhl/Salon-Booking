package com.MicroService.service;

import com.MicroService.exception.UserException;
import com.MicroService.modal.User;

import java.util.List;

public interface UserService {

    User createUser(User user);
    User getUserById(Long id) throws UserException;
    List<User> getAllUsers();
    User updateUser(User user , Long id) throws UserException;
    String deleteUser(Long id) throws UserException;
}
