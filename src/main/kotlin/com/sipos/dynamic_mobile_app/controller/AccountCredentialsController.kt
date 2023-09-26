package com.sipos.dynamic_mobile_app.controller

import com.sipos.dynamic_mobile_app.dto.LoginDto
import com.sipos.dynamic_mobile_app.dto.LoginResponseDto
import com.sipos.dynamic_mobile_app.models.RegisterRequestBody
import com.sipos.dynamic_mobile_app.models.SuccessResponse
import com.sipos.dynamic_mobile_app.services.AccountCredentialsService
import com.sipos.dynamic_mobile_app.services.HashService
import com.sipos.dynamic_mobile_app.services.TokenService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@CrossOrigin
@RestController
@RequestMapping("/api/account")
class AccountCredentialsController(
    private val accountCredentialsService: AccountCredentialsService,
    private val hashService: HashService,
    private val tokenService: TokenService
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

    @PostMapping("login")
    fun login(
        @RequestBody loginDto: LoginDto
    ): LoginResponseDto {
        val user = accountCredentialsService.loadUserByUsername(loginDto.username)

        if (!hashService.checkBcrypt(loginDto.password, user.password)) {
            throw ResponseStatusException(400, "Login failed", null)
        }

        return LoginResponseDto(
            token = tokenService.createToken(user),
            userId = user.details?.getUUID()!!
        )
    }

    @GetMapping("testare")
    fun testare() = ResponseEntity.ok("ana are mere")

}