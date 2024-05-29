package hexlet.code;

import com.fasterxml.jackson.databind.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class Differ {
    public static void generate(String filePath1, String filePath2) throws Exception {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();

        if (!Files.exists(path1)) {
            throw new Exception("File '" + path1 + "' does not exist");
        }
        if (!Files.exists(path2)) {
            throw new Exception("File '" + path2 + "' does not exist");
        }

        // Читаем файл
//        String content = Files.readString(path);
        String jsonString = Files.readString(path1);


    /*public class Car {

    private String color;
    private String type;

    // standard getters setters
    }*/

        ObjectMapper objectMapper = new ObjectMapper();
        //Car car = new Car("yellow", "renault");
        //String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        Car car = objectMapper.readValue(jsonString, Car.class);
    }
}
