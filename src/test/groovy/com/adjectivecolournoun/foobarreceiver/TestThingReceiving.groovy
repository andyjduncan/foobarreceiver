package com.adjectivecolournoun.foobarreceiver

import com.adjectivecolournoun.foobarreceiver.domain.Bar
import com.adjectivecolournoun.foobarreceiver.domain.Foo
import com.adjectivecolournoun.foobarreceiver.repositories.ThingRepository
import com.adjectivecolournoun.foobarreceiver.web.ReceivingController
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class TestThingReceiving extends Specification {
    private final repository = Mock(ThingRepository)
    private final random = new Random()
    private final thingId = (1..4).collect({ this.random.nextInt(10) }).join('')
    private final fooId = (1..4).collect({ Integer.toHexString(this.random.nextInt(16)) }).join('')
    private final barId = (1..3).collect({ Integer.toHexString(this.random.nextInt(16)) }).join('')
    private final bazId = (1..3).collect({ Integer.toHexString(this.random.nextInt(16)) }).join('')
    private final controller = new ReceivingController(repository)
    private final mockMvc = standaloneSetup(this.controller).build()

    void 'receives a foo'() {
        given:
        def thing = "foo:$thingId:$fooId"


        when:
        def response = mockMvc.perform(post("/receive?thing=$thing"))

        then:
        response.andExpect(status().isNoContent())
    }

    void 'stores a foo'() {
        given:
        def thing = "foo:$thingId:$fooId"

        when:
        mockMvc.perform(post("/receive?thing=$thing"))

        then:
        1 * repository.save(new Foo(thingId.toInteger(), fooId))
    }

    void 'returns a bad request if thing id is invalid for a foo'() {
        given:
        def thing = "foo:abcd:$fooId"

        when:
        def response = mockMvc.perform(post("/receive?thing=$thing"))

        then:
        response.andExpect(status().isBadRequest())
    }


    void 'returns a bad request if foo id is invalid for a foo'() {
        given:
        def thing = "foo:$thingId:wxyz"

        when:
        def response = mockMvc.perform(post("/receive?thing=$thing"))

        then:
        response.andExpect(status().isBadRequest())
    }


    void 'receives a bar'() {
        given:
        def thing = "bar:$thingId:$barId:$bazId"


        when:
        def response = mockMvc.perform(post("/receive?thing=$thing"))

        then:
        response.andExpect(status().isNoContent())
    }

    void 'stores a bar'() {
        given:
        def thing = "bar:$thingId:$barId:$bazId"

        when:
        mockMvc.perform(post("/receive?thing=$thing"))

        then:
        1 * repository.save(new Bar(thingId.toInteger(), barId, bazId))
    }

    void 'returns a bad request if thing id is invalid for a bar'() {
        given:
        def thing = "bar:abcd:$barId:$bazId"

        when:
        def response = mockMvc.perform(post("/receive?thing=$thing"))

        then:
        response.andExpect(status().isBadRequest())
    }

    void 'returns a bad request if bar id is invalid for a bar'() {
        given:
        def thing = "bar:$thingId:xyz:$bazId"

        when:
        def response = mockMvc.perform(post("/receive?thing=$thing"))

        then:
        response.andExpect(status().isBadRequest())
    }

    void 'returns a bad request if baz id is invalid for a bar'() {
        given:
        def thing = "bar:$thingId:$barId:xyz"

        when:
        def response = mockMvc.perform(post("/receive?thing=$thing"))

        then:
        response.andExpect(status().isBadRequest())
    }

    void 'returns a bad request if the thing type is unknown'() {
        given:
        def thing = "fff:$thingId"

        when:
        def response = mockMvc.perform(post("/receive?thing=$thing"))

        then:
        response.andExpect(status().isBadRequest())
    }

}