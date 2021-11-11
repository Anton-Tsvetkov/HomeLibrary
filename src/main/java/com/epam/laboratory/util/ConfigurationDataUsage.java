package com.epam.laboratory.util;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationDataUsage {
    private static final String propertiesFilePath = "src/main/resources/properties/paths.properties";

    public static String pathToLibraryJsonFile;
    public static String pathToLibraryCsvFile;
    public static String pathToUserStoreJsonFile;
    public static String pathToUsersLogs;

    private static final Logger logger = Logger.getLogger(ConfigurationDataUsage.class);

    static {
        try (FileInputStream propertiesFile = new FileInputStream(ConfigurationDataUsage.propertiesFilePath)) {
            Properties properties = new Properties();
            properties.load(propertiesFile);

            pathToLibraryJsonFile = properties.getProperty("PATH_TO_LIBRARY_JSON_FILE");
            pathToLibraryCsvFile = properties.getProperty("PATH_TO_LIBRARY_CSV_FILE");
            pathToUserStoreJsonFile = properties.getProperty("PATH_TO_USER_STORE_JSON_FILE");
            pathToUsersLogs = properties.getProperty("PATH_TO_USERS_LOGS_FILE");

        } catch (FileNotFoundException ex) {
            logger.error("Properties config file not found. " + ex.getMessage());
            logger.error(ex.getMessage());
        } catch (IOException ex) {
            logger.error("Reading file error. " + ex.getMessage());
            logger.error(ex.getMessage());
        }
    }



}
