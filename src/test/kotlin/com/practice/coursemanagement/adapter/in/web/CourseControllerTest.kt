package com.practice.coursemanagement.adapter.`in`.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.practice.coursemanagement.course.adapter.`in`.web.CourseRegistrationController
import com.practice.coursemanagement.course.adapter.`in`.web.RegisterCourseRequest
import com.practice.coursemanagement.course.application.port.`in`.CourseRegistrationUseCase
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(CourseRegistrationController::class)
class CourseControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var courseRegistrationUseCase: CourseRegistrationUseCase

    @Test
    fun `수강신청을 성공한다`() {
        // given
        val request = RegisterCourseRequest(courseId = 1, userId = 1000)

        // then
        mockMvc.perform(
            patch("/course/registerCourse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.code").value("0"))
            .andExpect(jsonPath("$.message").value("success"))
    }

    @Test
    fun `수강신청 상태 조회를 성공한다`() {
        // given
        val courseId = 1
        val userId = 1000

        // then
        mockMvc.perform(
                get("/course/%d/%s/status".format(courseId, userId))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.code").value("0"))
            .andExpect(jsonPath("$.message").value("success"))
    }
}