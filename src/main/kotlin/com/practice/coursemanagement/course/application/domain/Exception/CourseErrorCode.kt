package com.practice.coursemanagement.course.application.domain.Exception

enum class CourseErrorCode (
    val code: Int,
    val message: String,
) {
    INVALID_REGISTER_DATE_TIME (100, "수강 신청 기간이 아닙니다."),
    COURSE_NOT_FOUND (101, "존재하지 않는 강의입니다."),
    REGISTRATION_NOT_FOUND (102, "수강신청 내역이 없습니다."),
    EXCESSIVE_COURSE_CAPACITY (103, "수강신청 가능 정원을 초과하였습니다."),
    DUPLICATED_REGISTRATION (104, "이미 수강신청한 유저입니다.")
}