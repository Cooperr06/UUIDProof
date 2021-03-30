package de.cooperr.uuidproof;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class Main {

    public static void main(String[] args) throws IOException {

        boolean running = true;
        long count = 0;

        while (running) {

            count++;

            UUID firstUUID = UUID.randomUUID();
            UUID secondUUID = UUID.randomUUID();

            if (firstUUID.equals(secondUUID)) {

                System.out.println("Try: " + count);
                System.out.println("Result: TRUE\n");
                System.out.println("UUID_1: " + firstUUID.toString());
                System.out.println("UUID_2: " + secondUUID.toString() + "\n");
                System.out.println("---------- IT'S DONE ----------");

                running = false;

                final File file = new File("src/main/resources/", "Result.txt");

                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(("Try: " + count + "\nResult: TRUE\nUUID_1: " + firstUUID.toString() + "\nUUID_2: " + secondUUID.toString()).getBytes());
                outputStream.flush();
                outputStream.close();

            } else {

                System.out.println("Try: " + count);
                System.out.println("Result: FALSE\n");
                System.out.println("UUID_1: " + firstUUID.toString());
                System.out.println("UUID_2: " + secondUUID.toString() + "\n\n--------------------\n");

            }
        }
    }
}
