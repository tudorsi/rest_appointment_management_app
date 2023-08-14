package com.sipos.dynamic_mobile_app.models

import jakarta.persistence.*
import java.util.*

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

    fun getUUID(): UUID? {
        return this.uuid
    }

}