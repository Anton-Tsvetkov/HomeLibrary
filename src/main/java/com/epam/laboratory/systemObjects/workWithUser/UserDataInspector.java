package com.epam.laboratory.systemObjects.workWithUser;

import com.epam.laboratory.systemObjects.workWithData.ConfigurationDataUsage;
import com.epam.laboratory.systemObjects.workWithData.loadData.DataLoader;
import com.epam.laboratory.systemObjects.workWithData.loadData.JSONParser;
import com.epam.laboratory.workObjects.user.User;
import com.epam.laboratory.workObjects.user.UserStatus;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserDataInspector {

    private static final DataLoader DATA_LOADER = new DataLoader();
    private List<User> userList = (List<User>) DATA_LOADER.getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile);
    private static final Logger LOGGER = Logger.getLogger(UserDataInspector.class);


    public boolean isUserExists(String username) {
        List<?> userList = DATA_LOADER.getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile);
        for (Object user : userList) {
            String usernameRealUser = ((User) user).getUsername();
            if (usernameRealUser.equals(username) && isUserUnlocked(usernameRealUser)) {
                return true;
            } else if (!isUserUnlocked(usernameRealUser)) {
                System.out.println("User " + username + " is locked");
                return false;
            }
        }
        return false;
    }

    private static boolean isUserUnlocked(String username) {
        List<?> userList = DATA_LOADER.getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile);
        for (Object user : userList) {
            if (((User) user).getUserStatus() == UserStatus.UNLOCKED) {
                return true;
            }
        }
        return false;
    }

    public int getIndexOfUserInListByUserName(List<User> users, String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isUserDataCorrect(String username, String password) {
        try {
            return userList.get(getIndexOfUserInListByUserName(userList, username)).getPassword().equals(password);
        } catch (IndexOutOfBoundsException e) {
            LOGGER.error("Incorrect data for: \"" + username + "\" user");
            LOGGER.error(e.getMessage());
        }
        return false;
    }

}
