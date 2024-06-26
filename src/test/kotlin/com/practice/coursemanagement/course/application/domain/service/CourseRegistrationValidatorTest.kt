package com.practice.coursemanagement.course.application.domain.service

import com.practice.coursemanagement.course.application.domain.Exception.CourseException
import com.practice.coursemanagement.course.application.domain.model.Course
import com.practice.coursemanagement.course.application.domain.model.CourseRegistration
import com.practice.coursemanagement.course.application.port.`in`.RegisterCourseCommand
import org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CourseRegistrationValidatorTest {
    @Test
    fun `수강 신청 요청시간이 수강 신청 시작 시간보다 이전이면 예외가 발생한다`() {
        // given
        val date = LocalDateTime.now()
        val course = Course(
            1,
            "테스트 강의",
            date.plusMinutes(5),
            10,
            date.minusDays(1),
            null
        )

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
        val registrationList = listOf(
            CourseRegistration(1, "테스트 강의", 1000, LocalDateTime.now()),
            CourseRegistration(1, "테스트 강의", 1001, LocalDateTime.now()),
            CourseRegistration(1, "테스트 강의", 1002, LocalDateTime.now()),
        )
        val course = Course(
            1,
            "테스트 강의",
            date,
            3,
            date.minusDays(1),
            registrationList
        )
        val registerCourseCommand = RegisterCourseCommand (1, 1003, date)

        // when, then
        assertThatThrownBy { CourseRegistrationValidator.validateCourse(course, registerCourseCommand) }
            .isInstanceOf(CourseException::class.java)
            .hasMessage("수강신청 가능 정원을 초과하였습니다.")

    }

    @Test
    fun `신청 목록에 있는 신청자가 중복 신청을 하면 예외가 발생한다`() {
        // given
        val date = LocalDateTime.now()
        val registrationList = listOf(
            CourseRegistration(1, "테스트 강의", 1000, LocalDateTime.now()),
            CourseRegistration(1, "테스트 강의", 1001, LocalDateTime.now())
        )
        val course = Course(
            1,
            "테스트 강의",
            date,
            10,
            date.minusDays(1),
            registrationList
        )
        val registerCourseCommand = RegisterCourseCommand (1, 1000, date)

        // when, then
        assertThatThrownBy { course.validateDuplication(registerCourseCommand.userId) }
            .isInstanceOf(CourseException::class.java)
            .hasMessage("이미 수강신청한 유저입니다.")
    }
}