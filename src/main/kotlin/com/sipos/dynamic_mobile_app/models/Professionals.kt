package com.sipos.dynamic_mobile_app.models

import jakarta.persistence.*
import java.util.*
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin

@Entity
@Table(name = "professionals")
class Professionals : ParentModelObject() {
    var firstName: String? = null
    var lastName: String? = null

    @ManyToOne
    @JoinColumn(name = "business")
    var business: Businesses? = null

    @ManyToMany(mappedBy = "professionals")
    val services: MutableList<Services> = mutableListOf()

    @DecimalMax("5.0")
    @DecimalMin("0.0")
    val rating: Double? = null
    fun getUUID(): UUID? {
        return this.uuid
    }

}