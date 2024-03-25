package com.practice.coursemanagement.course.adapter.out.persistence

import com.practice.coursemanagement.course.application.domain.model.CourseRegistration
import com.practice.coursemanagement.course.application.port.out.GetRegistrationStatusPort
import com.practice.coursemanagement.course.application.port.out.RegisterCoursePort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CourseRegistrationPersistenceAdapter(
    val courseRegistrationRepository: CourseRegistrationRepository,
    val courseRegistrationPersistenceMapper: CourseRegistrationPersistenceMapper,
) : RegisterCoursePort, GetRegistrationStatusPort {

    override fun registerCourse(courseRegistration: CourseRegistration): CourseRegistration {
        val courseRegistrationEntity = courseRegistrationRepository.save(courseRegistrationPersistenceMapper.mapToJpaEntity(courseRegistration))
        return courseRegistrationEntity.toDomain()
    }

    override fun getRegistrationStatus(courseId: Long, userId: Long): CourseRegistration? {
        return courseRegistrationRepository.findByIdOrNull(CourseRegistrationJpaEntityPk(courseId = courseId, userId = userId))?.toDomain()
    }
}