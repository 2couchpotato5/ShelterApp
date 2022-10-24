package ui;

import java.util.Scanner;

import model.Clothes;
import model.Shelter;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;


//Shelter application
public class ShelterApp {
    private Scanner input;
    private Shelter shelter;
    private boolean keepGoing;
    private static final String JSON_STORE = "./data/shelter.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public ShelterApp() throws FileNotFoundException {
        shelter = new Shelter();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        keepGoing = true;
        shelter.addFurniture("chair");
        shelter.addClothes(new Clothes("coat", "black", 45));
        shelter.addRequest("chair");
        shelter.fund(500);
        shelter.addRequest("blouse");
        shelter.addClothes(new Clothes("skirt", "white", 36));
        shelter.addFurniture("table");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runShelter();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runShelter() {
        String command;

        while (keepGoing) {
            displayLogin();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes login command
    private void processCommand(String command) {
        if (command.equals("u")) {
            displayUserPage();
        } else if (command.equals("s")) {
            displaySponsorPage();
        } else {
            System.out.println("Selection not valid...");
        }
    }



    // EFFECTS: displays login options
    private void displayLogin() {
        System.out.println("\nWelcome to Shelter App");
        System.out.println("\nChose how you want to login by choosing");
        System.out.println("\tu -> to enter as a user");
        System.out.println("\ts -> to enter as a sponsor");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays menu of options to user
    private void displayUserPage() {
        System.out.println("Enter 'I' to see the list of available items");
        System.out.println("Enter 'R' to make new request");
        System.out.println("Enter 'S' to save changes to file");
        System.out.println("Enter 'L' to load last changes from file");

        String command;
        command = input.next();
        command = command.toLowerCase();

        handleInput(command);

    }


    // EFFECTS: processes user command
    private void handleInput(String command) {
        if (command.equals("i")) {
            printClothes(shelter);
            printFurniture(shelter);
            printAvailableDonations(shelter);
            System.out.println("To make a selection print 'C'");
        } else if (command.equals("r")) {
            makeRequest();
        } else if (command.equals("s")) {
            saveShelter();
        } else if (command.equals("l")) {
            loadShelter();
        } else {
            System.out.println("Selection not valid...");
        }

        quitingOptionsUser();

    }

    // MODIFIES: this
    // EFFECTS: gives different option to proceed to user
    private void quitingOptionsUser() {
        System.out.println("To go back print 'B'");
        System.out.println("To quit print 'Q'");

        String str;
        str = input.next();
        str = str.toLowerCase();

        if (str.equals("b")) {
            displayUserPage();
        } else if (str.equals("c")) {
            makeSelection();
        } else {
            keepGoing = false;
            System.out.println("Quitting...");
        }
    }

    // EFFECTS: saves the shelter to file
    private void saveShelter() {
        try {
            jsonWriter.open();
            jsonWriter.write(shelter);
            jsonWriter.close();
            System.out.println("Saved changesq to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads shelter from file
    private void loadShelter() {
        try {
            shelter = jsonReader.read();
            System.out.println("Loaded shelter changes from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds new request to the shelter
    private void makeRequest() {
        System.out.println("Make a request of clothes or furniture you need");
        String str;
        str = input.next();
        str = str.toLowerCase();

        shelter.addRequest(str);
        quitingOptionsUser();

    }


    //EFFECTS: shows to user menu of selections
    private void makeSelection() {
        System.out.println("Choose a category");

        System.out.println("Enter 'C' for clothes");
        System.out.println("Enter 'F' for furniture");
        System.out.println("Enter 'M' for monetary donations");

        String str;
        str = input.next();
        str = str.toLowerCase();

        handleSelection(str);

    }

    private void handleSelection(String str) {
        if (str.equals("c")) {
            chooseClothes();
        } else if (str.equals("f")) {
            chooseFurniture();
        } else {
            chooseFund();
        }
        quitingOptionsUser();

    }

    //MODIFIES:this
    //EFFECTS: chooses item from the list of available clothes
    private void chooseClothes() {
        System.out.println("Enter the name of the clothes:");
        String name;
        name = input.next();
        name = name.toLowerCase();

        System.out.println("Enter the color of the clothes:");
        String color;
        color = input.next();
        color = color.toLowerCase();


        System.out.println("Enter the size of the clothes from size 30 to 54:");
        Integer size;
        size = Integer.valueOf(input.next());

        Clothes cl = new Clothes(name, color, size);

        if (shelter.getClothes().contains(cl)) {
            shelter.takeClothes(cl);
        } else {
            System.out.println("There is no such an item");
        }
    }

    //MODIFIES:this
    //EFFECTS: chooses item from the list of available furniture
    private void chooseFurniture() {
        System.out.println("Enter the name of the furniture:");

        String name;
        name = input.next();
        name = name.toLowerCase();

        shelter.takeFurniture(name);
    }

    //MODIFIES:this
    //EFFECTS: decreases the fund of the shelter by the amount input
    private void chooseFund() {
        System.out.println("Enter the amount you want to take:");

        Integer amount;
        amount = Integer.valueOf(input.next());

        shelter.useDonations(amount);
    }

    // EFFECTS: displays menu of options to sponsor
    private void displaySponsorPage() {
        System.out.println("Enter 'D' to make a donation");
        System.out.println("Enter 'R' to see current request");
        System.out.println("Enter 'I' to see the list of available items");
        System.out.println("Enter 'S' to save changes to file");
        System.out.println("Enter 'L' to load last changes from file");

        String command;
        command = input.next();
        command = command.toLowerCase();


        handleInputSponsor(command);

    }

    // EFFECTS: processes sponsor command depending on the string
    private void handleInputSponsor(String command) {
        if (command.equals("r")) {
            printRequests(shelter);
        } else if (command.equals("d")) {
            makeDonation();
        } else if (command.equals("i")) {
            printClothes(shelter);
            printFurniture(shelter);
            printAvailableDonations(shelter);
        } else if (command.equals("s")) {
            saveShelter();
        } else if (command.equals("l")) {
            loadShelter();
        } else {
            System.out.println("Selection not valid...");
        }

        quitingOptionsSponsor();

    }

    // MODIFIES: this
    // EFFECTS: gives different option to proceed to sponsor
    private void quitingOptionsSponsor() {
        System.out.println("To go back print 'B'");
        System.out.println("To quit print 'Q'");

        String str;
        str = input.next();
        str = str.toLowerCase();

        if (str.equals("b")) {
            displaySponsorPage();
        } else {
            keepGoing = false;
            System.out.println("Quitting...");

        }
    }

    //EFFECTS: displays menu to make donations
    private void makeDonation() {
        System.out.println("Choose a category");
        System.out.println("Enter 'C' for clothes");
        System.out.println("Enter 'F' for furniture");
        System.out.println("Enter 'M' for monetary donations");

        String str;
        str = input.next();
        str = str.toLowerCase();


        handleDonations(str);
    }

    //MODIFIES: this
    //EFFECTS: makes new donation to the shelter
    private void handleDonations(String str) {
        if (str.equals("c")) {
            newClothes();
        } else if (str.equals("f")) {
            newFurniture();
        } else {
            newFund();
        }

        quitingOptionsSponsor();

    }


    //MODIFIES:this
    //EFFECTS: adds new donated item to the list of available clothes
    private void newClothes() {
        System.out.println("Enter the name of the clothes:");
        String name;
        name = input.next();
        name = name.toLowerCase();


        System.out.println("Enter the color of the clothes:");
        String color;
        color = input.next();
        color = color.toLowerCase();


        System.out.println("Enter the size of the clothes from size 30 to 54:");
        Integer size;
        size = Integer.valueOf(input.next());


        shelter.addClothes(new Clothes(name, color, size));
    }

    //MODIFIES:this
    //EFFECTS: adds new donated item to the list of available furniture
    private void newFurniture() {
        System.out.println("Enter the name of the furniture:");
        String name;
        name = input.next();
        name = name.toLowerCase();


        shelter.addFurniture(name);
    }

    //MODIFIES:this
    //EFFECTS: increases the fund of the shelter by the amount input
    private void newFund() {
        System.out.println("Enter the amount you want to donate:");
        Integer amount;
        amount = Integer.valueOf(input.next());


        shelter.fund(amount);
    }

    //EFFECTS:print the list of all available clothes
    private void printClothes(Shelter shelter) {
        if (shelter.getClothes().size() == 0) {
            System.out.println("There are no available clothes at this moment");
        } else {
            System.out.println("Shelter has the following clothes available:");
            for (Clothes c : shelter.getClothes()) {
                System.out.println(c.getName() + "  in " + c.getColor() + " color and size " + c.getSize());
            }
        }

    }

    //EFFECTS:print the list of all available furniture
    private void printFurniture(Shelter shelter) {
        if (shelter.getFurnitures().size() == 0) {
            System.out.println("There are no available furniture at this moment");
        } else {
            System.out.println("Shelter has the following furniture available:");
            for (String s : shelter.getFurnitures()) {
                System.out.println(s);
            }
        }

    }

    //EFFECTS:print the available monetary donations
    private void printAvailableDonations(Shelter shelter) {
        System.out.println("Shelter received " + shelter.getAmountFounded() + "$ in monetary donations");
    }

    //EFFECTS:print the list of all available requests
    private void printRequests(Shelter shelter) {
        if (shelter.getRequests().size() == 0) {
            System.out.println("There are no requests at this moment");
        } else {
            System.out.println("Shelter has the following requests:");
            for (String s : shelter.getRequests()) {
                System.out.println("Request:" + s);
            }
        }

    }
}
