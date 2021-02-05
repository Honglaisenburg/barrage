package com.example.org.barrage.domain;

import com.example.org.barrage.infrastructure.SlotCalculator;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BarrageService {

    @Autowired
    private RedissonClient redissonClient;

    public void createBarrage(String activityId, Barrage barrage) {
        long currentStamp = System.currentTimeMillis();
        long slot = SlotCalculator.calculate(currentStamp);
        String slotKey = String.format("%s_%s", activityId, slot);
        saveToSlot(slotKey, barrage);
    }

    public BarrageList getBarrages(String activityId, Long lastStamp) {
        long currentStamp = System.currentTimeMillis();
        long validLastStamp = checkNull(lastStamp, currentStamp);
        List<Long> querySlots = SlotCalculator.calculateQuerySlots(validLastStamp, currentStamp);
        List<Barrage> barrageList = queryBarrages(activityId, querySlots);
        return new BarrageList(currentStamp, barrageList);
    }

    private long checkNull(Long last, Long current) {
        return Objects.isNull(last) ? current - 3000 : last;
    }

    private List<Barrage> queryBarrages(String activityId, List<Long> slots) {
        return slots.stream()
                .map(slot -> String.format("%s_%s", activityId, slot))
                .flatMap(slotKey -> getBarrages(slotKey).stream())
                .collect(Collectors.toList());
    }

    private Collection<Barrage> getBarrages(String slotKey) {
        return redissonClient.getSet(slotKey).stream()
                .map(Barrage::format)
                .collect(Collectors.toList());
    }

    private void saveToSlot(String slotKey, Barrage barrage) {
        redissonClient.getSet(slotKey).tryAdd(barrage.toJson());
    }
}
