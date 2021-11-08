package com.epam.laboratory.systemObjects.workWithData;

import com.epam.laboratory.systemObjects.workWithData.parsers.DataLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserActionsLogger {

    private final DataLoader DATA_LOADER = new DataLoader();

    private String parseActionToLogFormat(String username, String action) {
        String actionLogFormat = username;
        actionLogFormat += "\t" + DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
        actionLogFormat += "\n" + action + "\n";
        return actionLogFormat;
    }

    public void logAction(String username, String action) {
        DATA_LOADER.addDataToFile(ConfigurationDataUsage.pathToUsersLogs,
                parseActionToLogFormat(username, action));
    }

    public String getUserLogsByUsername(String username) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ConfigurationDataUsage.pathToUsersLogs))) {
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

        return DATA_LOADER.getDataFromFileAsString(ConfigurationDataUsage.pathToUsersLogs);
    }

}
