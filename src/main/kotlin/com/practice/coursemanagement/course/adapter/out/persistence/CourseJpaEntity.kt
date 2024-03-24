package com.practice.coursemanagement.course.adapter.out.persistence

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class CourseJpaEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val courseId: Long,

    @Column(nullable = false)
    val courseName: String,

    @Column(nullable = false)
    val startDateTime: LocalDateTime,

    @Column(nullable = false)
    val capacity: Long,

    @Column(nullable = false)
    val updateDateTime: LocalDateTime
)