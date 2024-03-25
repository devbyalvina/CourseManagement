package com.practice.coursemanagement.course.application.domain.model

import java.time.LocalDateTime

data class CourseRegistration(
    val courseId: Long,
    val userId: Long,
    val updateDateTime: LocalDateTime
) {

}