package com.user.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.aop.TrackExecutionTime;
import com.user.entity.User;
import com.user.exception.ResourceNotFoundException;
import com.user.repository.UserRepository;
import com.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userReposiory;

	@Override
	@TrackExecutionTime
	public List<User> findAllUser() {
		return userReposiory.findAll();
	}

	@Override
	@TrackExecutionTime
	public User getUserById(long userId) {
		return this.userReposiory.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
	}

	@Override
	@TrackExecutionTime
	public User createUser(User user) {
		return userReposiory.save(user);
	}

}
