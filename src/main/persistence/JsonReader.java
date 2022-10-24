package persistence;

import model.Clothes;
import model.Shelter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads shelter from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads shelter from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Shelter read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseShelter(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses shelter from JSON object and returns it
    private Shelter parseShelter(JSONObject jsonObject) {
        Shelter sh = new Shelter();
        addClothes(sh, jsonObject);
        addFurnitures(sh, jsonObject);
        addRequest(sh, jsonObject);
        Integer amount = jsonObject.getInt("Amount Founded");
        sh.fund(amount);
        return sh;
    }

    // MODIFIES: sh
    // EFFECTS: parses clothes from JSON object and adds them to shelter
    private void addClothes(Shelter sh, JSONObject jsonObject) {
        JSONArray clothesJsonArray = jsonObject.getJSONArray("Clothes");
        for (Object json : clothesJsonArray) {
            JSONObject nextClothes = (JSONObject) json;
            addCloth(sh, nextClothes);
        }
    }

    // MODIFIES: sh
    // EFFECTS: parses one piece of cloth from JSON object and adds it to shelter
    private void addCloth(Shelter sh, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String color = jsonObject.getString("color");
        Integer size = jsonObject.getInt("size");
        Clothes cl = new Clothes(name, color, size);
        sh.addClothes(cl);
    }

    // MODIFIES: sh
    // EFFECTS: parses furniture from JSON object and adds them to shelter
    private void addFurnitures(Shelter sh, JSONObject jsonObject) {
        JSONArray furnitureJsonArray = jsonObject.getJSONArray("Furniture");
        for (Object json : furnitureJsonArray) {
            //JSONObject nextFurniture = (JSONObject) json;
            sh.addFurniture((String) json);
        }
    }

    // MODIFIES: sh
    // EFFECTS: parses requests from JSON object and adds them to shelter
    private void addRequest(Shelter sh, JSONObject jsonObject) {
        JSONArray requestsJsonArray = jsonObject.getJSONArray("Requests");
        for (Object json : requestsJsonArray) {
            //JSONObject nextRequest = (JSONObject) json;
            sh.addRequest(String.valueOf(json));
        }
    }
}
