package com.sipos.dynamic_mobile_app.services

import com.sipos.dynamic_mobile_app.models.Businesses
import com.sipos.dynamic_mobile_app.repository.AppointmentServicesRepository
import com.sipos.dynamic_mobile_app.repository.BusinessRepository
import com.sipos.dynamic_mobile_app.repository.ProfessionalsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class BusinessService @Autowired constructor(
    private val servicesRepository: AppointmentServicesRepository,
    private val businessRepository: BusinessRepository,
    private val professionalsRepository: ProfessionalsRepository
) {
    fun getBusinessByServiceId(serviceId: UUID): List<Businesses> {
        val businesses = businessRepository.findAll()
        val returnedBusinesses = mutableListOf<Businesses>()
        businesses.forEach { business ->
            val professionals = business.professionals
            professionals?.forEach { professional ->
                val services = professional.services
                services.forEach { service ->
                    if (service.getUUID() == serviceId) {
                        returnedBusinesses.add(business)
                    }
                }
            }
        }
        return returnedBusinesses
    }
}