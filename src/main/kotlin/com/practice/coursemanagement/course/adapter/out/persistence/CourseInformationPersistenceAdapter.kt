package com.practice.coursemanagement.course.adapter.out.persistence

import com.practice.coursemanagement.course.application.domain.Exception.CourseErrorCode
import com.practice.coursemanagement.course.application.domain.Exception.CourseException
import com.practice.coursemanagement.course.application.domain.model.Course
import com.practice.coursemanagement.course.application.port.out.GetCoursePort
import jakarta.persistence.LockModeType
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CourseInformationPersistenceAdapter (
    val courseInformationRepository: CourseInformationRepository,
) : GetCoursePort {
    @Transactional
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    override fun getCourseForRegistration(courseId: Long): Course {
        //return courseInformationRepository.findCourseByIdForUpdate(courseId) ?: throw CourseException(CourseErrorCode.COURSE_NOT_FOUND)
        val courseInformationEntity = courseInformationRepository.findByIdOrNull(courseId) ?: throw CourseException(CourseErrorCode.COURSE_NOT_FOUND)
        return courseInformationEntity.toDomain()
    }
}