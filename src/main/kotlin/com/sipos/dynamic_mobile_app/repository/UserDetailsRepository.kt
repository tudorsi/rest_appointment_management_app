package com.sipos.dynamic_mobile_app.repository

import com.sipos.dynamic_mobile_app.models.AccountDetails
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserDetailsRepository: CrudRepository<AccountDetails, UUID> {
}