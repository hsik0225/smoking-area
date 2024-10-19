package com.hsik.smoking.domain.client.kakao

import com.hsik.smoking.config.AppEnvironment
import com.hsik.smoking.util.RestClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KakaoConfiguration(
    private val appEnvironment: AppEnvironment,
) {
    @Bean
    fun kakaoClient(): KakaoClient {
        val env = appEnvironment.kakao
        return if (env.useDummy) {
            DummyKakaoClient()
        } else {
            StableKakaoClient(env, RestClient.from(env))
        }
    }
}
