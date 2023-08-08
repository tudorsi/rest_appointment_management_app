package com.sipos.dynamic_mobile_app.models

import jakarta.persistence.*

@Entity
@Table(name = "professionals")
class Professionals : ParentModelObject() {
    val firstName: String? = null
    val lastName: String? = null

    @ManyToOne
    @JoinColumn(name = "business")
    val business: Businesses? = null

    @OneToMany
    @JoinColumn(name = "services")
    val services: List<Services>? = null

}