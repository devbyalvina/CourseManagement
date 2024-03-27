package com.practice.coursemanagement.course.application.domain.Exception

class CourseException : RuntimeException {
    var errorCode: CourseErrorCode
    var msg: String

    constructor(courseErrorCode: CourseErrorCode) : super(courseErrorCode.message) {
        this.errorCode = courseErrorCode
        this.msg = courseErrorCode.message
    }

    constructor(courseErrorCode: CourseErrorCode, message: String) : super(message) {
        this.errorCode = courseErrorCode
        this.msg = message
    }
}