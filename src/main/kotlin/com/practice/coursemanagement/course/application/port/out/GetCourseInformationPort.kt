package com.practice.coursemanagement.course.application.port.out

import com.practice.coursemanagement.course.application.domain.model.CourseInformation

interface GetCourseInformationPort {
    fun getCourseInformation(courseId: Long) : CourseInformation?
}