package com.example.full_monolith.sonarlint;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;

class SonarTest {

    @Test
    void isSameNumberTest() {
        isSameNumberValue(new AtomicLong(1), new AtomicLong(2));
        assert new AtomicLong(1).get() == new AtomicLong(2).get();
    }

    void isSameNumberValue(AtomicLong al, AtomicLong al2) {
        if (al.equals(al2)) {
            System.out.println("Same value");
        }
    }


}
