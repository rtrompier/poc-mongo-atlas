package com.example.mongoatlas.controller;

import com.example.mongoatlas.mapper.UserMapper;
import com.example.mongoatlas.model.UserCreateModel;
import com.example.mongoatlas.model.UserModel;
import com.example.mongoatlas.model.UserUpdateModel;
import com.example.mongoatlas.service.UserService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@GetMapping
	public Flux<UserModel> findUsers (@RequestParam(value = "q") String query) {
		return this.userService.findUsers(query)
				.map(UserMapper::toUserModel);
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<UserModel>> findUser (@PathVariable String id) {
		return this.userService.findUser(id)
				.map(UserMapper::toUserModel)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<UserModel> createUser(@RequestBody UserCreateModel user) {
		return this.userService.saveUser(UserMapper.toUserEntity(user))
				.map(UserMapper::toUserModel);
	}

	@PutMapping("/{id}")
	public Mono<UserModel> updateUser(@RequestBody UserUpdateModel user) {
		return this.userService.saveUser(UserMapper.toUserEntity(user))
				.map(UserMapper::toUserModel);
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteUser(@PathVariable String id) {
		return this.userService.deleteUser(id);
	}

}
