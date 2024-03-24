package com.practice.coursemanagement.course.domain

import java.time.LocalDateTime

data class Course(
    val courseId: Long,
    val courseName: String,
    val startDateTime: LocalDateTime,
    val capacity: Long,
    val updateDateTime: LocalDateTime
)
