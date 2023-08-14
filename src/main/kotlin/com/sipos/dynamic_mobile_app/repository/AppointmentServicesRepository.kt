package com.sipos.dynamic_mobile_app.repository

import com.sipos.dynamic_mobile_app.models.Services
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AppointmentServicesRepository : CrudRepository<Services, UUID> {
}