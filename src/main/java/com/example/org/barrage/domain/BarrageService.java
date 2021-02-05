package com.example.org.barrage.domain;

import com.example.org.barrage.infrastructure.SlotCalculator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BarrageService {

    public void createBarrage(String activityId, Barrage barrage) {
        long currentStamp = System.currentTimeMillis();
        long slot = SlotCalculator.calculate(currentStamp);
        String slotKey = String.format("%s_%s", activityId, slot);
        saveToSlot(slotKey, barrage);
    }

    public BarrageList getBarrages(String activityId, Long lastStamp) {
        long currentStamp = System.currentTimeMillis();
        List<Long> querySlots = SlotCalculator.calculateQuerySlots(lastStamp, currentStamp);
        List<Barrage> barrageList = queryBarrages(activityId, querySlots);
        return new BarrageList(currentStamp, barrageList);
    }

    private List<Barrage> queryBarrages(String activityId, List<Long> slots) {
        return slots.stream()
                .map(slot -> String.format("%s_%s", activityId, slot))
                .flatMap(slotKey -> getBarrages(slotKey).stream())
                .collect(Collectors.toList());
    }

    private Collection<Barrage> getBarrages(String slotKey) {
        return new ArrayList<>();
    }

    private void saveToSlot(String slotKey, Barrage barrage) {

    }
}
