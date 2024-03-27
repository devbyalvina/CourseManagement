package com.practice.coursemanagement.course.adapter.out.persistence

import com.practice.coursemanagement.course.application.domain.model.Course
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface CourseInformationRepository : JpaRepository<CourseInformationJpaEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query( value = "SELECT ci.course_id                    AS courseId" +
                    "     , ci.course_name                  AS courseName" +
                    "     , ci.start_sign_up_date_time      AS startSignUpDateTime" +
                    "     , ci.capacity                     AS capacity" +
                    "     , ci.update_date_time             AS updateDateTime" +
                    "     , COUNT(cr.course_id, cr.user_id) AS registeredCount" +
                    "  FROM course_information ci" +
                    "     , course_registration cr" +
                    " WHERE ci.course_id = :courseId" +
                    "   AND ci.course_id = cr.course_id" +
                    " GROUP BY ci.course_id, ci.course_name, ci.start_sign_up_date_time, ci.capacity, ci.update_date_time"
          , nativeQuery = true)
    fun findCourseByIdForUpdate(courseId: Long): Course?
}