package com.practice.coursemanagement.course.adapter.out.persistence

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
    val updateDateTime: LocalDateTime
)
