package org.example;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public final class StreamExtension {

    private StreamExtension() {
    }

    public static String oddIndexNames(List<String> names) {
        return IntStream.range(0, names.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(i -> i + ". " + names.get(i))
                .collect(joining(", "));
    }

    public static List<String> uppercaseReverseOrderedNames(List<String> names) {
        return names.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    public static String toNumbersString(String[] strings) {
        return Arrays.stream(strings)
                .map(s -> s.split(","))
                .flatMap(Arrays::stream)
                .map(String::trim)
                //.filter(s -> s.chars().allMatch(Character::isDigit))
                .sorted()
                .collect(joining(", "));
    }

    public static Stream<Long> getRandomNumbers(long a, long c, long m) {
        return LongStream.iterate(System.nanoTime(), (x) -> (a * x + c) % m).boxed();
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Spliterator<T> secondSpliterator = second.sequential().spliterator();
        Iterator<T> secondIterator = Spliterators.iterator(secondSpliterator);
        Stream<T> resultStream = first
                .sequential()
                .flatMap(x -> secondIterator.hasNext() ?
                        Stream.of(x, secondIterator.next()) : Stream.of());

        long size = secondSpliterator.getExactSizeIfKnown();
        return size == -1 ? resultStream : resultStream.limit(size * 2);

    }
}
