package com.adjectivecolournoun.foobarreceiver

import com.adjectivecolournoun.foobarreceiver.domain.Bar
import com.adjectivecolournoun.foobarreceiver.domain.Foo
import com.adjectivecolournoun.foobarreceiver.repositories.FooRepository
import com.adjectivecolournoun.foobarreceiver.web.ReceivingController
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


class TestThingReceiving extends Specification {

    private final repository = Mock(FooRepository)

    private final controller = new ReceivingController(repository)
    private final mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    private final random = new Random()
    private final thingId = (1..4).collect({ random.nextInt(10) }).join('')
    private final fooId = (1..4).collect({ Integer.toHexString(random.nextInt(16)) }).join('')
    private final barId = (1..3).collect({ Integer.toHexString(random.nextInt(16)) }).join('')
    private final bazId = (1..3).collect({ Integer.toHexString(random.nextInt(16)) }).join('')

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

    void 'returns a bad request response on an invalid thing id on a foo'() {
        given:
        def thing = "foo:abdc:$fooId"

        when:
        def response = mockMvc.perform(post("/receive?thing=$thing"))

        then:
        response.andExpect(status().isBadRequest())
    }

    void 'returns a bad request response on an invalid foo id'() {
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


    void 'returns a bad request response on an invalid thing id on a bar'() {
        given:
        def thing = "bar:abcd:$barId:$bazId"

        when:
        def response = mockMvc.perform(post("/receive?thing=$thing"))

        then:
        response.andExpect(status().isBadRequest())
    }

    void 'returns a bad request response on an invalid bar id'() {
        given:
        def thing = "bar:$thingId:xyz:$bazId"

        when:
        def response = mockMvc.perform(post("/receive?thing=$thing"))

        then:
        response.andExpect(status().isBadRequest())
    }

    void 'returns a bad request response on an invalid baz id'() {
        given:
        def thing = "bar:$thingId:$barId:xyz"

        when:
        def response = mockMvc.perform(post("/receive?thing=$thing"))

        then:
        response.andExpect(status().isBadRequest())
    }

    void 'returns a bad request for an unknown type of thing'() {
        given:
        def thing = "fff:$thingId"

        when:
        def response = mockMvc.perform(post("/receive?thing=$thing"))

        then:
        response.andExpect(status().isBadRequest())
    }
}