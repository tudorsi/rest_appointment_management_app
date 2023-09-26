package com.sipos.dynamic_mobile_app.controller


import com.sipos.dynamic_mobile_app.dto.CreateAppointmentDto
import com.sipos.dynamic_mobile_app.models.Appointments
import com.sipos.dynamic_mobile_app.models.SuccessResponse
import com.sipos.dynamic_mobile_app.repository.AppointmentRepository
import com.sipos.dynamic_mobile_app.repository.AppointmentServicesRepository
import com.sipos.dynamic_mobile_app.repository.ProfessionalsRepository
import com.sipos.dynamic_mobile_app.repository.UserDetailsRepository
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/api/appointment")
class AppointmentsController(
    private val appointmentRepository: AppointmentRepository,
    private val userDetailsRepository: UserDetailsRepository,
    private val professionalsRepository: ProfessionalsRepository,
    private val servicesRepository: AppointmentServicesRepository
) {
    @PutMapping("createAppointment")
    fun createAppointment(
        @RequestBody createAppointmentRequestBody: CreateAppointmentDto
    ): SuccessResponse {
        val professional = createAppointmentRequestBody.professionalId?.let {
            professionalsRepository.findById(it).get()
        }
        val user = createAppointmentRequestBody.userId?.let {
            userDetailsRepository.findById(it).get()
        }
        val service = createAppointmentRequestBody.serviceId?.let {
            servicesRepository.findById(it).get()
        }
        val appointment = Appointments()

        appointment.date = createAppointmentRequestBody.date
        appointment.professional = professional
        appointment.user = user
        appointment.service = service

        appointmentRepository.save(appointment)

        return SuccessResponse(true)

    }
}