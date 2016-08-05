package com.adjectivecolournoun.foobarreceiver.domain;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class InvalidThingException extends RuntimeException {
    public InvalidThingException(String thing) {
        super(thing);
    }
}
