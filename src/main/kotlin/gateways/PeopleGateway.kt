package org.example.gateways

import org.example.gateways.clients.PeopleClient
import org.springframework.stereotype.Component

@Component
class PeopleGateway(private val peopleClient: PeopleClient ) {

    fun findPeople(id : Long) = peopleClient.findPeople(id)

    suspend fun findPeopleC(id : Long) = peopleClient.findPeopleC(id)
}