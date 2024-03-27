package com.practice.coursemanagement.course.adapter.out.persistence

import com.practice.coursemanagement.course.application.domain.Exception.CourseErrorCode
import com.practice.coursemanagement.course.application.domain.Exception.CourseException
import com.practice.coursemanagement.course.application.domain.model.Course
import com.practice.coursemanagement.course.application.port.out.GetCoursePort
import org.springframework.stereotype.Component

@Component
class CourseInformationPersistenceAdapter (
    val courseInformationRepository: CourseInformationRepository,
) : GetCoursePort {
    override fun getCourseForRegistration(courseId: Long): Course {
        return courseInformationRepository.findCourseByIdForUpdate(courseId) ?: throw CourseException(CourseErrorCode.COURSE_NOT_FOUND)
    }
}