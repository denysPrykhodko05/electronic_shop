package com.epam.prykhodko.util;

import static java.lang.System.currentTimeMillis;

import java.util.Map;
import java.util.Map.Entry;

public class TimerThread implements Runnable {

    private Map<Long, String> captchaKeys;
    private String time;

    public TimerThread(Map<Long, String> captchaKeys, String time) {
        this.captchaKeys = captchaKeys;
        this.time = time;
    }

    @Override
    public void run() {
        Long previousTime = currentTimeMillis();
        while (true) {
            Long currentTime = currentTimeMillis();
            if (currentTime - previousTime >= Long.valueOf(time) * 1000 && captchaKeys.size() > 0) {
                previousTime = currentTime;
                Long finalPreviousTime = previousTime;
                for (Entry<Long, String> e : captchaKeys.entrySet()) {
                    if (finalPreviousTime - e.getKey() >= 0) {
                        captchaKeys.remove(e.getKey());
                    }
                }
            }
        }
    }
}
