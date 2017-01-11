package deserializers;

import com.google.gson.*;
import main.*;

import java.lang.reflect.Type;

/**
 * Created by kreska on 08.01.17.
 */
public class PoliticianDeserializer implements JsonDeserializer<Politician> {

    @Override
    public Politician deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject data = (JsonObject) jsonElement;
        JsonObject jsonPolitician = (JsonObject) data.get("data");

        Politician politician = new Politician();

        politician.setId(jsonPolitician.get("poslowie.id").getAsInt());
        //politician.setGenitive(jsonPolitician.get("poslowie.dopelniacz").getAsString());

        String firstName = jsonPolitician.get("poslowie.imie_pierwsze").getAsString();
        String lastName = jsonPolitician.get("poslowie.nazwisko").getAsString();
        String name = firstName + " " + lastName;
        if(name.equals("Mariusz Kamiński")) {
            name = jsonPolitician.get("ludzie.nazwa").getAsString();
        }
        politician.setName(name);
        return politician;
    }
}
