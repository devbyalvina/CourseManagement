package com.practice.coursemanagement.course.adapter.out.persistence

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class RegistrationJpaEntityPk(
    var courseId: Long,
    var userId: Long
): Serializable
