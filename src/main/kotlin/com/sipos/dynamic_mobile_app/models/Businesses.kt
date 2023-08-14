package com.sipos.dynamic_mobile_app.models

import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "businesses")
class Businesses : ParentModelObject() {
    var name: String? = null

    @OneToMany(mappedBy = "business")
    var professionals: MutableList<Professionals>? = null

    fun getUUID(): UUID? {
        return this.uuid
    }

}
