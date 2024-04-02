package com.practice.coursemanagement

import com.practice.coursemanagement.CourseManagementApplicationTests
import com.practice.coursemanagement.course.adapter.out.persistence.CourseInformationJpaEntity
import com.practice.coursemanagement.course.adapter.out.persistence.CourseInformationPersistenceAdapter
import com.practice.coursemanagement.course.adapter.out.persistence.CourseInformationRepository
import com.practice.coursemanagement.course.adapter.out.persistence.CourseRegistrationRepository
import com.practice.coursemanagement.course.application.domain.model.CourseRegistration
import com.practice.coursemanagement.course.application.domain.service.CourseRegistrationService
import com.practice.coursemanagement.course.application.port.`in`.RegisterCourseCommand
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import java.time.LocalDateTime
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletableFuture.allOf
import java.util.concurrent.atomic.AtomicInteger

@SpringBootTest
class CourseManagementApplicationTests (
	@Autowired private val courseRegistrationService: CourseRegistrationService,
	@Autowired private val courseInformationRepository: CourseInformationRepository,
	@Autowired private val courseRegistrationRepository: CourseRegistrationRepository,
	@Autowired private val courceInformationPersistenceAdapter: CourseInformationPersistenceAdapter
) {
	private val logger: Logger = LoggerFactory.getLogger(javaClass)

	@Test
	fun contextLoads() {
	}
	@Test
	fun `수강신청을 성공한다`() {
		// given
		val registerCourseCommand = RegisterCourseCommand(1, 1000, LocalDateTime.now())
		courseInformationRepository.save(
			CourseInformationJpaEntity(
				1,
				"테스트 강의",
				LocalDateTime.now().minusMinutes(10),
				30,
				LocalDateTime.now().minusMinutes(15)
			))

		// when
		val result = courseRegistrationService.registerCourse(registerCourseCommand)

		// then
		assertThat(result.userId == 1000L)
	}


}
