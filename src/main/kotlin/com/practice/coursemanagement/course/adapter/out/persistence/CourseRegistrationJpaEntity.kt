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

    @Column(nullable = false)
    @UpdateTimestamp
    val updateDateTime: LocalDateTime,

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "courseInformation", nullable = false)
//    val courseInformation: CourseInformation
) {
    fun toDomain() = CourseRegistration (
        courseId = courseRegistrationJpaEntityPk.courseId,
        courseName = null,
        userId = courseRegistrationJpaEntityPk.userId,
        updateDateTime = updateDateTime
    )
}
