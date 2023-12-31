package com.sipos.dynamic_mobile_app.dto

import com.sipos.dynamic_mobile_app.models.Professionals
import java.util.*

data class ProfessionalResponseDto(
    var professionalId: UUID,
    var firstName: String,
    var lastName: String,
    var rating: Double
) {
    companion object {
        fun professionalToResponseDto(professional: Professionals): ProfessionalResponseDto {
            return ProfessionalResponseDto(
                professional.getUUID()!!,
                professional.firstName ?: "",
                professional.lastName ?: "",
                professional.rating ?: 5.0
            )
        }
    }

}