package com.practice.coursemanagement.course.adapter.out.persistence

import com.practice.coursemanagement.course.application.domain.model.CourseInformation
import com.practice.coursemanagement.course.application.port.out.GetCourseInformationPort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CourseInformationPersistenceAdapter (
    val courseInformationRepository: CourseInformationRepository,
) : GetCourseInformationPort{
    override fun getCourseInformation(courseId: Long): CourseInformation? {
        return courseInformationRepository.findByIdOrNull(courseId)?.toDomain()
    }
}