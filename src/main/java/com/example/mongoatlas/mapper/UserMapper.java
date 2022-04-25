package com.example.mongoatlas.mapper;

import com.example.mongoatlas.entity.UserEntity;
import com.example.mongoatlas.model.UserCreateModel;
import com.example.mongoatlas.model.UserModel;
import com.example.mongoatlas.model.UserUpdateModel;

public class UserMapper {

	public static UserEntity toUserEntity(UserCreateModel model) {
		return UserEntity.builder()
				.firstname(model.getFirstname())
				.lastname(model.getLastname())
				.avs(model.getAvs())
				.build();
	}

	public static UserEntity toUserEntity(UserUpdateModel model) {
		return UserEntity.builder()
				.id(model.getId())
				.firstname(model.getFirstname())
				.lastname(model.getLastname())
				.avs(model.getAvs())
				.build();
	}

	public static UserModel toUserModel(UserEntity entity) {
		return UserModel.builder()
				.id(entity.getId())
				.firstname(entity.getFirstname())
				.lastname(entity.getLastname())
				.avs(entity.getAvs())
				.build();
	}

}
