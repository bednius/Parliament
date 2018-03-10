package main;

public class Politician {

    private int id;
    private String name;
    private Trip[] trips;
    private Expense[] expenses;

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    public float getExpenses() {
        float result = 0;
        for (Expense expense : expenses) {
            result += expense.getSum();
        }
        return result;
    }

    public float getSmallExpenses() {
        float result = 0;
        for (Expense expense : expenses) {
            result += expense.getExpense(12);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
