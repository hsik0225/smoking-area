package com.hsik.smoking.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfiguration {
    @Bean
    fun requestResponseLogFilter(): RequestResponseLogFilter = RequestResponseLogFilter()
}
