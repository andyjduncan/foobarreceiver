package com.adjectivecolournoun.foobarreceiver.domain;

import java.util.Objects;
import java.util.regex.Matcher;

public class Bar extends Thing {

    private final String barId;

    private final String bazId;

    private Bar(Integer id, String barId, String bazId) {
        super(id);
        this.barId = barId;
        this.bazId = bazId;
    }

    static Bar makeBar(String thing) {
        String regex = "bar:(\\d{4}):(\\p{XDigit}{3}):(\\p{XDigit}{3})";

        Matcher matcher = makeMatcher(thing, regex);

        Integer thingId = Integer.valueOf(matcher.group(1));

        String barId = matcher.group(2);

        String bazId = matcher.group(3);

        return new Bar(thingId, barId, bazId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bar bar = (Bar) o;
        return Objects.equals(id, bar.id) &&
                Objects.equals(barId, bar.barId) &&
                Objects.equals(bazId, bar.bazId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, barId, bazId);
    }

    @Override
    public String toString() {
        return "Bar{" +
                "id=" + id +
                ", barId='" + barId + '\'' +
                ", bazId='" + bazId + '\'' +
                '}';
    }
}
