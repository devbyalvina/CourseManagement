package com.practice.coursemanagement.course.application.domain.service

import com.practice.coursemanagement.course.application.domain.model.CourseRegistration
import com.practice.coursemanagement.course.application.port.`in`.*
import com.practice.coursemanagement.course.application.port.out.GetCourseInformationPort
import com.practice.coursemanagement.course.application.port.out.GetRegistrationCountPort
import com.practice.coursemanagement.course.application.port.out.RegisterCoursePort
import com.practice.coursemanagement.course.application.port.out.GetRegistrationStatusPort
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
@Transactional
class CourseRegistrationService (
    private val getCourseInformationPort: GetCourseInformationPort,
    private val getRegistrationCountPort: GetRegistrationCountPort,
    private val registerCoursePort: RegisterCoursePort,
    private val getRegistrationStatusPort: GetRegistrationStatusPort,
) : CourseInformationUseCase, CourseRegistrationUseCase{

    /**
     *  수강신청
     */
    @Transactional
    override fun registerCourse(registerCourseCommand: RegisterCourseCommand): CourseRegistrationResult {
        // 강의 정보 가져오기 & validation 체크
        val courseInformation = getCourseInformationPort.getCourseInformation(registerCourseCommand.courseId)
            ?: throw RuntimeException("강의 정보가 없습니다")

        // 날짜 validation 체크
        if (registerCourseCommand.updateDateTime.isBefore(courseInformation.startSignUpDateTime))
            throw RuntimeException("강의 정보가 없습니다")

        // 현재까지 수강 신청한 인원 수 정보 가져오기
        val count = getRegistrationCountPort.getRegistrationCount(registerCourseCommand.courseId)

        // 수강 인원수 validation 체크
        if (count > courseInformation.capacity)
            throw RuntimeException("수강신청 정원을 초과하였습니다.")

        // 수강 신청 Insert
        val registrationResult = registerCoursePort.registerCourse(CourseRegistration(
            courseId = registerCourseCommand.courseId,
            userId = registerCourseCommand.userId,
            updateDateTime = registerCourseCommand.updateDateTime
        )) ?: throw RuntimeException("수강신청이 마감되었습니다.")

        return CourseRegistrationResult (registrationResult.courseId, courseInformation.courseName, registrationResult.userId, registrationResult.updateDateTime, RegisterStatus.SUCCESS)
    }

    /**
     *  수강신청 상태 조회
     */
    override fun getRegistrationStatus(getRegistrationStatusCommand: GetRegistrationStatusCommand): CourseRegistrationResult {
        val registrationStatus = getRegistrationStatusPort.getRegistrationStatus(getRegistrationStatusCommand.courseId, getRegistrationStatusCommand.userId)
            ?: throw RuntimeException("수강신청 내역이 없습니다.")

        val courseInformation = getCourseInformationPort.getCourseInformation(getRegistrationStatusCommand.courseId)
            ?: throw RuntimeException("강의 정보가 없습니다")

        return CourseRegistrationResult (registrationStatus.courseId, courseInformation.courseName, registrationStatus.userId, registrationStatus.updateDateTime, RegisterStatus.SUCCESS)
    }

    override fun getCourseInformation(getCourseInformationCommand: GetCourseInformationCommand): CourseInformationResult {
        TODO("Not yet implemented")
    }
}


