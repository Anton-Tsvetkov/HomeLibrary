package com.epam.laboratory.entities.system;

import com.epam.laboratory.util.ConfigurationDataUsage;
import com.epam.laboratory.util.parsers.DataLoader;
import com.epam.laboratory.entities.global.UserStore;
import com.epam.laboratory.entities.user.User;
import com.epam.laboratory.entities.user.UserStatus;
import org.apache.log4j.Logger;

import java.util.List;

public class UserDataInspector {

    private final DataLoader dataLoader = new DataLoader();
    private final ConfigurationDataUsage configurationDataUsage = new ConfigurationDataUsage();
    private final UserStore userStore;
    private final Logger logger = Logger.getLogger(UserDataInspector.class);

    public UserDataInspector() {
        userStore = (UserStore) dataLoader.getDataFromFile(configurationDataUsage.getPathToUserStoreJsonFile());
    }

    public boolean isUserExists(String username) {
        UserStore userStore = (UserStore) dataLoader.getDataFromFile(configurationDataUsage.getPathToUserStoreJsonFile());
        for (User user : userStore.getList()) {
            String usernameRealUser = user.getUsername();
            if (usernameRealUser.equals(username) && isUserUnlocked(usernameRealUser)) {
                return true;
            } else if (!isUserUnlocked(usernameRealUser)) {
                System.out.println("User " + username + " is locked");
                return false;
            }
        }
        return false;
    }

    private boolean isUserUnlocked(String username) {
        UserStore userStore = (UserStore) dataLoader.getDataFromFile(configurationDataUsage.getPathToUserStoreJsonFile());
        for (User user : userStore.getList()) {
            if (user.getUserStatus() == UserStatus.UNLOCKED) {
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
            return userStore.getList().get(getIndexOfUserInListByUserName(userStore.getList(), username)).getPassword().equals(password);
        } catch (IndexOutOfBoundsException e) {
            logger.error("Incorrect data for: \"" + username + "\" user");
            logger.error(e.getMessage());
        }
        return false;
    }

}
