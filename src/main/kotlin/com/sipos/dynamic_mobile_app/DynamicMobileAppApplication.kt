package com.sipos.dynamic_mobile_app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class DynamicMobileAppApplication

fun main(args: Array<String>) {
	runApplication<DynamicMobileAppApplication>(*args)
}
