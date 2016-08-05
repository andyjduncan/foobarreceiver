package com.adjectivecolournoun.foobarreceiver.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Thing {
    protected final Integer id;

    public Thing(Integer id) {
        this.id = id;
    }

    public static Thing makeThing(String thing) {
        if (thing.startsWith("foo")) {
            return Foo.makeFoo(thing);
        } else if (thing.startsWith("bar")) {
            return Bar.makeBar(thing);
        } else {
            throw new InvalidThingException(thing);
        }
    }

    protected static Matcher makeMatcher(String thing, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(thing);

        if (!matcher.matches()) {
            throw new InvalidThingException(thing);
        }
        return matcher;
    }
}
