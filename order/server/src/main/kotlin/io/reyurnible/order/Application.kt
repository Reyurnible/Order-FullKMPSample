package io.reyurnible.order

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.reyurnible.order.plugins.*
import io.reyurnible.order.routes.configureRouting

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    // Create EndPoints
    configureRouting()
}
