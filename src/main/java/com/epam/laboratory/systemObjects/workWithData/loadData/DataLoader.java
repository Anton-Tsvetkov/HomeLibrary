package com.epam.laboratory.systemObjects.workWithData.loadData;

import com.epam.laboratory.exceptions.UserDataException;
import com.epam.laboratory.systemObjects.workWithData.ConfigurationDataUsage;
import com.epam.laboratory.workObjects.user.User;

import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    private final JSONParser JSON_PARSER = new JSONParser();


    public List<?> getDataFromFile(String pathToFile) {
        if (pathToFile.toLowerCase().contains("json")) {
            return JSON_PARSER.parseObjectsFromJson(pathToFile);
        } else if (pathToFile.toLowerCase().contains("csv")) {
            // return data from csv file
            return new ArrayList<>();
        } else {
            System.out.println("Data not found");
            return new ArrayList<>();
        }
    }

    public User getUserByUsername(String username) throws Throwable {
        List<User> userList = (List<User>) getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile);
        for (User user : userList){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        throw new UserDataException().fillInStackTrace();
    }

    // add exceptions
    public void addNewObjectsInFile(String pathToFile, List<?> objects) {
        if (pathToFile.toLowerCase().contains("json")) {
            JSON_PARSER.parseObjectsToJson(pathToFile, objects);
        } else if (pathToFile.toLowerCase().contains("csv")) {
            // return data from csv file
        } else {
            System.out.println("Data not found");
        }
    }

}
