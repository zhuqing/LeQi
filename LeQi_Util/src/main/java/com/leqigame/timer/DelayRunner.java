/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqigame.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import javafx.application.Platform;

/**
 * 延时提交，延时可叠加
 *
 * @author zhuqing
 */
public class DelayRunner {

    /**
     * 延时1000毫秒
     */
    public final static Long LONG = 1000L;
    /**
     * 延时300毫秒
     */
    public final static Long SHORTEST = 300L;
    /**
     * 延时500毫秒
     */
    public final static Long NORMAL = 500L;
    private final static Map<Object, List<Timer>> timerMap = new ConcurrentHashMap<>();

    /**
     * 取消所有的Timer
     */
    public final static void cancelAllTimer() {
        if (timerMap.isEmpty()) {
            return;
        }
        timerMap.values().stream().forEach(new Consumer<List<Timer>>() {
            @Override
            public void accept(List<Timer> t) {
                if (t == null || t.isEmpty()) {
                    return;
                }
                for (Timer timer : t) {
                    timer.cancel();
                }
            }
        });
    }

    /**
     * 是否已经执行
     *
     * @param node
     * @return
     */
    public synchronized static Boolean isRunning(Object node) {
        return timerMap.containsKey(node);
    }

    /**
     * 开始计时
     *
     * @param node
     * @param millSeconds
     */
    public synchronized static void delayRunner(Object node, Long millSeconds, Runnable runner) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                removeTimer(node, timer, runner);

            }
        }, millSeconds);
        putTimer(node, timer);

    }

    /**
     * 开始计时 , 默认500毫秒
     *
     * @param node
     */
    public synchronized static void delayRunner(Object node, Runnable runner) {
        delayRunner(node, NORMAL, runner);
    }

    /**
     * 计时结束移除计时器
     *
     * @param node
     * @param timer
     */
    private synchronized static void removeTimer(Object node, Timer timer, Runnable runner) {
        List<Timer> timers = timerMap.get(node);
        if (timers == null) {
            return;
        }
        // EventStreams
        timer.cancel();
        timers.remove(timer);
        if (timers.isEmpty()) {
            timerMap.remove(node);
            if (runner != null) {
                if (!Platform.isFxApplicationThread()) {
                    Platform.runLater(() -> runner.run());
                } else {
                    runner.run();
                }
            }
        }
    }

    /**
     * 添加计时器
     *
     * @param node
     * @param timer
     */
    private static void putTimer(Object node, Timer timer) {
        List<Timer> timers = null;
        if (timerMap.containsKey(node)) {
            timers = timerMap.get(node);
        } else {
            timers = new ArrayList<>();
            timerMap.put(node, timers);
        }

        timers.add(timer);
    }
}
