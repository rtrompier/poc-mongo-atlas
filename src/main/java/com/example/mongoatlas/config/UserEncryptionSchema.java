//package com.example.mongoatlas.config;
//
//import com.example.mongoatlas.entity.UserEntity;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.data.mongodb.core.CollectionOptions;
//import org.springframework.data.mongodb.core.MongoJsonSchemaCreator;
//import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
//import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
//import org.springframework.data.mongodb.core.schema.MongoJsonSchema;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class UserEncryptionSchema implements ApplicationRunner {
//
//	private final MongoMappingContext mappingContext;
//
//	private final ReactiveMongoTemplate mongoTemplate;
//
//	/**
//	 * Try to generate collection by using the default configuration of Spring Data Mongodb, but doesn't work.
//	 * Return an error with the generated schema format
//	 * Command failed with error 51088 (Location51088): 'Array elements must have type BinData, found array' on server cluster0-xxxxx.mongodb.net:27017. The full response is {"ok": 0.0, "errmsg": "Array elements must have type BinData, found array", "code": 51088, "codeName": "Location51088", "$clusterTime": {"clusterTime": {"$timestamp": {"t": 1650882111, "i": 32}}, "signature": {"hash": {"$binary": {"base64": "YQhBpcKDsOf3r+PVrhnVplUnuAs=", "subType": "00"}}, "keyId": 7047578532829986839}}, "operationTime": {"$timestamp": {"t": 1650882111, "i": 32}}}
//	 * @param args
//	 * @throws Exception
//	 */
//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		MongoJsonSchemaCreator schemaCreator = MongoJsonSchemaCreator.create(mappingContext);
//		MongoJsonSchema sampleSchema = schemaCreator
//				.filter(MongoJsonSchemaCreator.encryptedOnly())
//				.createSchemaFor(UserEntity.class);
//		log.info(sampleSchema.schemaDocument().toJson());
//		// Block only used for test
//		if (!mongoTemplate.collectionExists(UserEntity.class).block()) {
//			mongoTemplate.createCollection(UserEntity.class, CollectionOptions.empty().schema(sampleSchema)).block();
//		}
//	}
//
//}
