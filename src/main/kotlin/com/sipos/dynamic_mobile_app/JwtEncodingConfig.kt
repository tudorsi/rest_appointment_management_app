package com.sipos.dynamic_mobile_app

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.crypto.spec.SecretKeySpec

@Configuration
class JwtEncodingConfig(
    @Value("\${security.key}")
    private val jwtKey: String,
) {
//    private val secretKey = SecretKeySpec(jwtKey.toByteArray(), "HmacSHA256")
//
//    @Bean
//    fun jwtDecoder(): JwtDecoder {
//        return NimbusJwtDecoder.withSecretKey(secretKey).build()
//    }
//
//    @Bean
//    fun jwtEncoder(): JwtEncoder {
//        val secret = ImmutableSecret<SecurityContext>(secretKey)
//        return NimbusJwtEncoder(secret)
//    }
}