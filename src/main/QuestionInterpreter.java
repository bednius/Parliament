package main;

import com.google.gson.GsonBuilder;
import deserializers.DataDeserializer;
import deserializers.PoliticianDeserializer;
import deserializers.PoliticianDetailsDeserializer;
import deserializers.TripDeserializer;

import java.io.IOException;

/**
 * Created by kreska on 09.01.17.
 */
public class QuestionInterpreter implements Runnable{

    Politician politician;

    public Data getData(String url) throws IOException {
        RequestsMaker requestsMaker = new RequestsMaker();
        String jsonString = requestsMaker.getJsonString(url);
        Data data = new GsonBuilder()
                .registerTypeAdapter(Data.class, new DataDeserializer())
                .registerTypeAdapter(Politician.class, new PoliticianDeserializer())
                .create()
                .fromJson(jsonString, Data.class);
        return data;
    }

    public void setPolitician(Politician politician) {
        this.politician = politician;
    }

    @Override
    public void run() {
        String url = "https://api-v3.mojepanstwo.pl/dane/poslowie/" + this.politician.getId() + ".json?layers[]=wyjazdy&layers[]=wydatki";
        RequestsMaker requestsMaker = new RequestsMaker();
        String jsonString = null;
        try {
            jsonString = requestsMaker.getJsonString(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PoliticianDetails politicianDetails = new GsonBuilder()
                .registerTypeAdapter(PoliticianDetails.class, new PoliticianDetailsDeserializer())
                .registerTypeAdapter(Trip.class, new TripDeserializer())
                .create()
                .fromJson(jsonString, PoliticianDetails.class);
        this.politician.setExpenses(politicianDetails.getExpenses());
        this.politician.setTrips(politicianDetails.getTrips());
    }

}
