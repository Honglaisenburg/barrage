package com.example.org.barrage;

import com.example.org.barrage.infrastructure.SlotCalculator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlotCalculatorTest {

    @Test
    public void should_calculate_save_slot() {
        assertEquals(1612527450L, SlotCalculator.calculate(1612527449313L));
        assertEquals(1612527449L, SlotCalculator.calculate(1612527449000L));
        assertEquals(1612527449L, SlotCalculator.calculate(1612527448003L));
    }

    @Test
    public void should_calculate_query_slot() {
        List<Long> querySlots = SlotCalculator.calculateQuerySlots(1612527449313L, 1612527450313L);
        assertEquals(1, querySlots.size());
        assertEquals(1612527450L, querySlots.get(0));

        List<Long> longSlots = SlotCalculator.calculateQuerySlots(1612527440313L, 1612527450313L);
        assertEquals(10, longSlots.size());
        assertEquals(1612527441L, longSlots.get(0));
        assertEquals(1612527442L, longSlots.get(1));
        assertEquals(1612527443L, longSlots.get(2));
        assertEquals(1612527444L, longSlots.get(3));
        assertEquals(1612527445L, longSlots.get(4));
        assertEquals(1612527446L, longSlots.get(5));
        assertEquals(1612527447L, longSlots.get(6));
        assertEquals(1612527448L, longSlots.get(7));
        assertEquals(1612527449L, longSlots.get(8));
        assertEquals(1612527450L, longSlots.get(9));
    }

    @Test
    public void should_calculate_int_query_slots() {
        List<Long> intSlots = SlotCalculator.calculateQuerySlots(1612527441000L, 1612527442000L);
        assertEquals(1, intSlots.size());
        assertEquals(1612527442L, intSlots.get(0));
    }
}
