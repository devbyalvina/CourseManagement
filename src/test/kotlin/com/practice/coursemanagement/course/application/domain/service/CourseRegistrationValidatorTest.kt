package com.practice.coursemanagement.course.application.domain.service

import com.practice.coursemanagement.course.application.domain.Exception.CourseException
import com.practice.coursemanagement.course.application.domain.model.Course
import com.practice.coursemanagement.course.application.port.`in`.RegisterCourseCommand
import org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CourseRegistrationValidatorTest {
    @Test
    fun `수강 신청 요청시간이 수강 신청 시작 시간보다 이전이면 예외가 발생한다`() {
        // given
        val date = LocalDateTime.now()
        val course = Course( 1
                           , "테스트 강의"
                           , date.plusMinutes(5)
                           , 10
                           , date.minusDays(1)
                           , 5
                           , null)

        val registerCourseCommand = RegisterCourseCommand (1, 1000, date)

        // when, then
        assertThatThrownBy { CourseRegistrationValidator.validateCourse(course, registerCourseCommand) }
            .isInstanceOf(CourseException::class.java)
            .hasMessage("수강 신청 기간이 아닙니다.")
    }

    @Test
    fun `강의 신청자 수가 수강 신청 정원보다 크거나 같으면 예외가 발생한다`() {
        // given
        val date = LocalDateTime.now()
        val course = Course( 1
                           , "테스트 강의"
                           , date
                           , 10
                           , date
                           , 10
                           , null)
        val registerCourseCommand = RegisterCourseCommand (1, 1000, date)

        // when, then
        assertThatThrownBy { CourseRegistrationValidator.validateCourse(course, registerCourseCommand) }
            .isInstanceOf(CourseException::class.java)
            .hasMessage("수강신청 가능 정원을 초과하였습니다.")

    }
}