package org.example.controllers

import org.example.usecases.FindPeople
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.slf4j.Logger
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.time.Duration

@RestController
@RequestMapping("/people")
class PeopleController(private val findPeople: FindPeople) {

companion object{
    val log: Logger = LoggerFactory.getLogger(PeopleController::class.java)
}
    @GetMapping("/{id}")
    fun findPeople(@PathVariable id: Long) : Map<String, Any> {
        val start = LocalDateTime.now()
        log.info("start: {}", start)
        val name = findPeople.name(id)
        val end = LocalDateTime.now()
        log.info("end  : {}", end)
        log.info("total: {} ms", java.time.Duration.between(start, end).toMillis())
        return mapOf("name" to name)
    }

    @GetMapping("/range/{quantity}")
    fun findPeoplesName(@PathVariable quantity: Long) : ArrayList<String> {
        val start = LocalDateTime.now()
        log.info("start: {}", start)
        val names = findPeople.range(quantity)
        val end = LocalDateTime.now()
        log.info("end  : {}", end)
        log.info("total: {} ms", java.time.Duration.between(start, end).toMillis())
        log.info("names: {}", names)
        return names
    }
}