package helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Parser {
    public static HashMap<String, String> parseJSONFields(String filePath) {
        String jsonString = null;
        HashMap<String, String> hm = new HashMap<>();
        try {
            Path path = Paths.get(filePath);
            byte[] bytes = Files.readAllBytes(path);
            jsonString = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        String[] lines = jsonString.split("\n");

        for (String line : lines) {
            if (line.contains(":")) {
                String[] parts = line.split(":");
                String fieldName = parts[0].trim().replace("\"", "");
                String fieldValue = parts[1].trim().replace(",", "").replace("\"", "");
                hm.put(fieldName, fieldValue);
            }
        }
        return hm;
    }
}
// basic parser to avoid 3rd party libs dependencies - jackson
