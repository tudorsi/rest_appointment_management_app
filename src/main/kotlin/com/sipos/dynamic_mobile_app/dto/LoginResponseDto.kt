package com.sipos.dynamic_mobile_app.dto

import java.util.*

data class LoginResponseDto(
    val token: String,
    val userId: UUID
)