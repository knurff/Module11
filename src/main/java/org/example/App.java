package org.example;

import lombok.experimental.ExtensionMethod;

import static java.util.stream.Stream.iterate;
import static java.util.stream.Stream.of;

@ExtensionMethod(StreamExtension.class)
public class App {
    public static void main(String[] args) {
        System.out.println(StreamExtension.getRandomNumbers(
                        25_214_903_917L, 11L, (long) Math.pow(2, 48))
                .limit(10)
                .toList());
        System.out.println(iterate(1, i -> i + 2).zip(of(2, 4, 6)).toList());
    }


}
