package main;

import java.io.IOException;
import java.util.IllegalFormatCodePointException;

/**
 * Created by kreska on 11.01.17.
 */
public class ArgumentsParser {
    public void parse(String[] args) {
        try {
            if (args.length < 2 || args.length > 5)
                throw new IllegalArgumentException("Wrong number of arguments");
            if (!args[0].equals("7") && !args[0].equals("8"))
                throw new IllegalArgumentException("Wrong number of cadence. Type 7 or 8");

            String name = "";

            Parliament parliament = new Parliament();

            for (int i = 2; i < args.length - 1; i++)
                name += args[i] + " ";
            name += args[args.length - 1];

            switch (args[1]) {
                case "-avg": {
                    if(args.length != 2) throw new IllegalArgumentException("Too many arguments");
                    parliament.build(Integer.parseInt(args[0]));
                    System.out.println(parliament.average());
                    break;
                }
                case "-expenses": {
                    parliament.build(Integer.parseInt(args[0]));
                    System.out.println(parliament.getSumOfExpenses(name));
                    break;
                }
                case "-small": {
                    parliament.build(Integer.parseInt(args[0]));
                    System.out.println(parliament.getSmallExpenses(name));
                    break;
                }
                case "-tripscount": {
                    if(args.length != 2) throw new IllegalArgumentException("Too many arguments");
                    parliament.build(Integer.parseInt(args[0]));
                    System.out.println(parliament.mostTripsAbroad());
                    break;
                }
                case "-tripstime": {
                    if(args.length != 2) throw new IllegalArgumentException("Too many arguments");
                    parliament.build(Integer.parseInt(args[0]));
                    System.out.println(parliament.mostTimeAbroad());
                    break;
                }
                case "-tripscost": {
                    if(args.length != 2) throw new IllegalArgumentException("Too many arguments");
                    parliament.build(Integer.parseInt(args[0]));
                    System.out.println(parliament.mostExpensiveTrip());
                    break;
                }
                case "-italy": {
                    if(args.length != 2) throw new IllegalArgumentException("Too many arguments");
                    parliament.build(Integer.parseInt(args[0]));
                    System.out.println(parliament.italyVisitors());
                    break;
                }
                default:
                    throw new IllegalArgumentException("\nWrong second argument.\nType one of:\n-avg -expenses -small -tripscount - tripstime -tripscost -italy");
            }

        } catch (IllegalArgumentException e) {
            System.err.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
