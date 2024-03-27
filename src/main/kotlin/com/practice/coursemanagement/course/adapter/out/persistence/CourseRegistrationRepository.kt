package com.practice.coursemanagement.course.adapter.out.persistence

import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface CourseRegistrationRepository : JpaRepository<CourseRegistrationJpaEntity, CourseRegistrationJpaEntityPk> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select count(1) from CourseRegistrationJpaEntity cr where cr.courseRegistrationJpaEntityPk.courseId = :courseId")
    fun countByCourseIdForUpdate(courseId: Long): Long
}