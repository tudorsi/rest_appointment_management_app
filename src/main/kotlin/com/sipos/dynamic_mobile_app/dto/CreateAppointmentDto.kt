package com.sipos.dynamic_mobile_app.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class CreateAppointmentDto(
    @JsonFormat(pattern = "dd/MM/yyyy")
    val date: Date? = null,
    val professionalId: UUID? = null,
    val userId: UUID? = null,
    val serviceId: UUID? = null
)
