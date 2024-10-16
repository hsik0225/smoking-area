package com.hsik.smoking.domain.area.client

import com.hsik.smoking.config.AppEnvironment
import com.hsik.smoking.util.RestClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenDataPortalConfiguration(
    private val appEnvironment: AppEnvironment,
) {
    @Bean
    fun openDataPortalClient(): OpenDataPortalClient {
        val env = appEnvironment.openDataPortal
        return if (env.useDummy) {
            DummyOpenDataPortalClient()
        } else {
            StableOpenDataPortalClient(env, RestClient.from(env))
        }
    }
}
