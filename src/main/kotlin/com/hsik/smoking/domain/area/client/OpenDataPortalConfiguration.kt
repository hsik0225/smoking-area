package com.hsik.smoking.domain.area.client

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenDataPortalConfiguration {
    @Bean
    fun openDataPortalClient(): OpenDataPortalClient = DummyOpenDataPortalClient()
}
