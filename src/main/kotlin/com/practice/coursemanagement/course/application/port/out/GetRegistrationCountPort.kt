package com.practice.coursemanagement.course.application.port.out

interface GetRegistrationCountPort {
    fun getRegistrationCount(courseId: Long) : Long
}