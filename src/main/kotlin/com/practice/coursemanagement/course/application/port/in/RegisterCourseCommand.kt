package com.practice.coursemanagement.course.application.port.`in`

import java.time.LocalDateTime

data class RegisterCourseCommand (
    val courseId: Long,
    val userId: Long,
    val updateDateTime: LocalDateTime
)