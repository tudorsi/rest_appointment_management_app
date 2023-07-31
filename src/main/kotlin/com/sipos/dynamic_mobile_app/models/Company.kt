package com.sipos.dynamic_mobile_app.models

import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "companies")
class Company: ParentModelObject() {
    private var name: String? = null
    private var address: String? = null
    @OneToMany(mappedBy = "company")
    private var professionals: List<Professional> = emptyList()

}