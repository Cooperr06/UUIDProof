package de.cooperr.uuidproof;

import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        boolean running = true;
        long count = 0;

        while (running) {

            count++;

            UUID firstUUID = UUID.randomUUID();
            UUID secondUUID = UUID.randomUUID();

            if (firstUUID.toString().equals(secondUUID.toString())) {

                System.out.println("Try: " + count);
                System.out.println("Result: TRUE\n");
                System.out.println("UUID_1: " + firstUUID.toString());
                System.out.println("UUID_2: " + secondUUID.toString() + "\n");
                System.out.println("---------- IT'S DONE ----------");

                running = false;

            } else {

                System.out.println("Try: " + count);
                System.out.println("Result: FALSE\n");
                System.out.println("UUID_1: " + firstUUID.toString());
                System.out.println("UUID_2: " + secondUUID.toString() + "\n\n----------\n");

            }
        }
    }
}
