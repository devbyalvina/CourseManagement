package com.practice.coursemanagement.course.adapter.out.persistence

import com.practice.coursemanagement.course.application.domain.model.CourseRegistration
import org.springframework.stereotype.Component

@Component
class CourseRegistrationPersistenceMapper {
    fun mapToJpaEntity(courseRegistration: CourseRegistration): CourseRegistrationJpaEntity {
        return CourseRegistrationJpaEntity(
            CourseRegistrationJpaEntityPk(
                courseRegistration.courseId,
                courseRegistration.userId
            ),
            courseRegistration.updateDateTime
        )
    }
}