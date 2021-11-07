package com.epam.laboratory.systemObjects.workWithData.parsers;

import com.epam.laboratory.exceptions.UserDataException;
import com.epam.laboratory.systemObjects.workWithData.ConfigurationDataUsage;
import com.epam.laboratory.workObjects.globalObjects.GlobalObject;
import com.epam.laboratory.workObjects.globalObjects.Library;
import com.epam.laboratory.workObjects.user.User;

import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    private final JSONParser JSON_PARSER = new JSONParser();


    public GlobalObject getDataFromFile(String pathToFile) {
        if (pathToFile.toLowerCase().contains("json")) {
            return JSON_PARSER.parseObjectsFromJson(pathToFile);
        } else if (pathToFile.toLowerCase().contains("csv")) {
            // return data from csv file
            return new Library();
        } else {
            System.out.println("Data not found");
            return new Library();
        }
    }


    public void updateFile(String pathToFile, GlobalObject actualGlobalObject){
        GlobalObject oldGlobalObject = new DataLoader().getDataFromFile(pathToFile);
        if(oldGlobalObject.getList().size() != actualGlobalObject.getList().size()){

        }
    }

    public void addNewObjectsInFile(String pathToFile, GlobalObject object) {
        if (pathToFile.toLowerCase().contains("json")) {
            JSON_PARSER.parseObjectsToJson(pathToFile, object);
        } else if (pathToFile.toLowerCase().contains("csv")) {
            // return data from csv file
        } else {
            System.out.println("Data not found");
        }
    }

}
