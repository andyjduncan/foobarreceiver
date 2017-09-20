package com.adjectivecolournoun.foobarreceiver.repositories;

import com.adjectivecolournoun.foobarreceiver.domain.Foo;
import com.adjectivecolournoun.foobarreceiver.domain.Thing;
import org.springframework.data.repository.CrudRepository;

public interface ThingRepository extends CrudRepository<Thing, Integer> {
}
