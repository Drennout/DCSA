package com.company.task4;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;


public class Generator implements ObservableOnSubscribe {
    @Override
    public void subscribe(@NonNull ObservableEmitter emitter) throws Throwable {
        scheduler(emitter, 0);
    }

    private void scheduler(@NonNull ObservableEmitter<File> emitter, int count) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("Generated: File" + count);
                emitter.onNext(new File("File" + count));
                scheduler(emitter, count + 1);
            }
        };

        timer.schedule(task, 1000);
    }
}
