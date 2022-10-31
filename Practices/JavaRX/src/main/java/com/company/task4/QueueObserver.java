package com.company.task4;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QueueObserver implements ObservableOnSubscribe<File>, Observer<File> {
    private final int length;
    private final List<File> queue;

    public QueueObserver(int length, List<File> queue) {
        this.length = length;
        this.queue = queue;
    }


    @Override
    public void subscribe(@NonNull ObservableEmitter<File> emitter) throws Throwable {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                if (!queue.isEmpty()) {
                    emitter.onNext(queue.remove(0));
                }
            }
        };

        timer.schedule(task, 0, 10);
    }

    @Override
    public void onNext(@NonNull File file) {
        if (queue.size() < length) {
            queue.add(file);
        }
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }


    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }

}
