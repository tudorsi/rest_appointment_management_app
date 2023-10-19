package com.sipos.dynamic_mobile_app

import com.sipos.dynamic_mobile_app.services.AccountCredentialsService
import com.sipos.dynamic_mobile_app.services.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.authority.SimpleGrantedAuthority

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableWebSecurity
open class WebSecurityConfiguration() {
    private lateinit var tokenService: TokenService
    private lateinit var accountCredentialsService: AccountCredentialsService

    @Bean
    open fun encoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Bean
    open fun authProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(accountCredentialsService)
        authProvider.setPasswordEncoder(encoder())
        return authProvider
    }

    @Bean
    @Throws(Exception::class)
    open fun authManager(http: HttpSecurity): AuthenticationManager? {
        return http.getSharedObject(AuthenticationManagerBuilder::class.java)
            .authenticationProvider(authProvider())
            .build()
    }

    @Bean
    open fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf {
                disable()

            }
            httpBasic { }
            authorizeRequests {
                authorize("/api/account/**", permitAll)
//                authorize(anyRequest,authenticated)

            }
            oauth2ResourceServer {
                jwt {}
            }
        }

        http.authenticationManager {
            val jwt = it as BearerTokenAuthenticationToken
            val user = tokenService.parseToken(jwt.token) ?: throw InvalidBearerTokenException("Invalid token")
            UsernamePasswordAuthenticationToken(user, "", listOf(SimpleGrantedAuthority("USER")))
        }

        return http.build()
    }

    @Lazy
    @Autowired
    fun setAccountCredentialsService(accountCredentialsService: AccountCredentialsService) {
        this.accountCredentialsService = accountCredentialsService
    }

    @Lazy
    @Autowired
    fun setTokenService(tokenService: TokenService) {
        this.tokenService = tokenService
    }


}