package com.practice.coursemanagement.course.adapter.`in`.web


import com.practice.coursemanagement.course.application.domain.Exception.CourseErrorCode

data class CourseRegistrationResponse<T>(
    var code: Int,
    val message: String,
    val body: T? = null,
) {
    companion object {
        @JvmStatic
        fun <T> ok(): CourseRegistrationResponse<T> {
            return process(0, "success", null)
        }

        @JvmStatic
        fun <T> ok(body: T): CourseRegistrationResponse<T> {
            return process(0, "success", body)
        }

        @JvmStatic
        fun <T> error(errorCode: CourseErrorCode): CourseRegistrationResponse<T> {
            return process(errorCode.code, errorCode.message, null)
        }

        @JvmStatic
        fun <T> error(errorCode: CourseErrorCode, message: String): CourseRegistrationResponse<T> {
            return process(errorCode.code, message, null)
        }

        @JvmStatic
        private fun <T> process(code: Int, message: String, body: T?): CourseRegistrationResponse<T> {
            return CourseRegistrationResponse(code, message, body)
        }
    }
}


