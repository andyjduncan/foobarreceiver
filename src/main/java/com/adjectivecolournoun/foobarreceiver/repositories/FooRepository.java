package com.adjectivecolournoun.foobarreceiver.repositories;

import com.adjectivecolournoun.foobarreceiver.domain.Thing;
import org.springframework.data.repository.CrudRepository;

public interface FooRepository extends CrudRepository<Thing, Integer> {
}
