package org.example.gateways.clients

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "people", url = "https://swapi.dev/api")
interface PeopleClient {

    @GetMapping("/people/{id}")
    fun findPeople(@PathVariable id: Long) : Map<String, Any>
}