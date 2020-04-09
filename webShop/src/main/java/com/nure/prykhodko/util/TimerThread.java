package com.nure.prykhodko.util;

import static java.lang.System.currentTimeMillis;

import java.util.Map;
import java.util.Map.Entry;

public class TimerThread implements Runnable {

    private Map<Long, String> captchaKeys;

    public TimerThread(Map<Long, String> captchaKeys) {
        this.captchaKeys = captchaKeys;
    }

    @Override
    public void run() {
        Long currentTime = currentTimeMillis();
        for (Entry<Long, String> e : captchaKeys.entrySet()) {
            if (currentTime - e.getKey() >= 0) {
                captchaKeys.remove(e.getKey());
            }
        }
    }
}