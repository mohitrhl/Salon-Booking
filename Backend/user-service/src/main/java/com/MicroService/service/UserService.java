package com.MicroService.service;

import com.MicroService.exception.UserException;
import com.MicroService.modal.User;

import java.util.List;

public interface UserService {

    User getUserByEmail(String email) throws UserException;
    User getUserFromJwtToken(String jwt) throws Exception;
    User getUserById(Long id) throws UserException;
    List<User> getAllUsers();
}
