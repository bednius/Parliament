package deserializers;

import com.google.gson.*;
import main.*;

import java.lang.reflect.Type;

/**
 * Created by kreska on 09.01.17.
 */
public class TripDeserializer implements JsonDeserializer<Trip> {
    @Override
    public Trip deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject data = (JsonObject) jsonElement;
        Trip trip = new Trip();

        trip.setCost(data.get("koszt_suma").getAsFloat());
        trip.setCountry(data.get("kraj").getAsString());
        trip.setDuration(data.get("liczba_dni").getAsInt());
        return trip;
    }
}
