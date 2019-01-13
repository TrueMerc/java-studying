package ru.ryabtsev.se;

import java.util.concurrent.CountDownLatch;

/**
 * Race application (Java Core. Professional. Homework 5.
 */
public class RaceApplication {
    public static final int CARS_COUNT = 4;
    public static final int TUNNEL_CAPACITY = CARS_COUNT / 2;

    public static void main(String[] args) {

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel( TUNNEL_CAPACITY ), new Road(40), new Finish() );

        CountDownLatch countDownLatch = new CountDownLatch( CARS_COUNT );
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; ++i) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), countDownLatch);
        }

        Thread[] carThreads = new Thread[ CARS_COUNT ];


        for (int i = 0; i < cars.length; ++i) {
            carThreads[i] = new Thread(cars[i]);
            carThreads[i].start();
        }

        while( !allCarsReady(cars) ) {
            try {
                Thread.currentThread().sleep( 500 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for( int i = 0; i < cars.length; ++i) {
            countDownLatch.countDown();
        }


        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");


        // Finishing race.
        for (int i = 0; i < cars.length; ++i ) {
            try {
                carThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

    static boolean allCarsReady(Car[] cars) {
        for( int i = 0; i < cars.length; ++i ) {
            if( !cars[i].isReady() ) {
                return false;
            }
        }
        return true;
    }
}





