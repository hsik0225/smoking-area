package com.hsik.smoking.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("app")
class AppEnvironment {
    val openDataPortal = OpenDataPortal()

    class OpenDataPortal : ConnectionOptions()

    open class ConnectionOptions(
        var host: String = "localhost",
        var connectionTimeout: Long = 1,
        var readTimeout: Long = 1,
        var useDummy: Boolean = true,
    )
}
