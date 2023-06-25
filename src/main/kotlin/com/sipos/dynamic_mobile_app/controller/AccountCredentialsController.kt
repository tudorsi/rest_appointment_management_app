package com.sipos.dynamic_mobile_app.controller

import com.sipos.dynamic_mobile_app.models.RegisterRequestBody
import com.sipos.dynamic_mobile_app.models.SuccessResponse
import com.sipos.dynamic_mobile_app.services.AccountCredentialsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/api/account")
class AccountCredentialsController(
    private val accountCredentialsService: AccountCredentialsService
) {

    @PostMapping("register")
    fun register(
        @RequestBody registerRequestBody: RegisterRequestBody
    ): SuccessResponse {
        val rr = RegisterRequestBody(
            registerRequestBody.password,
            registerRequestBody.email,
            registerRequestBody.firstName,
            registerRequestBody.lastName
        )
        return accountCredentialsService.register(rr)
    }

    @GetMapping("testare")
    fun testare() = ResponseEntity.ok("ana are mere")

}