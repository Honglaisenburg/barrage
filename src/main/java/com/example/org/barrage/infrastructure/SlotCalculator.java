package com.example.org.barrage.infrastructure;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class SlotCalculator {

    public static long calculate(long timestamp) {
        long mainPart = timestamp / 1000;
        long fix = timestamp % 1000 == 0 ? 0 : 1;
        return mainPart + fix;
    }

    public static List<Long> calculateQuerySlots(long start, long end) {
        List<Long> relatedSlots = LongStream.rangeClosed(start / 1000, end / 1000).boxed().collect(Collectors.toList());
        return relatedSlots.subList(1, relatedSlots.size());
    }
}
