package com.sipos.dynamic_mobile_app.models

import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "businesses")
class Businesses : ParentModelObject() {
    val name: String? = null

    @OneToMany(mappedBy = "business")
    val professionals: List<Professionals>? = null

}
