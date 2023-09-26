package com.sipos.dynamic_mobile_app.models

import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "users")
class AccountDetails : ParentModelObject() {
    var firstName: String? = null
    var lastName: String? = null

    fun getUUID(): UUID? {
        return this.uuid
    }

}