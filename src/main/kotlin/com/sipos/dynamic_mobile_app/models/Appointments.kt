package com.sipos.dynamic_mobile_app.models

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "appointments")
class Appointments : ParentModelObject() {
    @JsonFormat(pattern = "dd/MM/yyyy")
    val date: LocalDate? = null

    @OneToOne
    @JoinColumn(name = "professional")
    val professional: Professionals? = null

    @OneToOne
    @JoinColumn(name = "user")
    val user: AccountDetails? = null

    @OneToOne
    @JoinColumn(name = "service")
    val service: Services? = null

}