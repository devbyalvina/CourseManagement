package com.practice.coursemanagement.course.application.port.`in`

interface CourseInformationUseCase {
    fun getCourseInformation (getCourseInformationCommand: GetCourseInformationCommand): CourseInformationResult
}