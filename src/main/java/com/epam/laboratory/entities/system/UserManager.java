package com.epam.laboratory.entities.system;

import com.epam.laboratory.exceptions.UserDataException;
import com.epam.laboratory.util.ConfigurationDataUsage;
import com.epam.laboratory.util.UserActionsLogger;
import com.epam.laboratory.util.parsers.DataLoader;
import com.epam.laboratory.entities.global.UserStore;
import com.epam.laboratory.entities.library.Bookmark;
import com.epam.laboratory.entities.user.User;
import com.epam.laboratory.entities.user.UserRights;
import com.epam.laboratory.entities.user.UserStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserManager {

    private final DataLoader dataLoader = new DataLoader();
    private final UserActionsLogger userActionsLogger = new UserActionsLogger();

    public void registerNewUser(String username, String password) {
        UserStore userStore = (UserStore) dataLoader.getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile);
        userStore.addObjectsToList(new ArrayList<User>((Collection<? extends User>) new User(username, password)));
        dataLoader.updateFile(ConfigurationDataUsage.pathToUserStoreJsonFile, userStore);
    }

    private UserRights getUserStatusByUserName(String username) throws Throwable {
        return new UserManager().getUserByUsername(username).getUserRights();
    }

    public String getUsersLogs(String username) {
        return userActionsLogger.getUserLogsByUsername(username);
    }

    public User getUserByUsername(String username) throws Throwable {
        List<User> userList = (List<User>) dataLoader.getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile).getList();
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new UserDataException("").fillInStackTrace();

    }

    public Bookmark getBookmarkByName(User user, String bookTitle, String bookmarkName) {
        List<Bookmark> bookmarkList = user.getBookmarkList();
        Bookmark emptyBookmark = new Bookmark();
        for (Bookmark bookmark : bookmarkList) {
            if (bookmark.getName().equals(bookmarkName) && bookmark.getISBN().equals(bookTitle)) {
                return bookmark;
            }
        }
        return emptyBookmark;
    }

    public void blockUser(String username) {
        UserStore userStore = (UserStore) dataLoader.getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile);
        for (User user : userStore.getList()) {
            if (user.getUsername().equals(username)) {
                user.setUserStatus(UserStatus.LOCKED);
                break;
            }
        }
        dataLoader.updateFile(ConfigurationDataUsage.pathToUserStoreJsonFile, userStore);
    }

    public void unlockUser(String username) {
        UserStore userStore = (UserStore) dataLoader.getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile);
        for (User user : userStore.getList()) {
            if (user.getUsername().equals(username)) {
                user.setUserStatus(UserStatus.UNLOCKED);
                break;
            }
        }
        dataLoader.updateFile(ConfigurationDataUsage.pathToUserStoreJsonFile, userStore);
    }
}
