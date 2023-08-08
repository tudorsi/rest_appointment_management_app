package com.sipos.dynamic_mobile_app.services

import com.sipos.dynamic_mobile_app.models.AccountCredentials
import com.sipos.dynamic_mobile_app.models.AccountDetails
import com.sipos.dynamic_mobile_app.models.RegisterRequestBody
import com.sipos.dynamic_mobile_app.models.SuccessResponse
import com.sipos.dynamic_mobile_app.repository.AccountCredentialsRepository
import com.sipos.dynamic_mobile_app.repository.UserDetailsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AccountCredentialsService @Autowired constructor(
    private val accountCredentialsRepository: AccountCredentialsRepository,
    private val userDetailRepository: UserDetailsRepository,
    private val passwordEncoder: PasswordEncoder

) : UserDetailsService {
    @Transactional
    fun register(requestBody: RegisterRequestBody): SuccessResponse {
        //TODO: encode password
        val hashedPassword = passwordEncoder.encode(requestBody.password)
        val accountCredentials = AccountCredentials()
        accountCredentials.password = hashedPassword
        accountCredentials.username = requestBody.email
        if (accountCredentialsRepository.existsByEmail(accountCredentials.username)) {
            throw RuntimeException("Duplicate user")
        }
        val accountDetails = AccountDetails()
        accountDetails.firstName = requestBody.firstName
        accountDetails.lastName = requestBody.lastName

        accountCredentialsRepository.save(accountCredentials)
        userDetailRepository.save(accountDetails)
        accountCredentials.details = accountDetails
        accountCredentialsRepository.save(accountCredentials)
        return SuccessResponse(true)
    }

    override fun loadUserByUsername(username: String?): AccountCredentials {
        val account = username?.lowercase()?.let { accountCredentialsRepository.findByEmail(it) }

        if (account != null) {
            return account
        } else {
            throw Exception("User not found")
        }
    }

    fun findById(userId: UUID): AccountCredentials {
        val account = accountCredentialsRepository.findById(userId).orElse(null)
        if (account != null) {
            return account
        } else {
            throw Exception("User not found")
        }
    }
}