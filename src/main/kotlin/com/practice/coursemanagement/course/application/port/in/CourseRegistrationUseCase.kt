package com.practice.coursemanagement.course.application.port.`in`

interface CourseRegistrationUseCase {
    fun registerCourse (registerCourseCommand: RegisterCourseCommand): CourseRegistrationResult

    fun getRegistrationStatus (getRegistrationStatusCommand: GetRegistrationStatusCommand): CourseRegistrationResult
}