package com.sipos.dynamic_mobile_app.models

import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import java.util.*
@MappedSuperclass
open class ParentModelObject: Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private var uuid: UUID? = null
}