package com.example.mongoatlas.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.mongoatlas.entity.UserEntity;
import com.example.mongoatlas.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	private final ReactiveMongoOperations mongoOperations;

	public Flux<UserEntity> findUsers(@NonNull String search) {
		List<AggregationOperation> operationList = new ArrayList<>();

		String searchQuery = """
				{
					$search: {
					  index: 'users-index',
					  text: {
						query: '%s',
						path: {
						  'wildcard': '*'
						},
				        fuzzy: {
				          'maxEdits': 2
				        }
					  }
					}
				}
				""".formatted(search);
		AggregationOperation usersSearchOperation = context -> context.getMappedObject(Document.parse(searchQuery));
		operationList.add(usersSearchOperation);

		Aggregation aggregation = Aggregation.newAggregation(operationList);
		return this.mongoOperations.aggregate(aggregation, "users", UserEntity.class);
	}

	public Mono<UserEntity> saveUser(UserEntity user) {
		return this.userRepository.save(user);
	}

	public Mono<UserEntity> findUser(String id) {
		return this.userRepository.findById(id);
	}

	public Mono<Void> deleteUser(String id) {
		return this.userRepository.deleteById(id);
	}
}
