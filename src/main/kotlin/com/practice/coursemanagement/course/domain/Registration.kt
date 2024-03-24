package com.practice.coursemanagement.course.domain

import java.time.LocalDateTime

data class Registration (
    val courseId: Long,
    val userId: Long,
    val updateDateTime: LocalDateTime
)