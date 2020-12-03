package com.grade

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
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
        get(path = "/api/grade/grade") {
            val grade = Grade(score = 88, grade = "A")
            val user = GradeResponse(success = true, message = "Cal grade success", grade = grade)
            call.respond(user)
        }

        get(path = "/api/grade/grade1") {
            val score = call.parameters["score"]?.toInt()
            val gradeResponse = GradeResponse()

            if (score == null) {
                gradeResponse.success = false
                gradeResponse.message = "Please enter score"
            } else {
                val grade = Grade(score = score)

//                if (score>=80){
//                    grade.grade = "A"
//                }else if (score>=75){
//                    grade.grade = "B+"
//                }else if (score>=70){
//                    grade.grade = "B"
//                }else if (score>=65){
//                    grade.grade = "C+"
//                }else if (score>=60){
//                    grade.grade = "C"
//                }else if (score>=55){
//                    grade.grade = "D+"
//                }else if (score>=50){
//                    grade.grade = "D"
//                }else{
//                    grade.grade = "F"
//                }

                when {
                    score >= 80 -> grade.grade = "A"
                    score >= 75 -> grade.grade = "B+"
                    score >= 70 -> grade.grade = "B"
                    score >= 65 -> grade.grade = "C+"
                    score >= 60 -> grade.grade = "C"
                    score >= 55 -> grade.grade = "D+"
                    score >= 50 -> grade.grade = "D"
                    else -> grade.grade = "F"
                }

                gradeResponse.success = true
                gradeResponse.message = "Cal grade success"
                gradeResponse.grade = grade
            }

            call.respond(gradeResponse)
        }

    }

}

data class GradeResponse(
    var success: Boolean? = null,
    var message: String? = null,
    var grade: Grade? = null

)

data class Grade(
    val score: Int? = null,
    var grade: String? = null
)

