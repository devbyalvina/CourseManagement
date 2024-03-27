package com.practice.coursemanagement.course.adapter.out.persistence

import com.practice.coursemanagement.course.application.domain.Exception.CourseErrorCode
import com.practice.coursemanagement.course.application.domain.Exception.CourseException
import com.practice.coursemanagement.course.application.domain.model.CourseRegistration
import com.practice.coursemanagement.course.application.port.out.GetRegistrationCountPort
import com.practice.coursemanagement.course.application.port.out.GetRegistrationStatusPort
import com.practice.coursemanagement.course.application.port.out.RegisterCoursePort
import org.springframework.stereotype.Component

@Component
class CourseRegistrationPersistenceAdapter(
    val courseRegistrationRepository: CourseRegistrationRepository,
    val courseRegistrationPersistenceMapper: CourseRegistrationPersistenceMapper,
) : RegisterCoursePort, GetRegistrationStatusPort, GetRegistrationCountPort {

    override fun registerCourse(courseRegistration: CourseRegistration): CourseRegistration {
        val courseRegistrationEntity = courseRegistrationRepository.save(courseRegistrationPersistenceMapper.mapToJpaEntity(courseRegistration))
        return courseRegistrationEntity.toDomain()
    }

    override fun getRegistrationStatus(courseId: Long, userId: Long): CourseRegistration {
        return courseRegistrationRepository.findById(CourseRegistrationJpaEntityPk(courseId = courseId, userId = userId))
            .orElseThrow { CourseException(CourseErrorCode.REGISTRATION_NOT_FOUND) }
            .toDomain()
    }

    override fun getRegistrationCount(courseId: Long): Long {
        return courseRegistrationRepository.countByCourseIdForUpdate(courseId)
    }
}