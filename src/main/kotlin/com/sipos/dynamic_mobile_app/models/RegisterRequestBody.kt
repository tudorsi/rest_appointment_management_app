package com.sipos.dynamic_mobile_app.models


import org.jetbrains.annotations.NotNull
import javax.validation.constraints.Email
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size


data class RegisterRequestBody(
    @NotNull
    @Size(min = 6, max = 35)
    @Pattern(regexp = "(?=.*[A-Z])(?=.*[a-z]).*")
    val password: String,

    @NotNull
    @Email
    val email: String,

    @NotNull
    @Size(min = 6, max = 25)
    val firstName: String,

    @NotNull
    @Size(min = 6, max = 25)
    val lastName: String,
    )
