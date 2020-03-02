package com.epam.prykhodko.util;

import static java.lang.System.currentTimeMillis;

import java.util.Map;

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
            if (currentTime - previousTime >= Long.valueOf(time)) {
                previousTime = currentTime;
                Long finalPreviousTime = previousTime;
                captchaKeys.entrySet().stream().filter(e -> finalPreviousTime - e.getKey() > 0).forEach(captchaKeys::remove);
            }
        }
    }
}
