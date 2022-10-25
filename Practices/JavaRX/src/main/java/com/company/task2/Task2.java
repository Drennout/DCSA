package com.company.task2;

import io.reactivex.rxjava3.core.Observable;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Task2 {
    public static void main(String[] args) {
        task3();
    }

    private static void task1() {
        Observable<String> observableStr = Observable.range(1, 10).map(i -> randomLetter());
        Observable<Integer> observableInt = Observable.range(1, 10).map(i -> randomInt());

        Observable
                .zip(
                        observableStr,
                        observableInt,
                        (let, num) -> let + num
                )
                .subscribe(
                        System.out::println
                );
    }

    private static void task2() {
        Observable<Integer> observable1 = Observable.range(1, 10).map(i -> randomInt());
        Observable<Integer> observable2 = Observable.range(1, 10).map(i -> randomInt());

        Observable
                .concat(observable1, observable2)
                .subscribe(System.out::println);
    }

    private static void task3() {
        Observable<Integer> o1 = Observable.just(1, 2, 3);
        Observable<Integer> o2 = Observable.just(4, 5, 6);

        Observable
                .zip(
                        o1,
                        o2,
                        (n1, n2) -> n1 + " " + n2
                )
                .subscribe(
                        System.out::println
                );
    }

    private static String randomLetter() {
        Random r = new Random();
        Character c = (char) (r.nextInt(26) + 'a');
        return Character.toString(c);
    }

    private static int randomInt() {
        return (int) Math.round(Math.random() * 100);
    }
}
