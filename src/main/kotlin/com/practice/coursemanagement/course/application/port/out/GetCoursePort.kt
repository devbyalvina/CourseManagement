package com.practice.coursemanagement.course.application.port.out

import com.practice.coursemanagement.course.application.domain.model.Course

interface GetCoursePort {
    fun getCourseForRegistration(courseId: Long) : Course
}