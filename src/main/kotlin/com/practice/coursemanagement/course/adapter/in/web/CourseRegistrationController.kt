package com.practice.coursemanagement.course.adapter.`in`.web

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
    ): CourseRegistrationResponse {
        val registerCourseCommand = RegisterCourseCommand(registerCourseRequest.courseId, registerCourseRequest.userId, LocalDateTime.now())
        return CourseRegistrationResponse.of(courseRegistrationUseCase.registerCourse(registerCourseCommand))
    }

    /**
     *  수강신청 상태 조회
     */
    @GetMapping ("/{courseId}/{userId}/status")
    fun getRegistrationStatus(
        @PathVariable courseId: Long,
        @PathVariable userId: Long
    ): CourseRegistrationResponse {
        val getRegistrationStatusCommand = GetRegistrationStatusCommand(courseId, userId)
        return CourseRegistrationResponse.of(courseRegistrationUseCase.getRegistrationStatus(getRegistrationStatusCommand))
    }
}