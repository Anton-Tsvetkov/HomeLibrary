package com.epam.laboratory.util.parsers;

import com.epam.laboratory.entities.global.GlobalObject;
import com.epam.laboratory.entities.global.Library;
import com.epam.laboratory.entities.global.UserStore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONParser {

    private static final Logger logger = Logger.getLogger(JSONParser.class);


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
            logger.error(ex.getMessage());
        }

        return object;

    }

    public void parseObjectsToJson(String pathToJSONFile, GlobalObject object) {
        try {
            Writer fileWriter = Files.newBufferedWriter(Paths.get(pathToJSONFile));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(object, fileWriter);
            fileWriter.close();
        } catch (IOException ex) {
            logger.error("Error writing to file '" + pathToJSONFile + "'");
            logger.error(ex.getMessage());
        }

    }

}

