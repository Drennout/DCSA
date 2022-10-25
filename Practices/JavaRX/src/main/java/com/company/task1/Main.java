package com.company.task1;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int MIN_T = 15;
    private static final int MAX_T = 30;
    private static final int MIN_CO2 = 30;
    private static final int MAX_CO2 = 100;

    private static final int NORM_T = 20;
    private static final int NORM_CO2 = 70;

    public static void main(String[] args) {
        Sensor tempSensor = new Sensor(MIN_T, MAX_T, new Timer());
        Sensor co2Sensor = new Sensor(MIN_CO2, MAX_CO2, new Timer());

        Observable<Integer> observableCO2 = Observable.create(co2Sensor);
        Observable<Integer> observableTemp = Observable.create(tempSensor);

        observableCO2.join(
                        observableTemp,
                        i -> Observable.timer(100, TimeUnit.MILLISECONDS),
                        i -> Observable.timer(100, TimeUnit.MILLISECONDS),
                        Data::new)
                .subscribe(new Observer<>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Data data) {
                        String message = "";
                        int t = data.getT();
                        int co2 = data.getCo2();

                        if (t > NORM_T && co2 > NORM_CO2) {
                            message = "«ALARM!!!";
                        } else if (t > NORM_T || co2 > NORM_CO2) {
                            message = "Warning!";
                        }

                        System.out.println("Temperature: " + t + ", CO2: " + co2 + " " + message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });

    }
}