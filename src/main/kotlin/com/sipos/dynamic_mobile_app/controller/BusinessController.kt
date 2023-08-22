package com.sipos.dynamic_mobile_app.controller

import com.sipos.dynamic_mobile_app.dto.BusinessDto
import com.sipos.dynamic_mobile_app.dto.BusinessResponseDto
import com.sipos.dynamic_mobile_app.models.Businesses
import com.sipos.dynamic_mobile_app.models.SuccessResponse
import com.sipos.dynamic_mobile_app.repository.BusinessRepository
import com.sipos.dynamic_mobile_app.repository.ProfessionalsRepository
import com.sipos.dynamic_mobile_app.services.BusinessService
import org.springframework.web.bind.annotation.*
import java.util.*

@CrossOrigin
@RestController
@RequestMapping("/api/business")
class BusinessController(
    private val businessRepository: BusinessRepository,
    private val professionalsRepository: ProfessionalsRepository,
    private val businessService: BusinessService
) {
    @PostMapping("addBusiness")
    fun addBusiness(
        @RequestBody businessDto: BusinessDto
    ): SuccessResponse {
        val business = Businesses()
        business.name = businessDto.name
        business.city = businessDto.city
        business.county = businessDto.county
        business.address = businessDto.address
        business.latitude = businessDto.latitude
        business.longitude = businessDto.longitude

        businessRepository.save(business)
        return SuccessResponse(true)
    }

    @PutMapping("addProfessionalToBusiness/{businessId}")
    fun addProfessionalToBusiness(
        @PathVariable businessId: UUID,
        @RequestParam professionalId: UUID
    ): SuccessResponse {
        val business = businessRepository.findById(businessId).get()
        val professional = professionalsRepository.findById(professionalId).get()

        business.professionals?.add(professional)
        professional.business = business
        professionalsRepository.save(professional)
        businessRepository.save(business)
        return SuccessResponse(true)

    }

    @GetMapping("getBusinesses")
    fun getBusinesses(): List<BusinessResponseDto> {
        val businesses = businessRepository.findAll()
        var businessDtos = mutableListOf<BusinessResponseDto>()
        businesses.forEach { business ->
            businessDtos.add(BusinessResponseDto.businessToResponseDto(business))
        }
        return businessDtos
    }

    @GetMapping("getBusinessesByServiceId/{serviceId}")
    fun getBusinessesByServiceId(@PathVariable serviceId: UUID): List<BusinessResponseDto> {
        val businesses = businessService.getBusinessByServiceId(serviceId)
        var businessDtos = mutableListOf<BusinessResponseDto>()
        businesses.forEach { business ->
            businessDtos.add(BusinessResponseDto.businessToResponseDto(business))
        }
        return businessDtos
    }
}