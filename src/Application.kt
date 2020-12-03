package com.grade

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {

    install(DefaultHeaders)
    install(CallLogging)

    install(ContentNegotiation) {
        gson {
        }
    }

    install(Routing) {
        get(path = "/api/grade/user"){
            val grade = Grade(score = 88,grade = "A")
            val user = User(success = true,message = "Cal grade success",grade = grade)
            call.respond(user)
        }
        get(path = "/api/grade/userInfo"){
            val score = call.parameters["score"]?.toInt()
            val grade1 = call.parameters["grade"]

            val grade = Grade(score = score ,grade = grade1)
            val user = User(success = true,message = "Cal grade success",grade = grade)
            call.respond(user)
        }



    }

}
data class User(
    val success:Boolean? = null,
    val message:String? = null,
    val grade:Grade? = null

)
data class Grade(
    val score:Int? = null,
    val grade:String? = null
)

