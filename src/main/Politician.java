package main;

import java.security.PublicKey;

/**
 * Created by kreska on 07.01.17.
 */
public class Politician {

    private int id;
    private String name;
    //private String genitive;
    private Trip[] trips;
    private Expense[] expenses;

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    public float getExpenses() {
        float result = 0;
        for (int i = 0; i < expenses.length; i++) {
            result += expenses[i].getSum();
        }
        return result;
    }

    public float getSmallExpenses() {
        float result = 0;
        for (int i = 0; i < expenses.length; i++) {
            result += expenses[i].getExpense(12);
        }
        return result;
    }

    public int getId() {
        return id;
    }

    public Trip[] getTrips() {
        return trips;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTrips(Trip[] trips) {
        this.trips = trips;
    }

    public void setExpenses(Expense[] expenses) {
        this.expenses = expenses;
    }

    /*public String getGenitive() {
        return genitive;
    }

    public void setGenitive(String genitive) {
        this.genitive = genitive;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
