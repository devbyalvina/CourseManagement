package com.practice.coursemanagement.course.application.port.out

import com.practice.coursemanagement.course.application.domain.model.CourseRegistration

interface GetRegistrationStatusPort {
    fun getRegistrationStatus(courseId: Long, userId: Long) : CourseRegistration
}