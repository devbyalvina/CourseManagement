package com.practice.coursemanagement.course.adapter.`in`.web

import com.practice.coursemanagement.course.application.domain.model.CourseRegistration
import com.practice.coursemanagement.course.application.port.`in`.CourseRegistrationUseCase
import com.practice.coursemanagement.course.application.port.`in`.GetRegistrationStatusCommand
import com.practice.coursemanagement.course.application.port.`in`.RegisterCourseCommand
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/course")
class CourseRegistrationController (
    private val courseRegistrationUseCase: CourseRegistrationUseCase,
){
    /**
     *  수강신청
     */
    @PatchMapping("/registerCourse")
    fun registerCourse(
        @RequestBody registerCourseRequest: RegisterCourseRequest
    ): CourseRegistrationResponse<CourseRegistration> {
        val registerCourseCommand = RegisterCourseCommand(registerCourseRequest.courseId, registerCourseRequest.userId, LocalDateTime.now())
        val result = courseRegistrationUseCase.registerCourse(registerCourseCommand)
        return CourseRegistrationResponse.ok(result)
    }

    /**
     *  수강신청 상태 조회
     */
    @GetMapping ("/{courseId}/{userId}/status")
    fun getRegistrationStatus(
        @PathVariable courseId: Long,
        @PathVariable userId: Long
    ): CourseRegistrationResponse<CourseRegistration> {
        val getRegistrationStatusCommand = GetRegistrationStatusCommand(courseId, userId)
        val result = courseRegistrationUseCase.getRegistrationStatus(getRegistrationStatusCommand)
        return CourseRegistrationResponse.ok(result)
    }
}