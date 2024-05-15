package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
    public static void main(String[] args) {
        User owner = new User(1, "Ivan", "Petrov", 25);
        Car car = new Car(1, "bmv", "x5", "black", owner);
        String jsonRepresentation = null;
        try {
            jsonRepresentation = car.serialize();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(jsonRepresentation); // =>
      /*
      {
        "id":1,
        "brand":"bmv",
        "model":"x5",
        "color":"black",
        "owner":{
            "id":1,
            "firstName":"Ivan",
            "lastName":"P",
            "age":25
        }
      }
      */

        Car instance = null;
        try {
            instance = Car.unserialize(jsonRepresentation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(instance.getBrand()); // "bmv"
        System.out.println(instance.getModel()); // "x5"*/
    }

    public static void save(Path path, Car car) throws IOException {
        String jsonRepresentation = car.serialize();
        Files.writeString(path, jsonRepresentation, StandardOpenOption.WRITE);
    }

    public static Car extract(Path path) throws IOException {
        String jsonRepresentation = Files.readString(path);
        return Car.unserialize(jsonRepresentation);
    }
}
// END
