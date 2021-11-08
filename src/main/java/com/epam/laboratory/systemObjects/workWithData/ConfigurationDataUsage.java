package com.epam.laboratory.systemObjects.workWithData;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationDataUsage {
    private static final String PROPERTIES_FILE = "src/main/resources/paths.properties";

    public static String pathToLibraryJsonFile;
    public static String pathToLibraryCsvFile;
    public static String pathToUserStoreJsonFile;
    public static String pathToUsersLogs;

    private static final Logger LOGGER = Logger.getLogger(ConfigurationDataUsage.class);

    static {
        try (FileInputStream propertiesFile = new FileInputStream(PROPERTIES_FILE)) {
            Properties properties = new Properties();
            properties.load(propertiesFile);

            pathToLibraryJsonFile = properties.getProperty("PATH_TO_LIBRARY_JSON_FILE");
            pathToLibraryCsvFile = properties.getProperty("PATH_TO_LIBRARY_CSV_FILE");
            pathToUserStoreJsonFile = properties.getProperty("PATH_TO_USER_STORE_JSON_FILE");
            pathToUsersLogs = properties.getProperty("PATH_TO_USERS_LOGS_FILE");

        } catch (FileNotFoundException ex) {
            LOGGER.error("Properties config file not found. " + ex.getMessage());
            LOGGER.error(ex.getMessage());
        } catch (IOException ex) {
            LOGGER.error("Reading file error. " + ex.getMessage());
            LOGGER.error(ex.getMessage());
        }
    }



}
