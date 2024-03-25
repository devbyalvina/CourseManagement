package com.practice.coursemanagement.course.application.port.`in`

import java.time.LocalDateTime

data class CourseRegistrationResult (
    val courseId: Long,
    val courseName: String,
    val userId: Long,
    val updateDateTime: LocalDateTime,
    val registerStatus: RegisterStatus
)

/**
 * 수강신청 상태
 * - SUCCESS: 성공
 * - FAIL: 실패
 */
enum class RegisterStatus {
    SUCCESS, FAIL
}