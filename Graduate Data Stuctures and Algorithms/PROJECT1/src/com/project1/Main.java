package com.project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Display welcome message
        welcomeMessage();

        readFile();
    }

    private static void welcomeMessage() {
        System.out.println("Welcome! COP5416 - Project I");
    }

    private static void readFile() throws FileNotFoundException {
        Scanner fileReader = null;

        try {
            File dailyScoresTxt = new File("dailyScores.txt");
            fileReader = new Scanner(dailyScoresTxt);

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found. Check path.");
        } finally {
            fileReader.close();
        }
    }
}
