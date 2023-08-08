package com.sipos.dynamic_mobile_app.controller

import com.sipos.dynamic_mobile_app.dto.ServiceDto
import com.sipos.dynamic_mobile_app.models.SuccessResponse
import com.sipos.dynamic_mobile_app.services.AppointmentServicesService
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/api/service")
class ServicesController(
    private val appointmentServicesService: AppointmentServicesService
) {
    @PostMapping("addService")
    fun addService(
        @RequestBody serviceDto: ServiceDto
    ): SuccessResponse {
        return appointmentServicesService.addService(serviceDto)
    }
}