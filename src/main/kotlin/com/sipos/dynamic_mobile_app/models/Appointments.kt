package com.sipos.dynamic_mobile_app.models

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.util.*
@Entity
@Table(name = "user_appointments")
class Appointments : ParentModelObject() {
    var date: Date? = null

    @OneToOne
    @JoinColumn(name = "professional")
    var professional: Professionals? = null

    @OneToOne
    @JoinColumn(name = "user_details")
    var user: AccountDetails? = null

    @OneToOne
    @JoinColumn(name = "service")
    var service: Services? = null

}