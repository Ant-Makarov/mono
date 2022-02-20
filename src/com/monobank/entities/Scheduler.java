package com.monobank.entities;

import java.util.Random;
import java.util.Timer;

public class Scheduler {
    private Boolean decision = false;

    private boolean setDecision() {
        int a = 1;
        int b = 6;
        int result = a + (int) (Math.random() * b);
        if (result == 1) {
            decision = true;
        }
        return decision;
    }

    public Boolean getDecision() {
        return decision;
    }
}
