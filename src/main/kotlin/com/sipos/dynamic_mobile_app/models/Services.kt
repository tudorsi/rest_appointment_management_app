package com.sipos.dynamic_mobile_app.models

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "services")
class Services : ParentModelObject() {
    var name: String? = null
    var cost: Double? = null

    @ManyToMany
    @JoinTable(
        name = "service_professional",
        joinColumns = arrayOf(
            JoinColumn(name = "service")
        ),
        inverseJoinColumns = arrayOf(JoinColumn(name = "professional"))
    )
    var professionals: MutableList<Professionals> = mutableListOf()
    fun getUUID(): UUID? {
        return this.uuid
    }

}