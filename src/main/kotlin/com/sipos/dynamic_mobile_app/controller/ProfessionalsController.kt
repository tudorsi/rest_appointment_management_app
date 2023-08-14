package com.sipos.dynamic_mobile_app.controller

import com.sipos.dynamic_mobile_app.dto.ProfessionalDto
import com.sipos.dynamic_mobile_app.dto.ProfessionalResponseDto
import com.sipos.dynamic_mobile_app.models.Professionals
import com.sipos.dynamic_mobile_app.models.SuccessResponse
import com.sipos.dynamic_mobile_app.repository.AppointmentServicesRepository
import com.sipos.dynamic_mobile_app.repository.ProfessionalsRepository
import com.sipos.dynamic_mobile_app.services.AppointmentServicesService
import com.sipos.dynamic_mobile_app.services.ProfessionalsService
import org.springframework.web.bind.annotation.*
import java.util.*

@CrossOrigin
@RestController
@RequestMapping("/api/professional")
class ProfessionalsController(
    private val professionalsService: ProfessionalsService,
    private val professionalsRepository: ProfessionalsRepository,
    private val appointmentServicesService: AppointmentServicesService,
    private val appointmentServicesRepository: AppointmentServicesRepository
) {
    @PostMapping("addProfessional")
    fun addProfessional(
        @RequestBody professionalDto: ProfessionalDto
    ) {
        val professional = Professionals()
        professional.firstName = professionalDto.firstName
        professional.lastName = professionalDto.lastName
        professionalsRepository.save(professional)
    }

    @GetMapping("getProfessionals")
    fun getProfessionals(): List<ProfessionalResponseDto> {
        val professionals = professionalsRepository.findAll()
        val professionalDtos = mutableListOf<ProfessionalResponseDto>()
        professionals.forEach { professional ->
            professionalDtos.add(
                ProfessionalResponseDto
                    .professionalToResponseDto(professional)
            )
        }
        return professionalDtos
    }

    @PutMapping("addServiceToProfessional/{professionalId}")
    fun addServicetoProfessional(
        @PathVariable professionalId: UUID,
        @RequestParam serviceId: UUID
    ): SuccessResponse {
        val professional = professionalsService.findProfessionalById(professionalId)
        val service = appointmentServicesService.findServiceById(serviceId)
        professional.services.add(service)
        professionalsRepository.save(professional)
        service.professionals.add(professional)
        appointmentServicesRepository.save(service)
        return SuccessResponse(true)
    }
}