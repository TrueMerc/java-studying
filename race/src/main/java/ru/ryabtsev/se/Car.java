package ru.ryabtsev.se;

import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private Race race;

    private final CountDownLatch countDownLatch;

    private int speed;

    private String name;

    private boolean ready;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isReady() {
        return ready;
    }

    public Car(Race race, int speed, CountDownLatch countDownLatch) {
        this.race = race;
        this.speed = speed;
        this.countDownLatch = countDownLatch;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.ready = false;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            ready = true;
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        countDownLatch.countDown();
    }
}