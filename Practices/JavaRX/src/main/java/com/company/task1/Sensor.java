package com.company.task1;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

import java.util.Timer;
import java.util.TimerTask;


public class Sensor implements ObservableOnSubscribe {
    private final int min;
    private final int max;
    private final Timer timer;

    public Sensor(int min, int max, Timer timer) {
        this.min = min;
        this.max = max;
        this.timer = timer;
    }

    @Override
    public void subscribe(@NonNull ObservableEmitter emitter) throws Throwable {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                emitter.onNext((int) Math.round(Math.random() * (max - min) + min));
            }
        }, 0, 1000);
    }
}
