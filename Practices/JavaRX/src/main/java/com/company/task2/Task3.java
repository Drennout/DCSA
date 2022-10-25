package com.company.task2;

import io.reactivex.rxjava3.core.Observable;

public class Task3 {
    public static void main(String[] args) {
        System.out.println("Task1");
        Observable.range(0, 10)
                .map(i -> Math.round(Math.random() * 100))
                .takeLast(7)
                .subscribe(System.out::println);

        System.out.println("Task2");
        Observable.range(0, 10)
                .map(i -> Math.round(Math.random() * 100))
                .take(5)
                .subscribe(System.out::println);

        System.out.println("Task3");
        Observable.range(1, (int) Math.round(Math.random() * 10))
                .lastElement()
                .subscribe(System.out::println);
    }
}
