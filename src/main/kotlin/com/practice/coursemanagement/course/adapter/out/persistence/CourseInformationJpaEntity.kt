package com.practice.coursemanagement.course.adapter.out.persistence

import com.practice.coursemanagement.course.application.domain.model.Course
import jakarta.persistence.*
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table (name = "CourseInformation")
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

    @Column(nullable = false)
    @UpdateTimestamp
    val updateDateTime: LocalDateTime,
) {
    @OneToMany(fetch = FetchType.LAZY)
    val _courseRegistrationList: MutableList<CourseRegistrationJpaEntity> = mutableListOf()
    val courseRegistrationList: List<CourseRegistrationJpaEntity>
        get() = _courseRegistrationList

    fun toDomain() = Course (
        courseId = courseId,
        courseName = courseName,
        startSignUpDateTime = startSignUpDateTime,
        capacity = capacity,
        updateDateTime = updateDateTime,
        courseRegistrationList = courseRegistrationList.map{ it.toDomain() }
    )
}
