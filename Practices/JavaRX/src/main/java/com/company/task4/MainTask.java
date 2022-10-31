package com.company.task4;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

import java.io.File;
import java.util.ArrayList;

public class MainTask {
    public static void main(String[] args) {
        Generator generator = new Generator();

        QueueObserver queue = new QueueObserver(5, new ArrayList<>());

        Handler handler = new Handler();

        Observable.create(generator).subscribe(queue);

        ConnectableObservable<File> observableQueue = Observable.create(queue).publish();

        observableQueue.subscribe(handler);

        observableQueue.connect();
    }
}
