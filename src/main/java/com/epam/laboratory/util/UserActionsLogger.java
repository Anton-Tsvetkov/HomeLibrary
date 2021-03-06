package com.epam.laboratory.util;

import com.epam.laboratory.util.parsers.DataLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserActionsLogger {

    private final DataLoader dataLoader = new DataLoader();
    private final ConfigurationDataUsage configurationDataUsage = new ConfigurationDataUsage();

    private String parseActionToLogFormat(String username, String action) {
        String actionLogFormat = username;
        actionLogFormat += "\t" + DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
        actionLogFormat += "\n" + action + "\n";
        return actionLogFormat;
    }

    public void logAction(String username, String action) {
        dataLoader.addDataToFile(configurationDataUsage.getPathToUsersLogs(),
                parseActionToLogFormat(username, action));
    }

    public String getUserLogsByUsername(String username) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(configurationDataUsage.getPathToUsersLogs()))) {
            String userLog = "";
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                if (readLine.contains(username)) {
                    userLog += readLine + "\n";
                    userLog += bufferedReader.readLine() + "\n\n";
                }
                readLine = bufferedReader.readLine();
            }
            return userLog;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataLoader.getDataFromFileAsString(configurationDataUsage.getPathToUsersLogs());
    }

}
