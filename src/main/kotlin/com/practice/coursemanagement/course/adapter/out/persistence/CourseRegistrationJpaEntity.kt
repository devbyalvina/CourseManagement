package com.practice.coursemanagement.course.adapter.out.persistence

import com.practice.coursemanagement.course.application.domain.model.CourseRegistration
import jakarta.persistence.*
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table (name = "CourseRegistration")
class CourseRegistrationJpaEntity (
    @EmbeddedId
    val courseRegistrationJpaEntityPk: CourseRegistrationJpaEntityPk,

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    val updateDateTime: LocalDateTime
) {
    fun toDomain() = CourseRegistration (
        courseId = courseRegistrationJpaEntityPk.courseId,
        userId = courseRegistrationJpaEntityPk.userId,
        updateDateTime = updateDateTime
    )
}
