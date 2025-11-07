package org.example.greenometer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GreenometerApplication

fun main(args: Array<String>) {
    runApplication<GreenometerApplication>(*args)
}
