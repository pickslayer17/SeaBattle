package files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Deque;

public class JsonParser implements Parser{

    private ObjectMapper mapper = new ObjectMapper();

    public JsonParser() {

    }

    @Override
    public String serialize(Deque<GameState> gameStateHistory) throws IOException {//parseToJson
        StringBuilder stringBuilder = new StringBuilder();
        StringWriter writer = new StringWriter();


        mapper.writeValue(writer, gameStateHistory);
        stringBuilder.append(writer);
        return stringBuilder.toString();//json
    }

    @Override
    public Deque<GameState> deSerialize(String data) throws IOException {
        String jsonObj = data.trim();
        return mapper.readValue(jsonObj, new TypeReference<Deque<GameState>>(){});
    }


}
