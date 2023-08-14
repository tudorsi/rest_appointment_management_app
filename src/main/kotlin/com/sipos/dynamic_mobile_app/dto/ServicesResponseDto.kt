package com.sipos.dynamic_mobile_app.dto

import com.sipos.dynamic_mobile_app.models.Services
import java.util.*

data class ServicesResponseDto(
    val id: UUID? = null,
    val name: String? = null,
    val cost: Double? = null
) {
    companion object {
        fun serviceToResponseDto(service: Services): ServicesResponseDto {
            return ServicesResponseDto(service.getUUID(), service.name, service.cost)
        }
    }
}
