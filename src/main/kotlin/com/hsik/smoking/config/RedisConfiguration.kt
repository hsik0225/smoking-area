package com.hsik.smoking.config

import io.lettuce.core.SocketOptions
import io.lettuce.core.cluster.ClusterClientOptions
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisClusterConfiguration
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@Configuration
@EnableConfigurationProperties(RedisProperties::class)
@EnableRedisRepositories
class RedisConfiguration(
    private val properties: RedisProperties,
) {
    @Bean
    fun stringRedisTemplate(): StringRedisTemplate {
        val clientConfiguration =
            LettuceClientConfiguration
                .builder()
                .commandTimeout(properties.timeout)
                .clientOptions(
                    ClusterClientOptions
                        .builder()
                        .autoReconnect(true)
                        .socketOptions(
                            SocketOptions
                                .builder()
                                .connectTimeout(properties.connectTimeout)
                                .keepAlive(true)
                                .build(),
                        ).topologyRefreshOptions(
                            ClusterTopologyRefreshOptions
                                .builder()
                                .enablePeriodicRefresh(properties.lettuce.cluster.refresh.period)
                                .build(),
                        ).build(),
                ).build()

        val clusterConfiguration =
            RedisClusterConfiguration(properties.cluster.nodes)
                .apply { password = RedisPassword.of(properties.password) }

        val factory = LettuceConnectionFactory(clusterConfiguration, clientConfiguration).apply { afterPropertiesSet() }
        return StringRedisTemplate(factory)
    }
}
