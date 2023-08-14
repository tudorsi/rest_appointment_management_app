package com.sipos.dynamic_mobile_app.controller

import com.sipos.dynamic_mobile_app.dto.ServiceDto
import com.sipos.dynamic_mobile_app.dto.ServicesResponseDto
import com.sipos.dynamic_mobile_app.models.SuccessResponse
import com.sipos.dynamic_mobile_app.services.AppointmentServicesService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/api/service")
class ServicesController(
    private val appointmentServicesService: AppointmentServicesService
) {
    private val logger = LoggerFactory.getLogger(ServicesController::class.java)

    @PostMapping("addService")
    fun addService(
        @RequestBody serviceDto: ServiceDto
    ): SuccessResponse {
        return appointmentServicesService.addService(serviceDto)
    }

    @GetMapping("getServices")
    fun getServices(): List<ServicesResponseDto> {
        val serviceDtos = mutableListOf<ServicesResponseDto>()
        appointmentServicesService.getServices().forEach { service ->
            serviceDtos.add(ServicesResponseDto.serviceToResponseDto(service))
        }
        return serviceDtos
    }

}