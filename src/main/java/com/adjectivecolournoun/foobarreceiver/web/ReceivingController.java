package com.adjectivecolournoun.foobarreceiver.web;

import com.adjectivecolournoun.foobarreceiver.domain.Thing;
import com.adjectivecolournoun.foobarreceiver.repositories.FooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.adjectivecolournoun.foobarreceiver.domain.Thing.makeThing;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
public class ReceivingController {

    private final FooRepository repository;

    @Autowired
    public ReceivingController(FooRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/receive")
    @ResponseStatus(NO_CONTENT)
    void receiveThing(@RequestParam String thing) {

        repository.save(makeThing(thing));
    }

}
