package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Cell;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class JsonParser implements Parser<File>{

    private ObjectMapper mapper = new ObjectMapper();

    public JsonParser() {

    }

    @Override
    public String parseTo(String data) {//parseToJson

        return "{Json}";//json
    }

    @Override
    public String parseFrom(String data) {
        String jsonArray = data.trim();
        try {
            return  mapper.readValue(jsonArray, new TypeReference<List<Cell>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
