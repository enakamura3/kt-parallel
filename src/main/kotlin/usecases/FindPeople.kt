package org.example.usecases

import org.example.gateways.PeopleGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class FindPeople(private val peopleGateway: PeopleGateway) {
    companion object{
        val log: Logger = LoggerFactory.getLogger(FindPeople::class.java)
    }
    fun name(id: Long) : String {
        log.info("Find people name from id: {}", id)
        val p = peopleGateway.findPeople(id)
        return p["name"].toString()
    }

    fun range(quantity: Long) : ArrayList<String> {
        var names = arrayListOf<String>()
        val range = 1..quantity
        for (id in range) {
            val name = name(id)
            names.add(name)
        }
        return names
    }

    fun rangeParallel(quantity: Long) : ArrayList<String> {
        var names = arrayListOf<String>()
        val range = 1..quantity
        range.toList().parallelStream().map { id ->
            log.debug("Processing id: {}", id)
            val name = name(id)
            names.add(name)
        }.toList() // Operação terminal para coletar o resultado
        return names
    }
}