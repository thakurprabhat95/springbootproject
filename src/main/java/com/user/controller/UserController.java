package com.user.controller;

import java.util.List;

import org.hibernate.dialect.pagination.FirstLimitHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.VO.Audit;
import com.user.VO.ResponseTemplateVO;
import com.user.entity.User;
import com.user.exception.ResourceNotFoundException;
import com.user.repository.UserRepository;
import com.user.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	
	//inject userReposiory 
	@Autowired
	private UserRepository userRepository;
	
	//inject restTemplate
	@Autowired
	private RestTemplate restTemplate;
	
	//inject userService
	@Autowired
	private UserService userService;
	
	
	

	// get all users
	@GetMapping("/getAllUser")
	@ApiOperation(value = "/getAllUser", notes="get all users",tags = {"user service"})
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "User Data"),
			@ApiResponse(code = 404, message = "Invalid data"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public List<User> getAllUsers() {
		
		return userService.findAllUser();
	}
	
	//get audit data using user table
		@GetMapping("/getuser/{id}")
		@ApiOperation(value = "/getuser/{id}",notes = "get audit data using user", tags = {"user service"})
		@ApiResponses(value = { 
				@ApiResponse(code = 200, message = "User Data"),
				@ApiResponse(code = 404, message = "Invalid data"),
				@ApiResponse(code = 500, message = "Internal server error") })
		public ResponseEntity<ResponseTemplateVO> getUserAndAudit(@PathVariable("id") Long userId) throws ResourceNotFoundException
		{
			
			
			ResponseTemplateVO responseTemplateVO=new ResponseTemplateVO();
			 User user = this.userRepository.findById(userId)
						.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
			 
						Audit audit=  restTemplate.getForObject("http://localhost:9090/api/v1/audit/getaudit/" + user.getAuditId()
	        ,Audit.class);
						
			
			
			responseTemplateVO.setUser(user);
			responseTemplateVO.setAudit(audit);

			 return new ResponseEntity<ResponseTemplateVO>(responseTemplateVO, HttpStatus.OK);
		}


	// create user
	@PostMapping("/saveUser")
	@ApiOperation(value = "/saveUser", notes="create user",tags = {"user service"})
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "User created sucessfully"),
			@ApiResponse(code = 404, message = "Invalid data"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public  User createUser(@RequestBody User user) {
		
		return userService.createUser(user);
		
		
		
//		Audit audit=  restTemplate.getForObject("http://localhost:9090/api/v1/audit/saveAudit/"
//       ,Audit.class);
//	
//		responseTemplateVO.setUser(user);y67u8ji0kl,m;
//		responseTemplateVO.setAudit(audit);
//
//        return  responseTemplateVO;
//	Audit audit=responseTemplateVO.getAudit();
//	User user=responseTemplateVO.getUser();
//	user.setAuditId(user.getAuditId());
//	
//	
//	User userResponse = restTemplate.postForObject("http://localhost:9999/api/v1/user/saveUser/", user, User.class);
//	userRepository.save(user);
//	
//	return new TemplateResponse(userResponse.getAuditId(),user);
		
	}
	
	// update user
	@PutMapping("updateuser/{id}")
	@ApiOperation(value = "update user",notes = "update user", tags = {"user service"})
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "User Data updated"),
			@ApiResponse(code = 404, message = "Invalid data"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public User updateUser(@RequestBody User user, @PathVariable ("id") long userId) {
		
		 User existingUser = this.userRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
		 existingUser.setFirstName(user.getFirstName());
		 existingUser.setLastName(user.getLastName());
		 existingUser.setEmail(user.getEmail());
		 
		 return this.userRepository.save(existingUser);
	}
	
	// delete user by id
	@DeleteMapping("deleteuser/{id}")
	@ApiOperation(value = "delete user",notes = "delete user", tags = {"user service"})
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Delete user successfully"),
			@ApiResponse(code = 404, message = "Invalid data"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<User> deleteUser(@PathVariable ("id") long userId){
		
		 User existingUser = this.userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
		 this.userRepository.delete(existingUser);
		 return ResponseEntity.ok().build();
	}
	
	
	
}
