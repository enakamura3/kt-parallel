package org.example.controllers

import java.time.LocalDateTime
import kotlinx.coroutines.runBlocking
import org.example.usecases.FindPeople
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/people")
class PeopleController(private val findPeople: FindPeople) {

    companion object {
        val log: Logger = LoggerFactory.getLogger(PeopleController::class.java)
    }

    @GetMapping("/{id}")
    fun findPeople(@PathVariable id: Long): Map<String, Any> {
        val start = LocalDateTime.now()
        log.info("start: {}", start)
        val name = findPeople.name(id)
        val end = LocalDateTime.now()
        log.info("end  : {}", end)
        log.info("total: {} ms", java.time.Duration.between(start, end).toMillis())
        return mapOf("name" to name)
    }

    @GetMapping("/range/{quantity}")
    fun findPeoplesName(@PathVariable quantity: Long): ArrayList<String> {
        val start = LocalDateTime.now()
        log.info("start: {}", start)
        val names = findPeople.range(quantity)
        val end = LocalDateTime.now()
        log.info("end  : {}", end)
        log.info("total: {} ms", java.time.Duration.between(start, end).toMillis())
        log.info("names: {}", names)
        return names
    }

    @GetMapping("/range/p/{quantity}")
    fun findPeoplesNameP(@PathVariable quantity: Long): ArrayList<String> {
        val start = LocalDateTime.now()
        log.info("start: {}", start)
        val names = findPeople.rangeParallel(quantity)
        val end = LocalDateTime.now()
        log.info("end  : {}", end)
        log.info("total: {} ms", java.time.Duration.between(start, end).toMillis())
        log.info("names: {}", names)
        return names
    }

    @GetMapping("/range/c/{quantity}")
    fun getPeople(@PathVariable quantity: Long): ResponseEntity<List<String>> = runBlocking {
        val start = LocalDateTime.now()
        log.info("start: {}", start)
        val result = findPeople.rangeParallelC(quantity)
        val end = LocalDateTime.now()
        log.info("end  : {}", end)
        log.info("total: {} ms", java.time.Duration.between(start, end).toMillis())
        log.info("names: {}", result)
        ResponseEntity.ok(result)
    }
}