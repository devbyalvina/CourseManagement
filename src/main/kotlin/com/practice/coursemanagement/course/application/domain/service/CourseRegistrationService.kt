package com.practice.coursemanagement.course.application.domain.service

import com.practice.coursemanagement.course.application.domain.model.CourseRegistration
import com.practice.coursemanagement.course.application.port.`in`.*
import com.practice.coursemanagement.course.application.port.out.*
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class CourseRegistrationService (
    private val getCoursePort: GetCoursePort,
    private val registerCoursePort: RegisterCoursePort,
    private val getRegistrationStatusPort: GetRegistrationStatusPort,
) : CourseRegistrationUseCase{
    /**
     *  수강신청
     */
    @Transactional
    override fun registerCourse(registerCourseCommand: RegisterCourseCommand): CourseRegistration {
        // 강의 정보 가져오기
        val course = getCoursePort.getCourseForRegistration(registerCourseCommand.courseId)

        // 수강 신청 Validation Check
        CourseRegistrationValidator.validateCourse(course, registerCourseCommand)

        // 수강 신청 정보 객체 생성
        val courseRegistration = CourseRegistration (
                                    courseId = registerCourseCommand.courseId,
                                    courseName = course.courseName,
                                    userId = registerCourseCommand.userId,
                                    updateDateTime = registerCourseCommand.updateDateTime
                                )

        // 수강 신청 정보 저장
        val registerCourseResult = registerCoursePort.registerCourse(courseRegistration)

        return CourseRegistration(registerCourseResult.courseId, course.courseName, registerCourseResult.userId, registerCourseResult.updateDateTime)
    }

    /**
     *  수강신청 상태 조회
     */
    override fun getRegistrationStatus(getRegistrationStatusCommand: GetRegistrationStatusCommand): CourseRegistration {
        return getRegistrationStatusPort.getRegistrationStatus(getRegistrationStatusCommand.courseId, getRegistrationStatusCommand.userId)
    }
}


