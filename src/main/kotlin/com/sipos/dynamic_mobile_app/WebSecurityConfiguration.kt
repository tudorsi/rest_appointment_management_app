package com.sipos.dynamic_mobile_app

import com.sipos.dynamic_mobile_app.services.AccountCredentialsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
class WebSecurityConfiguration @Autowired constructor(

) {
    private lateinit var accountCredentialsService: AccountCredentialsService

    @Bean
    fun encoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(accountCredentialsService)
        authProvider.setPasswordEncoder(encoder())
        return authProvider
    }

    @Bean
    @Throws(Exception::class)
    fun authManager(http: HttpSecurity): AuthenticationManager? {
        return http.getSharedObject(AuthenticationManagerBuilder::class.java)
            .authenticationProvider(authProvider())
            .build()
    }

    @Bean
    open fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf { disable() }
            httpBasic { }
            authorizeRequests {
                authorize("/api/account/**", permitAll)

            }
        }
        return http.build()
    }

    @Lazy
    @Autowired
    fun setAccountCredentialsService(accountCredentialsService: AccountCredentialsService) {
        this.accountCredentialsService = accountCredentialsService
    }

}