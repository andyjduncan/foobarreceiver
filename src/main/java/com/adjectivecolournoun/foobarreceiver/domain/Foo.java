package com.adjectivecolournoun.foobarreceiver.domain;

import java.util.Objects;
import java.util.regex.Matcher;

public class Foo extends Thing {

    private final String fooId;

    private Foo(Integer id, String fooId) {
        super(id);
        this.fooId = fooId;
    }

    static Foo makeFoo(String thing) {
        String regex = "foo:(\\d{4}):(\\p{XDigit}{4})";

        Matcher matcher = makeMatcher(thing, regex);

        Integer thingId = Integer.valueOf(matcher.group(1));

        String fooId = matcher.group(2);

        return new Foo(thingId, fooId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foo foo = (Foo) o;
        return Objects.equals(id, foo.id) &&
                Objects.equals(fooId, foo.fooId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fooId);
    }

    @Override
    public String toString() {
        return "Foo{" +
                "id=" + id +
                ", fooId='" + fooId + '\'' +
                '}';
    }
}
