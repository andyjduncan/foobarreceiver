package com.adjectivecolournoun.foobarreceiver.web;

import com.adjectivecolournoun.foobarreceiver.domain.Thing;
import com.adjectivecolournoun.foobarreceiver.repositories.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
public class ReceivingController {

    private final ThingRepository repository;

    @Autowired
    public ReceivingController(ThingRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/receive")
    @ResponseStatus(NO_CONTENT)
    void receive(@RequestParam String thing) {

        repository.save(Thing.makeThing(thing));
    }

}
