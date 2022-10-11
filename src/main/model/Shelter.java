package model;

import java.util.ArrayList;
import java.util.List;

// Represents Shelter with lists of available to use items and monetary donations
public class Shelter {
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
    }

    // REQUIRES: amount > 0 AND getAmountFounded()>= amount
    // MODIFIES: this
    // EFFECTS: decreases amountFounded for this shelter by amount (in $)
    public void useDonations(int amount) {
        amountFounded = amountFounded - amount;
    }

    // MODIFIES: this
    // EFFECTS: adds given clothes to the list of available lists
    //          if added clothes satisfies one of the requests, then it will be removed from the list of requests
    public void addClothes(Clothes clothes1) {
        clothes.add(clothes1);
        if (requests.contains(clothes1.getName())) {
            requests.remove(clothes1.getName());
        }
    }

    // MODIFIES: this
    // EFFECTS: removes given clothes from the list of available lists
    public void takeClothes(Clothes clothes1) {
        if (clothes.contains(clothes1)) {
            clothes.remove(clothes1);
        }

    }

    // MODIFIES: this
    // EFFECTS: adds given furniture to the list of available lists
    //          if added furniture satisfies one of the requests, then it will be removed from the list of requests
    public void addFurniture(String furniture) {
        furnitures.add(furniture);
        if (requests.contains(furniture)) {
            requests.remove(furniture);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes given furniture from the list of available lists
    public void takeFurniture(String furniture) {
        if (furnitures.contains(furniture)) {
            furnitures.remove(furniture);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds new request to the list of requests
    public void addRequest(String request) {
        requests.add(request);
    }

}
