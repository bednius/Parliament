package deserializers;

import com.google.gson.*;
import main.*;

import java.lang.reflect.Type;

/**
 * Created by kreska on 08.01.17.
 */
public class DataDeserializer implements JsonDeserializer<Data> {

    @Override
    public Data deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = (JsonObject) jsonElement;

        Politician[] politicians = jsonDeserializationContext
                .deserialize(jsonObject.get("Dataobject"), Politician[].class);

        JsonObject jsonUrls = (JsonObject) ((JsonObject) jsonElement).get("Links");

        Data data = new Data(politicians);
        try {
            data.setNextUrl(jsonUrls.get("next").getAsString());
            data.setLastUrl(jsonUrls.get("last").getAsString());
        } catch (NullPointerException e) {}
        return data;
    }
}
