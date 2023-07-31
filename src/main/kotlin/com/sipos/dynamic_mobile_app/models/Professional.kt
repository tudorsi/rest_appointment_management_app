package com.sipos.dynamic_mobile_app.models

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "professionals")
class Professional: ParentModelObject() {
    private var firstName: String? = null
    private var lastName: String? = null

    @ManyToOne
    @JoinColumn(name= "company_id", nullable = false, referencedColumnName = "uuid")
    private  var company: Company? = null
}