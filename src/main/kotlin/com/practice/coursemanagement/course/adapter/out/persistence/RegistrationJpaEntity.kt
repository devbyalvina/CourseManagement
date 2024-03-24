package com.practice.coursemanagement.course.adapter.out.persistence

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
class RegistrationJpaEntity (
    @EmbeddedId
    val registrationJpaEntityPk: RegistrationJpaEntityPk,

    val updateDateTime: LocalDateTime
)