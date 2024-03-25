package com.practice.coursemanagement.course.adapter.out.persistence

import com.practice.coursemanagement.course.application.domain.model.CourseInformation
import jakarta.persistence.*
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
class CourseInformationJpaEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val courseId: Long,

    @Column(nullable = false)
    val courseName: String,

    @Column(nullable = false)
    val startSignUpDateTime: LocalDateTime,

    @Column(nullable = false)
    val capacity: Long,

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    val updateDateTime: LocalDateTime
) {
    fun toDomain() = CourseInformation (
        courseId = courseId,
        courseName = courseName,
        startSignUpDateTime = startSignUpDateTime,
        capacity = capacity,
        updateDateTime = updateDateTime
    )
}