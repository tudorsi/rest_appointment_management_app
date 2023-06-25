package com.sipos.dynamic_mobile_app.models

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class AccountDetails : ParentModelObject() {
    var firstName: String? = null
    var lastName: String? = null

}