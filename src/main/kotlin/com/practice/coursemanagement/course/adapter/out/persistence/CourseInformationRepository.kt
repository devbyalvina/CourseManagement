package com.practice.coursemanagement.course.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface CourseInformationRepository : JpaRepository<CourseInformationJpaEntity, Long> {
}