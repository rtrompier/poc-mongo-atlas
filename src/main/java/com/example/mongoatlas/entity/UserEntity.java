package com.example.mongoatlas.entity;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Encrypted;

@Data
@Builder
@Document(collection = "users")
public class UserEntity {

	@Id
	private ObjectId id;

	private String firstname;

	private String lastname;

	@Encrypted(keyId = "xKVup8B1Q+CkHaVRx+qa+g==", algorithm = "AEAD_AES_256_CBC_HMAC_SHA_512-Deterministic")
	private String avs;

}
