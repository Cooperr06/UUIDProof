package de.cooperr.uuidproof;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

    public static void main(String[] args) {

        final AtomicBoolean running = new AtomicBoolean(true);
        final AtomicLong count = new AtomicLong();

        final AtomicReference<UUID> firstUUIDPrint = new AtomicReference<>();
        final AtomicReference<UUID> secondUUIDPrint = new AtomicReference<>();

        final Timer timer = new Timer();

        new Thread(() -> {
            while (running.get()) {

                count.getAndIncrement();

                UUID firstUUID = UUID.randomUUID();
                UUID secondUUID = UUID.randomUUID();

                if (firstUUID.equals(secondUUID)) {

                    System.out.println("Try: " + count);
                    System.out.println("Result: TRUE\n");
                    System.out.println("UUID_1: " + firstUUID.toString());
                    System.out.println("UUID_2: " + secondUUID.toString() + "\n");
                    System.out.println("---------- IT'S DONE ----------");

                    running.set(false);

                    timer.cancel();
                } else {
                    firstUUIDPrint.set(firstUUID);
                    secondUUIDPrint.set(secondUUID);
                }
            }
        }).start();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Try: " + count);
                System.out.println("Result: FALSE\n");
                System.out.println("UUID_1: " + firstUUIDPrint.toString());
                System.out.println("UUID_2: " + secondUUIDPrint.toString() + "\n\n--------------------\n");
            }
        }, 0, 10000);
    }
}
