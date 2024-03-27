package com.practice.coursemanagement.course.application.port.`in`

import com.practice.coursemanagement.course.application.domain.model.CourseRegistration

interface CourseRegistrationUseCase {
    fun registerCourse (registerCourseCommand: RegisterCourseCommand): CourseRegistration

    fun getRegistrationStatus (getRegistrationStatusCommand: GetRegistrationStatusCommand): CourseRegistration
}