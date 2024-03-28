package com.practice.coursemanagement.course.application.domain.model

import com.practice.coursemanagement.course.application.domain.Exception.CourseErrorCode
import com.practice.coursemanagement.course.application.domain.Exception.CourseException
import java.time.LocalDateTime

data class Course (
    /* 강의 기본 property */
    // 강의 ID
    val courseId: Long,
    // 강의명
    val courseName: String,
    // 수강신청 시작 시간
    val startSignUpDateTime: LocalDateTime,
    // 정원
    val capacity: Long,
    // 수정일자
    val updateDateTime: LocalDateTime,

    /* 강의 신청자 property */
    // 강의 신청자 목록
    val courseRegistrationList : List<CourseRegistration>?
){
    // 날짜 validation 체크
    fun validateStartSignUpDateTime(requestDateTime: LocalDateTime) {
        if (requestDateTime.isBefore(startSignUpDateTime))
            throw CourseException(CourseErrorCode.INVALID_REGISTER_DATE_TIME)
    }

    // 수강신청 가능 인원수 validation 체크
    fun validateCapacity() {
        if (capacity <= (courseRegistrationList?.size ?: 0)) {
            throw CourseException(CourseErrorCode.EXCESSIVE_COURSE_CAPACITY)
        }
    }

    // 중복 신청 validation 체크
    fun validateDuplication(userId: Long) {
        //courseRegistrationList?.any{it.userId == userId}.takeIf { throw CourseException(CourseErrorCode.DUPLICATED_REGISTRATION) }
        if (courseRegistrationList?.any{it.userId == userId} == true) {
            throw CourseException(CourseErrorCode.DUPLICATED_REGISTRATION)
        }
    }
}