package com.practice.coursemanagement.course.application.domain.service

import com.practice.coursemanagement.course.application.domain.Exception.CourseException
import com.practice.coursemanagement.course.application.domain.model.Course
import com.practice.coursemanagement.course.application.domain.model.CourseRegistration
import com.practice.coursemanagement.course.application.port.`in`.GetRegistrationStatusCommand
import com.practice.coursemanagement.course.application.port.`in`.RegisterCourseCommand
import com.practice.coursemanagement.course.application.port.out.GetCoursePort
import com.practice.coursemanagement.course.application.port.out.GetRegistrationStatusPort
import com.practice.coursemanagement.course.application.port.out.RegisterCoursePort
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import io.mockk.mockk
import org.assertj.core.api.AssertionsForClassTypes
import org.junit.jupiter.api.Assertions.assertEquals


class CourseRegistrationServiceTest {
    @Test
    fun `수강신청을 성공한다`() {
        // arrange
        val registrationList = listOf(
            CourseRegistration(1, "테스트 강의", 1000, LocalDateTime.now()),
            CourseRegistration(1, "테스트 강의", 1001, LocalDateTime.now())
        )

        val getCoursePortStub = object : GetCoursePort {
            override fun getCourseForRegistration(courseId: Long): Course {
                val date = LocalDateTime.now()

                return Course(
                    1,
                    "테스트 강의",
                    date.minusMinutes(5),
                    10,
                    date.minusDays(2),
                    registrationList
                )
            }
        }

        val registerCoursePortStub = object : RegisterCoursePort {
            var registerCourseMap = registrationList.associateBy { list -> Pair<Long,Long>(list.courseId, list.userId) }.toMutableMap()

            override fun registerCourse(courseRegistration: CourseRegistration): CourseRegistration {
                registerCourseMap[Pair<Long, Long>(courseRegistration.courseId, courseRegistration.userId)] = courseRegistration;
                return courseRegistration;
            }
        }

        val sut = CourseRegistrationService(
            getCoursePort = getCoursePortStub,
            registerCoursePort = registerCoursePortStub,
            getRegistrationStatusPort = mockk()
        )

        val registerCourseCommand = RegisterCourseCommand (courseId = 1, userId = 1002, updateDateTime = LocalDateTime.now())

        // act
        val actual = sut.registerCourse(registerCourseCommand)

        // assert
        assertEquals(actual.courseId, 1)
        assertEquals(actual.userId, 1002)
    }

    @Test
    fun `수강 신청 요청시간이 수강 신청 시작 시간보다 이전이면 예외가 발생한다`() {
        // arrange
        val getCoursePortStub = object : GetCoursePort {
            override fun getCourseForRegistration(courseId: Long): Course {
                val date = LocalDateTime.now()

                return Course(
                    1,
                    "테스트 강의",
                    date.plusDays(5),
                    10,
                    date.minusDays(2),
                    null
                )
            }
        }

        val sut = CourseRegistrationService(
            getCoursePort = getCoursePortStub,
            registerCoursePort = mockk(),
            getRegistrationStatusPort = mockk()
        )

        val registerCourseCommand = RegisterCourseCommand (courseId = 1, userId = 1000, updateDateTime = LocalDateTime.now())

        // act, assert
        AssertionsForClassTypes.assertThatThrownBy {sut.registerCourse(registerCourseCommand) }
            .isInstanceOf(CourseException::class.java)
            .hasMessage("수강 신청 기간이 아닙니다.")
    }

    @Test
    fun `강의 신청자 수가 수강 신청 정원보다 크거나 같으면 예외가 발생한다`() {
        // arrange
        val registrationList = listOf(
            CourseRegistration(1, "테스트 강의", 1000, LocalDateTime.now()),
            CourseRegistration(1, "테스트 강의", 1001, LocalDateTime.now()),
            CourseRegistration(1, "테스트 강의", 1002, LocalDateTime.now()),
            CourseRegistration(1, "테스트 강의", 1003, LocalDateTime.now()),
            CourseRegistration(1, "테스트 강의", 1004, LocalDateTime.now()),
        )

        val getCoursePortStub = object : GetCoursePort {
            override fun getCourseForRegistration(courseId: Long): Course {
                val date = LocalDateTime.now()

                return Course(
                    1,
                    "테스트 강의",
                    date.minusMinutes(5),
                    5,
                    date.minusDays(2),
                    registrationList
                )
            }
        }

        val sut = CourseRegistrationService(
            getCoursePort = getCoursePortStub,
            registerCoursePort = mockk(),
            getRegistrationStatusPort = mockk()
        )

        val registerCourseCommand = RegisterCourseCommand (courseId = 1, userId = 1005, updateDateTime = LocalDateTime.now())

        // act, assert
        AssertionsForClassTypes.assertThatThrownBy {sut.registerCourse(registerCourseCommand) }
            .isInstanceOf(CourseException::class.java)
            .hasMessage("수강신청 가능 정원을 초과하였습니다.")
    }

    @Test
    fun `신청 목록에 있는 신청자가 중복 신청을 하면 예외가 발생한다`() {
        // arrange
        val registrationList = listOf(
            CourseRegistration(1, "테스트 강의", 1000, LocalDateTime.now()),
            CourseRegistration(1, "테스트 강의", 1001, LocalDateTime.now())
        )

        val getCoursePortStub = object : GetCoursePort {
            override fun getCourseForRegistration(courseId: Long): Course {
                val date = LocalDateTime.now()

                return Course(
                    1,
                    "테스트 강의",
                    date.minusMinutes(5),
                    10,
                    date.minusDays(2),
                    registrationList
                )
            }
        }

        val sut = CourseRegistrationService(
            getCoursePort = getCoursePortStub,
            registerCoursePort = mockk(),
            getRegistrationStatusPort = mockk()
        )

        val registerCourseCommand = RegisterCourseCommand (courseId = 1, userId = 1001, updateDateTime = LocalDateTime.now())

        // act, assert
        AssertionsForClassTypes.assertThatThrownBy {sut.registerCourse(registerCourseCommand) }
            .isInstanceOf(CourseException::class.java)
            .hasMessage("이미 수강신청한 유저입니다.")
    }

    @Test
    fun `수강신청 상태를 조회를 성공한다` () {
        // arrange
        val getRegistrationStatusPortStub = object : GetRegistrationStatusPort {
            override fun getRegistrationStatus(courseId: Long, userId: Long): CourseRegistration {
                return CourseRegistration(1, "테스트 강의", 1000, LocalDateTime.now())
            }
        }

        val sut = CourseRegistrationService(
            getCoursePort = mockk(),
            registerCoursePort = mockk(),
            getRegistrationStatusPort = getRegistrationStatusPortStub
        )

        val getRegistrationStatusCommand = GetRegistrationStatusCommand(1, 1000)

        // act
        val actual = sut.getRegistrationStatus(getRegistrationStatusCommand)

        // assert
        assertEquals(actual.courseId, 1)
        assertEquals(actual.userId, 1000)
    }
}