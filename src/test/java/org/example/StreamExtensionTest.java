package org.example;

import lombok.experimental.ExtensionMethod;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Stream.iterate;
import static java.util.stream.Stream.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtensionMethod(StreamExtension.class)
class StreamExtensionTest {

    public static final List<String> names = new ArrayList<>();

    @BeforeAll
    static void fillList(){
        names.add("Nick");
        names.add("Ivan");
        names.add("Peter");
        names.add("John");
        names.add("Kate");
        names.add("Antony");
        names.add("Mike");
        names.add("Cris");
        names.add("Netty");
        names.add("Billy");
    }

    @Test
    void oddIndexNames() {
        assertEquals("1. Ivan, 3. John, 5. Antony, 7. Cris, 9. Billy", names.oddIndexNames());

    }

    @Test
    void uppercaseReverseOrderedNames() {
        assertEquals(List.of("PETER", "NICK", "NETTY", "MIKE", "KATE", "JOHN", "IVAN", "CRIS", "BILLY", "ANTONY"),
                names.uppercaseReverseOrderedNames());
    }

    @Test
    void toNumbersString() {
        assertEquals("0, 1, 2, 4, 5", new String[]{"1, 2, 0", "4, 5"}.toNumbersString());
    }

    @Test
    void zip() {
        assertEquals(List.of(1, 2, 3, 4, 5, 6), iterate(1, i -> i + 2).zip(of(2, 4, 6)).toList());
        assertEquals(List.of(2, 1, 4, 3, 6, 5), of(2, 4, 6).zip(iterate(1, i -> i + 2)).toList());
        assertEquals(List.of(0, 0, 1, 1, 2, 2, 3, 3, 4, 4),
                iterate(0, i -> i + 1).zip(iterate(0, i -> i + 1)).limit(10).toList());
    }
}