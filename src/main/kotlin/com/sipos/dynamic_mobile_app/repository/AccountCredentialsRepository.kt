package com.sipos.dynamic_mobile_app.repository

import com.sipos.dynamic_mobile_app.models.AccountCredentials
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountCredentialsRepository: CrudRepository<AccountCredentials, UUID> {
    fun existsByEmail(email:String): Boolean
    fun findByEmail(email: String): AccountCredentials

}