package de.cooperr.uuidproof;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

                    final File file = new File("src/main/resources/", "Result.txt");

                    try {
                        final FileOutputStream outputStream = new FileOutputStream(file);
                        outputStream.write(("Try: " + count + "\nResult: TRUE\nUUID_1: " + firstUUID.toString() + "\nUUID_2: " + secondUUID.toString()).getBytes());
                        outputStream.flush();
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

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
