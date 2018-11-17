package ru.ryabtsev.se;

import java.util.concurrent.Semaphore;

/**
 * Tunnel stage. Some cars may wait while tunnel will be free for ride.
 */
public class Tunnel extends Stage {

    Semaphore semaphore;

    public Tunnel(int carsNumber) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.semaphore = new Semaphore( carsNumber );
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                if( semaphore.hasQueuedThreads() ) {
                    System.out.println("ОЖИДАЕТ ОСВОБОЖДЕНИЯ ТОННЕЛЯ " + semaphore.getQueueLength() + " УЧАСТНИКОВ");
                }
                semaphore.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}