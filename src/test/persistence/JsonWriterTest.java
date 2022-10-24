package persistence;

import model.Shelter;
import model.Clothes;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Shelter sh = new Shelter();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterNewShelter() {
        try {
            Shelter sh = new Shelter();
            JsonWriter writer = new JsonWriter("./data/testWriterNewShelter.json");
            writer.open();
            writer.write(sh);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNewShelter.json");
            sh = reader.read();
            assertEquals(0, sh.getAmountFounded());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterShelter() {
        try {
            Shelter sh = new Shelter();
            sh.addClothes(new Clothes("skirt", "black", 42));
            sh.addFurniture("chair");
            sh.addRequest("blouse" );
            sh.fund(300);
            JsonWriter writer = new JsonWriter("./data/testWriterShelter.json");
            writer.open();
            writer.write(sh);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterShelter.json");
            sh = reader.read();
            List<Clothes> clothes = sh.getClothes();
            assertEquals(1, clothes.size());
            List<String> furniture = sh.getFurnitures();
            assertEquals(1, furniture.size());
            List<String> request = sh.getRequests();
            assertEquals(1, request.size());
            assertEquals(300, sh.getAmountFounded());


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}