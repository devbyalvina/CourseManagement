package com.practice.coursemanagement.course.adapter.`in`.web

import com.practice.coursemanagement.course.application.domain.Exception.CourseException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CourseRegistrationControllerAdvice {
    val log: Logger get() = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(
        CourseException::class
    )
    fun handleCustomException(e: CourseException): CourseRegistrationResponse<Any> {
        log.warn("CustomException : {}", e.msg)
        return CourseRegistrationResponse.error(e.errorCode, e.msg)
    }

    @ExceptionHandler(
        Exception::class
    )
    fun handleException(e: Exception): ResponseEntity<String> {
        log.error("Exception : {}", e.message, e)
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(e.message)
    }
}