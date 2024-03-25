package com.practice.coursemanagement.course.application.port.`in`

data class GetRegistrationStatusCommand(
    val courseId: Long,
    val userId: Long
)
