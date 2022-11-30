package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents Shelter with lists of available to use items and monetary donations
public class Shelter implements Writable {
    private List<Clothes> clothes;         //list of available clothes
    private List<String> furnitures;       //list of available furniture
    private List<String> requests;         //list of made requests
    private int amountFounded;             // the current amount of donations

    // Effects: creates a shelter with empty lists of clothes, furniture and requests and 0 amountFounded
    public Shelter() {
        clothes = new ArrayList<>();
        furnitures = new ArrayList<>();
        requests = new ArrayList<>();
        amountFounded = 0;
    }

    public List<Clothes> getClothes() {
        return clothes;
    }

    public List<String> getFurnitures() {
        return furnitures;
    }

    public List<String> getRequests() {
        return requests;
    }

    public int getAmountFounded() {
        return amountFounded;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: increases amountFunded for this shelter by amount (in $)
    public void fund(int amount) {
        amountFounded = amountFounded + amount;
        EventLog.getInstance().logEvent(new Event("New monetary donation to shelter"));
    }

    // REQUIRES: amount > 0 AND getAmountFounded()>= amount
    // MODIFIES: this
    // EFFECTS: decreases amountFounded for this shelter by amount (in $)
    public void useDonations(int amount) {
        amountFounded = amountFounded - amount;
        EventLog.getInstance().logEvent(new Event("User took some monetary donations from shelter"));
    }

    // MODIFIES: this
    // EFFECTS: adds given clothes to the list of available lists
    //          if added clothes satisfies one of the requests, then it will be removed from the list of requests
    public void addClothes(Clothes clothes1) {
        clothes.add(clothes1);
        if (requests.contains(clothes1.getName())) {
            requests.remove(clothes1.getName());
        }
        EventLog.getInstance().logEvent(new Event("New item of clothing added to shelter."));
    }

    // MODIFIES: this
    // EFFECTS: removes given clothes from the list of available lists
    public void takeClothes(Clothes clothes1) {
        if (clothes.contains(clothes1)) {
            clothes.remove(clothes1);
        }
        EventLog.getInstance().logEvent(new Event("Piece of clothing was taken from the shelter."));

    }

    // MODIFIES: this
    // EFFECTS: adds given furniture to the list of available lists
    //          if added furniture satisfies one of the requests, then it will be removed from the list of requests
    public void addFurniture(String furniture) {
        furnitures.add(furniture);
        if (requests.contains(furniture)) {
            requests.remove(furniture);
        }
        EventLog.getInstance().logEvent(new Event("New item of furniture added to shelter."));
    }

    // MODIFIES: this
    // EFFECTS: removes given furniture from the list of available lists
    public void takeFurniture(String furniture) {
        if (furnitures.contains(furniture)) {
            furnitures.remove(furniture);
        }
        EventLog.getInstance().logEvent(new Event("Furniture was taken from the shelter."));
    }

    // MODIFIES: this
    // EFFECTS: adds new request to the list of requests
    public void addRequest(String request) {
        requests.add(request);
        EventLog.getInstance().logEvent(new Event("New request added to shelter."));

    }

    //EFFECTS: prints log
    public void logPrinter(EventLog eventlog) {
        for (Event e : eventlog) {
            System.out.println(e.getDescription());
        }
    }



    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Clothes", clothesToJson());
        json.put("Furniture", furnitureToJson());
        json.put("Requests", requestsToJson());
        json.put("Amount Founded", amountFounded);
        return json;
    }

    // EFFECTS: returns clothes in the shelter as a JSON array
    private JSONArray clothesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Clothes t : clothes) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns clothes in the shelter as a JSON array
    private JSONArray furnitureToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String s : furnitures) {
            jsonArray.put(s);
        }

        return jsonArray;
    }

    // EFFECTS: returns clothes in the shelter as a JSON array
    private JSONArray requestsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String r : requests) {
            jsonArray.put(r);
        }

        return jsonArray;
    }
}
