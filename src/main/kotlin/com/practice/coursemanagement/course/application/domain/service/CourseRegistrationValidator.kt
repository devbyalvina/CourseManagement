package com.practice.coursemanagement.course.application.domain.service

import com.practice.coursemanagement.course.application.domain.model.Course
import com.practice.coursemanagement.course.application.port.`in`.RegisterCourseCommand

object CourseRegistrationValidator {
    fun validateCourse(course: Course, registerCourseCommand: RegisterCourseCommand) {
        // 날짜 validation 체크
        course.validateStartSignUpDateTime(registerCourseCommand.updateDateTime)
        // 수강신청 가능 인원수 validation 체크
        course.validateCapacity()
        // 중복 신청 여부 validation 체크
        course.validateDuplication(registerCourseCommand.userId)
    }
}