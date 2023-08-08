package com.sipos.dynamic_mobile_app.models

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "services")
class Services : ParentModelObject() {
    var name: String? = null
    var cost: Double? = null

}