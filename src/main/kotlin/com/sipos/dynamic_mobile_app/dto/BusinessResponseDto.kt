package com.sipos.dynamic_mobile_app.dto

import com.sipos.dynamic_mobile_app.models.Businesses
import java.util.*

data class BusinessResponseDto(
    var businessId: UUID,
    var name: String,
    var city: String,
    var county: String,
    var address: String,
    var latitude: Double,
    var longitude: Double
) {
    companion object {
        fun businessToResponseDto(business: Businesses): BusinessResponseDto {
            return BusinessResponseDto(
                business.getUUID()!!,
                business.name ?: "",
                business.city ?: "",
                business.county ?: "",
                business.address ?: "",
                business.latitude ?: 0.0,
                business.longitude ?: 0.0
            )
        }
    }

}
