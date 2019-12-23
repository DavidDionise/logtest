package com.test.logging

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

@SpringBootApplication
class LoggingApplication

fun main(args: Array<String>) {
	runApplication<LoggingApplication>(*args)
}

@Configuration
class Router(val loggerTest: LoggerTest) {
	@Bean
	fun route(): RouterFunction<ServerResponse> = router {
		GET("/logs", loggerTest::log)
	}
}

@Component
class LoggerTest {
	fun log(request: ServerRequest): Mono<ServerResponse> {
		val logger = LoggerFactory.getLogger(this.javaClass)

		logger.info("Info message")
		logger.warn("Warn message")
		logger.debug("Debug message")
		logger.error("Error message")

		return ServerResponse
			.ok()
			.build()
	}
}