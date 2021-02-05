package com.example.org.barrage.domain;

import java.util.List;

public class BarrageList {
    Long stamp;
    List<Barrage> data;

    public BarrageList(long currentStamp, List<Barrage> barrageList) {
        this.stamp = currentStamp;
        this.data = barrageList;
    }
}
