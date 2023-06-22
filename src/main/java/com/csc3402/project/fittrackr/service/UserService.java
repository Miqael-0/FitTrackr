package com.csc3402.project.fittrackr.service;

import com.csc3402.project.fittrackr.dto.UserDto;
import com.csc3402.project.fittrackr.model.User;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
}