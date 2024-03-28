package com.practice.coursemanagement.course.application.domain.model

import com.practice.coursemanagement.course.application.domain.Exception.CourseException
import org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CourseTest {
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

        // when, then
        assertThatThrownBy { course.validateStartSignUpDateTime(date) }
            .isInstanceOf(CourseException::class.java)
            .hasMessage("수강 신청 기간이 아닙니다.")
    }

    @Test
    fun `강의 신청자 수가 수강 신청 정원보다 크거나 같으면 예외가 발생한다`() {
        // given
        val course = Course( 1
                           , "테스트 강의"
                           , LocalDateTime.now()
                           , 10
                           , LocalDateTime.now()
                           , 10
                           , null)

        // when, then
        assertThatThrownBy { course.validateCapacity() }
            .isInstanceOf(CourseException::class.java)
            .hasMessage("수강신청 가능 정원을 초과하였습니다.")
    }
}