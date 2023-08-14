package com.sipos.dynamic_mobile_app.repository

import com.sipos.dynamic_mobile_app.models.Businesses
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BusinessRepository : CrudRepository<Businesses, UUID> {
}