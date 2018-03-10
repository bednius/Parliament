package deserializers;

import com.google.gson.*;
import main.Data;
import main.Politician;

import java.lang.reflect.Type;

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
        } catch (NullPointerException ignored) {
        }
        return data;
    }
}
