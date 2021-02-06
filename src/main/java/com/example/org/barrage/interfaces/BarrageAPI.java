package com.example.org.barrage.interfaces;

import com.example.org.barrage.domain.Barrage;
import com.example.org.barrage.domain.BarrageList;
import com.example.org.barrage.domain.BarrageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/barrage")
public class BarrageAPI {

    @Autowired
    private BarrageService barrageService;

    @PostMapping
    public void createBarrage(
            @RequestParam(value = "activityId", required = false, defaultValue = "testActivity") String activityId,
            @RequestBody Barrage barrage
    ) {
        barrageService.createBarrage(activityId, barrage);
    }

    @GetMapping
    public BarrageList getBarrages(
            @RequestParam(value = "activityId", required = false, defaultValue = "testActivity") String activityId,
            @RequestParam(value = "lastStamp", required = false) Long lastStamp
    ) {
        return barrageService.getBarrages(activityId, lastStamp);
    }
}
