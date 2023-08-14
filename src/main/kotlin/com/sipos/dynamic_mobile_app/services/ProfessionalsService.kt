package com.sipos.dynamic_mobile_app.services

import com.sipos.dynamic_mobile_app.models.Professionals
import com.sipos.dynamic_mobile_app.repository.ProfessionalsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProfessionalsService @Autowired constructor(
    private val professionalsRepository: ProfessionalsRepository
) {
    fun findProfessionalById(professionalId: UUID): Professionals {
        return professionalsRepository.findById(professionalId).get()
    }
}