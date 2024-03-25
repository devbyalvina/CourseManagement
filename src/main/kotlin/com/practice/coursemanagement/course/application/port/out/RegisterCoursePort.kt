package com.practice.coursemanagement.course.application.port.out

import com.practice.coursemanagement.course.application.domain.model.CourseRegistration

interface RegisterCoursePort {
    fun registerCourse(courseRegistration : CourseRegistration) : CourseRegistration?

}