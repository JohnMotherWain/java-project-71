package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
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
        Map<String, String> jsonMap1 = objectMapper.readValue(jsonString1, new TypeReference<>(){});
        Map<String, String> jsonMap2 = objectMapper.readValue(jsonString2, new TypeReference<>(){});

        HashMap<String, String> jsonSummary = new HashMap<>(jsonMap1);
        jsonSummary.putAll(jsonMap2);
        List<String> resultKeyList = jsonSummary.keySet().stream().sorted().toList();
        List<String> resultList = new ArrayList<>();

        resultKeyList.forEach((inKey) -> {
            if(jsonMap1.containsKey(inKey) && jsonMap2.containsKey(inKey)) {
                if(jsonMap1.get(inKey).equals(jsonMap2.get(inKey))) {
                    resultList.add("    " + inKey + " : " + jsonMap1.get(inKey) + "\n");
                } else {
                    resultList.add("  - " + inKey + " : " + jsonMap1.get(inKey) + "\n");
                    resultList.add("  + " + inKey + " : " + jsonMap2.get(inKey) + "\n");
                }
            } else if (jsonMap1.containsKey(inKey) && !jsonMap2.containsKey(inKey)) {
                resultList.add("  - " + inKey + " : " + jsonMap1.get(inKey) + "\n");
            } else if (!jsonMap1.containsKey(inKey) && jsonMap2.containsKey(inKey)) {
                resultList.add("  + " + inKey + " : " + jsonMap2.get(inKey) + "\n");
            }
        });
        StringBuilder resultString = new StringBuilder("{\n");
        for(String buffer : resultList) {
            resultString.append(buffer);
        }
        return resultString + "}";
    }
}
