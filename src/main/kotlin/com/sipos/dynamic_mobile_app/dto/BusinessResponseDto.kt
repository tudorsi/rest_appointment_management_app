package com.sipos.dynamic_mobile_app.dto

import com.sipos.dynamic_mobile_app.models.Businesses
import java.util.*

data class BusinessResponseDto(
    var businessId: UUID,
    var name: String
) {
    companion object {
        fun businessToResponseDto(business: Businesses): BusinessResponseDto {
            return BusinessResponseDto(business.getUUID()!!, business.name ?: "")
        }
    }

}
