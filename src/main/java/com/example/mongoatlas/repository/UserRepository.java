package com.example.mongoatlas.repository;

import com.example.mongoatlas.entity.UserEntity;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, String> {

}
