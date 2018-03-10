package main;

public class PoliticianDetails {
    private Trip[] trips;
    private Expense[] expenses;

    public PoliticianDetails(Expense[] expenses) {
        this.expenses = expenses;
    }

    public Trip[] getTrips() {
        return trips;
    }

    public Expense[] getExpenses() {
        return expenses;
    }

    public void setTrips(Trip[] trips) {
        this.trips = trips;
    }
}
