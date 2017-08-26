/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqi.thread;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import javafx.application.Platform;

/**
 *
 * @author zhuqing
 */
public class TimerUtil {

    private static final Map<TimerTask, Timer> timerMap = new ConcurrentHashMap<>();

    /**
     * millSeconds 毫秒后在Application线程下执行
     *
     * @param runnable
     * @param millSeconds
     */
    public static synchronized void runLaterInApplicationThread(Runnable runnable, Long millSeconds) {
        createTimer(new TimerTask() {
            @Override
            public void run() {
                if (timerMap.containsKey(this)) {
                    timerMap.get(this).cancel();
                }
                if (!Platform.isFxApplicationThread()) {
                    Platform.runLater(runnable);

                } else {
                    runnable.run();
                }

            }
        }, millSeconds);
    }

    /**
     * millSeconds 毫秒后执行
     *
     * @param runnable
     * @param millSeconds
     */
    public static synchronized void runLater(Runnable runnable, Long millSeconds) {
        createTimer(new TimerTask() {
            @Override
            public void run() {
                if (timerMap.containsKey(this)) {
                    timerMap.get(this).cancel();
                }
                runnable.run();
            }
        }, millSeconds);
    }

    /**
     * 创建Timer
     *
     * @param timerTask
     * @param millSeconds
     * @return
     */
    public static synchronized Timer createTimer(TimerTask timerTask, Long millSeconds) {
        Timer timer = new Timer();
        timer.schedule(timerTask, millSeconds);
        timerMap.put(timerTask, timer);
        return timer;
    }
}
