package com.example.org.barrage.domain;

import java.util.List;

public class BarrageList {
    Long stamp;
    List<Barrage> data;

    public BarrageList() {
    }

    public Long getStamp() {
        return stamp;
    }

    public void setStamp(Long stamp) {
        this.stamp = stamp;
    }

    public List<Barrage> getData() {
        return data;
    }

    public void setData(List<Barrage> data) {
        this.data = data;
    }

    public BarrageList(long currentStamp, List<Barrage> barrageList) {
        this.stamp = currentStamp;
        this.data = barrageList;
    }
}
