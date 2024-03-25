package com.practice.coursemanagement.course.application.port.`in`

import java.time.LocalDateTime

data class CourseInformationResult(
    val courseId: Long,

    val courseName: String,

    val startSignUpDateTime: LocalDateTime,

    val capacity: Long,

    val updateDateTime: LocalDateTime
)
