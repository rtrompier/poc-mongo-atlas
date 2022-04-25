package com.example.mongoatlas.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
	@JsonSerialize(using= ToStringSerializer.class)
	private ObjectId id;
	private String firstname;
	private String lastname;
	private String avs;
}
