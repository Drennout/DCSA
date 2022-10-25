package com.company.task2;

import io.reactivex.rxjava3.core.Observable;

public class Task1 {
    public static void main(String[] args) {
        System.out.println("task1");
        Observable.range(0, 10)
                .map(i -> Math.round(Math.random() * 100))
                .map(num -> Math.pow(num, 2))
                .subscribe(System.out::println);

        System.out.println("task2");
        Observable.range(0, 10)
                .map(i -> Math.round(Math.random() * 1000))
                .filter(num -> num > 500)
                .subscribe(System.out::println);

        System.out.println("task3");
        Observable.range(1, (int) Math.round(Math.random() * 1000))
                .count()
                .subscribe(System.out::println);
    }
}
