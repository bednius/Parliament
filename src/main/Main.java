package main;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        /*ArgumentsParser argumentsParser = new ArgumentsParser();
        argumentsParser.parse(args);*/

        Parliament parliament = new Parliament();
        parliament.build(7);
        System.out.println(parliament.average());
        System.out.println("…………………………………………………………………………");
        System.out.print(parliament.italyVisitors());
        System.out.println("…………………………………………………………………………");
        System.out.println(parliament.mostTripsAbroad());
        System.out.println("…………………………………………………………………………");
        System.out.println(parliament.mostExpensiveTrip());
        System.out.println("…………………………………………………………………………");
        System.out.println(parliament.mostTimeAbroad());
        System.out.println("…………………………………………………………………………");
        System.out.println(parliament.getSumOfExpenses("Grzegorz Schetyna"));
        System.out.println("…………………………………………………………………………");
        System.out.println(parliament.getSmallExpenses("Ryszard Kalisz"));
    }
}
