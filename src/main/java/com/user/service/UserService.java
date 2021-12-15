package com.user.service;

import java.util.List;

import com.user.entity.User;

public interface UserService {

	List<User> findAllUser();

	User getUserById(long userId);

	User createUser(User user);

}
