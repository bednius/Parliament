package main;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Parliament {
    private Map<String, Politician> politiciansMap = new ConcurrentHashMap<>();
    private String nextUrl = "https://api-v3.mojepanstwo.pl/dane/poslowie.json";
    private String lastUrl;

    private void makePoliticians(int cadenceNo) throws IOException {
        this.nextUrl += "?conditions[poslowie.kadencja]=" + cadenceNo;
        QuestionInterpreter questionInterpreter = new QuestionInterpreter();

        do {
            Data data = questionInterpreter.getData(nextUrl);
            this.lastUrl = data.getLastUrl();

            Politician[] politicians = data.getPoliticians();
            for (Politician politician : politicians) {
                politiciansMap.put(politician.getName(), politician);
            }
            this.nextUrl = data.getNextUrl();
        } while (!lastUrl.equals(nextUrl));

        Data data = questionInterpreter.getData(nextUrl);

        Politician[] politicians = data.getPoliticians();

        for (Politician politician : politicians) {
            politiciansMap.put(politician.getName(), politician);
        }
    }

    private void makePoliticiansDetails() throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for (String key : politiciansMap.keySet()) {
            QuestionInterpreter questionInterpreter = new QuestionInterpreter();
            Politician politician = politiciansMap.get(key);
            questionInterpreter.setPolitician(politician);
            Runnable worker = questionInterpreter;
            executorService.execute(worker);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException ignored) {
        }
    }

    public void build(int cadenceNo) throws IOException {
        makePoliticians(cadenceNo);
        makePoliticiansDetails();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String key : politiciansMap.keySet()) {
            result.append(politiciansMap.get(key).getName()).append(" ").append(getSmallExpenses(key)).append(" ").append(getSumOfExpenses(key)).append("\n");
        }
        return result.toString();
    }

    public float getSumOfExpenses(String key) {
        politicianExists(key);
        return politiciansMap.get(key).getExpenses();
    }

    public float getSmallExpenses(String key) {
        politicianExists(key);
        return politiciansMap.get(key).getSmallExpenses();
    }

    public float average() {
        float result = 0;
        for (String key : politiciansMap.keySet()) {
            result += politiciansMap.get(key).getExpenses();
        }
        return result / politiciansMap.size();
    }

    public String mostTripsAbroad() {
        Politician resultPolitician = null;
        int max = 0;
        for (String key : politiciansMap.keySet()) {
            Politician politician = politiciansMap.get(key);
            Trip[] trips = politician.getTrips();
            int mult = 0;
            if (trips != null) {
                for (Trip trip : trips) {
                    if (trip.getCountry().equals("Polska"))
                        mult++;
                }
                if (trips.length - mult > max) {
                    resultPolitician = politician;
                    max = trips.length - mult;
                }
            }
        }
        return resultPolitician.getName();
    }

    public String mostTimeAbroad() {
        Politician resultPolitician = null;
        int max = 0;
        for (String key : politiciansMap.keySet()) {
            Politician politician = politiciansMap.get(key);
            Trip[] trips = politician.getTrips();
            int duration = 0;
            if (trips != null) {
                for (Trip trip : trips) {
                    if (!trip.getCountry().equals("Polska")) {
                        duration += trip.getDuration();
                    }
                }
            }
            if (duration > max) {
                max = duration;
                resultPolitician = politician;
            }
        }
        return resultPolitician.getName();
    }

    public String mostExpensiveTrip() {
        Politician politicianResult = null;
        float max = 0;
        float prevMax = 0;
        for (String key : politiciansMap.keySet()) {
            Politician politician = politiciansMap.get(key);
            Trip[] trips = politician.getTrips();
            if (trips != null) {
                for (Trip trip : trips) {
                    if (!trip.getCountry().equals("Polska")) {
                        max = max(max, trip.getCost());
                    }
                }
            }
            if (max > prevMax) {
                politicianResult = politician;
                prevMax = max;
            }
        }
        return politicianResult.getName();
    }

    public String italyVisitors() {
        StringBuilder result = new StringBuilder();
        for (String key : politiciansMap.keySet()) {
            Trip[] trips = politiciansMap.get(key).getTrips();
            if (trips != null) {
                for (Trip trip : trips) {
                    if (trip.getCountry().equals("Włochy")) {
                        result.append(politiciansMap.get(key).getName())
                                .append("\n");
                        break;
                    }
                }
            }
        }
        return result.toString();
    }

    private void politicianExists(String name) {
        if (!politiciansMap.containsKey(name))
            throw new IllegalArgumentException("This politician doesn't exists");
    }

    private float max(float a, float b) {
        if (a > b) return a;
        return b;
    }
}
