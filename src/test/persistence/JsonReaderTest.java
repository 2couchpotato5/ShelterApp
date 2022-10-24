package persistence;

import model.Clothes;
import model.Shelter;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Shelter sh = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNewShelter() {
        JsonReader reader = new JsonReader( "./data/testReaderNewShelter.json");
        try {
            Shelter sh = reader.read();
            assertEquals(0, sh.getAmountFounded());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderShelter() {
        JsonReader reader = new JsonReader("./data/testReaderShelter.json");
        try {
            Shelter sh = reader.read();
            List<Clothes> clothes = sh.getClothes();
            assertEquals(1, clothes.size());
            List<String> furniture = sh.getFurnitures();
            assertEquals(1, furniture.size());
            List<String> request = sh.getRequests();
            assertEquals(1, request.size());
            assertEquals(300, sh.getAmountFounded());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}