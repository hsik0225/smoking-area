package com.hsik.smoking.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("app")
class AppEnvironment {
    val openDataPortal = OpenDataPortal()

    class OpenDataPortal : ConnectionOptions() {
        lateinit var apiKey: String
    }

    open class ConnectionOptions(
        var host: String = "localhost",
        var connectionTimeout: Long = 5,
        var readTimeout: Long = 5,
        var useDummy: Boolean = true,
    )
}
