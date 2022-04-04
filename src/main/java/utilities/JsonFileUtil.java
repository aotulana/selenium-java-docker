package utilities;

import api.requestModel.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonFileUtil {

    public static User[] getTestUsersFromJsonFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("src/main/resources/api/users.json");

        return objectMapper.readValue(file, User[].class);
    }
}
