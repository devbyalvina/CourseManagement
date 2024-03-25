package com.practice.coursemanagement.course.adapter.`in`.web

import com.practice.coursemanagement.course.application.port.`in`.CourseRegistrationResult
import com.practice.coursemanagement.course.application.port.`in`.RegisterStatus
import java.time.LocalDateTime

data class CourseRegistrationResponse(
    val courseId: Long,
    val courseName: String,
    val userId: Long,
    val updateDateTime: LocalDateTime?,
    val registerStatus: RegisterStatus,
) {
    companion object {
        fun of (courseRegistrationResult: CourseRegistrationResult): CourseRegistrationResponse {
            return CourseRegistrationResponse (
                courseId = courseRegistrationResult.courseId,
                courseName = courseRegistrationResult.courseName,
                userId = courseRegistrationResult.userId,
                updateDateTime = courseRegistrationResult.updateDateTime,
                registerStatus = courseRegistrationResult.registerStatus
            )
        }
    }
}

