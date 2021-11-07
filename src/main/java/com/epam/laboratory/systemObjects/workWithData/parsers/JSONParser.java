package com.epam.laboratory.systemObjects.workWithData.parsers;

import com.epam.laboratory.workObjects.globalObjects.GlobalObject;
import com.epam.laboratory.workObjects.globalObjects.Library;
import com.epam.laboratory.workObjects.globalObjects.UserStore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JSONParser {

    private static final Logger LOGGER = Logger.getLogger(JSONParser.class);


    public GlobalObject parseObjectsFromJson(String pathToJSONFile) {
        GlobalObject object;
        if (pathToJSONFile.toLowerCase().contains("library")) {
            object = new Library();
        } else if (pathToJSONFile.toLowerCase().contains("userstore")) {
            object = new UserStore();
        } else {
            object = new Library();
        }

        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(pathToJSONFile));
            object = gson.fromJson(reader, object.getClass());
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }

        return object;

    }

    public void parseObjectsToJson(String pathToJSONFile, GlobalObject object) {
        try {
//            if (pathToJSONFile.toLowerCase().contains("library")) {
//                object = new Library();
//            } else if (pathToJSONFile.toLowerCase().contains("user")) {
//                object = new UserStore();
//            } else {
//                throw new UserDataException();
//            }

//            object.addObjectsToList(parseObjectsFromJson(pathToJSONFile));
//            object.addObjectsToList(objects);

            Writer fileWriter = Files.newBufferedWriter(Paths.get(pathToJSONFile));
            //Writer fileWriter = new BufferedWriter(new FileWriter(pathToJSONFile, true));

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(object, fileWriter);

            fileWriter.close();

        } catch (IOException ex) {
            LOGGER.error("Error writing to file '" + pathToJSONFile + "'");
            LOGGER.error(ex.getMessage());
        }

    }

}

