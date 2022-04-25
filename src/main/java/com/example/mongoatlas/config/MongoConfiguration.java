package com.example.mongoatlas.config;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.example.mongoatlas.entity.UserEntity;
import com.mongodb.AutoEncryptionSettings;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mongodb.core.MongoJsonSchemaCreator;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;

@Slf4j
@Configuration
public class MongoConfiguration {

	@Bean
	MongoClientSettingsBuilderCustomizer customizer(MappingContext mappingContext) {
		return (builder) -> {
			String keyVaultNamespace = "admin.datakeys";

			final byte[] localMasterKey = new byte[96];
			new SecureRandom().nextBytes(localMasterKey);
			log.info("---- local master key : {}", localMasterKey);

			Map<String, Map<String, Object>> kmsProviders = new HashMap<>() {
				{
					put("local", new HashMap<>() {
						{
							put("key", localMasterKey);
						}
					});
				}
			};

			MongoJsonSchemaCreator schemaCreator = MongoJsonSchemaCreator.create(mappingContext);
			MongoJsonSchema userSchema = schemaCreator
					.filter(MongoJsonSchemaCreator.encryptedOnly())
					.createSchemaFor(UserEntity.class);

			AutoEncryptionSettings autoEncryptionSettings = AutoEncryptionSettings.builder()
					.keyVaultNamespace(keyVaultNamespace)
					.kmsProviders(kmsProviders)
//					.extraOptions(extraOpts)
					.schemaMap(Collections.singletonMap("db.users", userSchema.schemaDocument().toBsonDocument()))
					.build();

			builder.autoEncryptionSettings(autoEncryptionSettings);
		};
	}

}
