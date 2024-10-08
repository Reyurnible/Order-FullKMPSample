package io.reyurnible.order.routes.api.users

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.reyurnible.order.domain.repository.UserRepository
import io.reyurnible.api.endpoints.users.CreateUserParams
import io.reyurnible.api.endpoints.users.UpdateUserParams
import io.reyurnible.order.infra.database.database
import io.reyurnible.order.infra.database.repository.DatabaseUserRepository

fun Route.usersApi(
    userRepository: UserRepository = DatabaseUserRepository(database)
) {
    val userEndPoint = ServerUserEndPoints(userRepository)
    // Create user
    post("/users") {
        val result = userEndPoint.post(params = call.receive<CreateUserParams>())
        call.respond(HttpStatusCode.Created, result)
    }

    get("/users") {
        val result = userEndPoint.getList()
        call.respond(result)
    }

    // Read user
    get("/users/{id}") {
        val id = call.parameters["id"] ?: throw IllegalArgumentException("Invalid ID")
        val result = userEndPoint.get(id)
        if (result != null) {
            call.respond(HttpStatusCode.OK, result)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }

    // Update user
    put("/users/{id}") {
        val id = call.parameters["id"] ?: throw IllegalArgumentException("Invalid ID")
        userEndPoint.put(id, call.receive<UpdateUserParams>())
        call.respond(HttpStatusCode.OK)
    }

    // Delete user
    delete("/users/{id}") {
        val id = call.parameters["id"] ?: throw IllegalArgumentException("Invalid ID")
        userEndPoint.delete(id)
        call.respond(HttpStatusCode.OK)
    }
}