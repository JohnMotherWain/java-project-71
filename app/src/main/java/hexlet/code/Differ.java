package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;


public class Differ {
    public static void generate(String filePath1, String filePath2) throws Exception {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        if (!Files.exists(path1)) {
            throw new Exception("File '" + path1 + "' does not exist");
        }
        String jsonString1 = Files.readString(path1);

        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();
        if (!Files.exists(path2)) {
            throw new Exception("File '" + path2 + "' does not exist");
        }
        String jsonString2 = Files.readString(path2);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap1 = objectMapper.readValue(jsonString1, new TypeReference<Map<String,Object>>(){});
        Map<String, Object> jsonMap2 = objectMapper.readValue(jsonString2, new TypeReference<Map<String,Object>>(){});

        System.out.println(jsonMap1.toString());
        System.out.println(jsonMap2.toString());


        // Читаем файл
//        String content = Files.readString(path);



    /*public class Car {

    private String color;
    private String type;

    // standard getters setters
    }*/

        //ObjectMapper objectMapper = new ObjectMapper();
        //Car car = new Car("yellow", "renault");
        //String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        //Car car = objectMapper.readValue(jsonString, Car.class);
        //String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";

    }
}
