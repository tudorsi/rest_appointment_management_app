package com.sipos.dynamic_mobile_app.services

import com.sipos.dynamic_mobile_app.dto.ServiceDto
import com.sipos.dynamic_mobile_app.models.Services
import com.sipos.dynamic_mobile_app.models.SuccessResponse
import com.sipos.dynamic_mobile_app.repository.AppointmentServicesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AppointmentServicesService @Autowired constructor(
    private val appointmentServicesRepository: AppointmentServicesRepository
) {

    fun addService(requestBody: ServiceDto): SuccessResponse {
        val service = Services()
        service.name = requestBody.name
        service.cost = requestBody.cost
        appointmentServicesRepository.save(service)
        return SuccessResponse(true)
    }
}