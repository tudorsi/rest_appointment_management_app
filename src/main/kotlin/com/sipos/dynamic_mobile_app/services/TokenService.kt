package com.sipos.dynamic_mobile_app.services

import com.sipos.dynamic_mobile_app.models.AccountCredentials
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.jwt.*
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

@Service
open class TokenService @Autowired constructor(
    private val jwtDecoder: JwtDecoder,
    private val jwtEncoder: JwtEncoder,
    private val userService: AccountCredentialsService,
) {
    fun createToken(user: AccountCredentials): String {
        val jwsHeader = JwsHeader.with { "HS256" }.build()
        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(30L, ChronoUnit.DAYS))
            .subject(user.username)
            .claim("userId", user.getUUID())
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    fun parseToken(token: String): AccountCredentials? {
        return try {
            val jwt = jwtDecoder.decode(token)
            val userId = jwt.claims["userId"] as UUID
            userService.findById(userId)
        } catch (e: Exception) {
            null
        }
    }
}