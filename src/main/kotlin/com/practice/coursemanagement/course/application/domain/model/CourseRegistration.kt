package com.practice.coursemanagement.course.application.domain.model

import java.time.LocalDateTime

data class CourseRegistration(
    // 강의 ID
    val courseId: Long,
    // 강의명
    val courseName: String?,
    // 사용자 ID
    val userId: Long,
    // 수정일자
    val updateDateTime: LocalDateTime
) {

}