package com.hsik.smoking.config

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@TestConfiguration
@Testcontainers
class MongoTestContainerConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    fun mongoDBContainer(): MongoDBContainer {
        return MongoDBContainer(DockerImageName.parse("mongo:latest"))
    }

    @Bean
    fun mongoDatabaseFactory(mongoDBContainer: MongoDBContainer): MongoDatabaseFactory {
        return SimpleMongoClientDatabaseFactory(mongoDBContainer.replicaSetUrl)
    }
}
