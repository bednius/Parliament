package deserializers;

import com.google.gson.*;
import main.*;

import java.lang.reflect.Type;

/**
 * Created by kreska on 09.01.17.
 */
public class PoliticianDetailsDeserializer implements JsonDeserializer<PoliticianDetails>{

    @Override
    public PoliticianDetails deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject data = (JsonObject) jsonElement;
        JsonObject layers = (JsonObject) data.get("layers");
        JsonObject wydatki = (JsonObject) layers.get("wydatki");

        Expense[] expenses = jsonDeserializationContext.deserialize(wydatki.get("roczniki"), Expense[].class);
        PoliticianDetails politicianDetails = new PoliticianDetails(expenses);
        try {
            Trip[] trips = jsonDeserializationContext.deserialize(layers.get("wyjazdy"), Trip[].class);
            politicianDetails.setTrips(trips);
        } catch (Exception e) {}
        return politicianDetails;
    }
}
