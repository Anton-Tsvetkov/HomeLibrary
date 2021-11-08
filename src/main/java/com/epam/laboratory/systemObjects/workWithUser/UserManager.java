package com.epam.laboratory.systemObjects.workWithUser;

import com.epam.laboratory.exceptions.UserDataException;
import com.epam.laboratory.systemObjects.workWithData.ConfigurationDataUsage;
import com.epam.laboratory.systemObjects.workWithData.UserActionsLogger;
import com.epam.laboratory.systemObjects.workWithData.parsers.DataLoader;
import com.epam.laboratory.systemObjects.workWithData.parsers.GlobalObjectParser;
import com.epam.laboratory.workObjects.globalObjects.UserStore;
import com.epam.laboratory.workObjects.library.Bookmark;
import com.epam.laboratory.workObjects.user.User;
import com.epam.laboratory.workObjects.user.UserRights;
import com.epam.laboratory.workObjects.user.UserStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserManager {

    private final DataLoader DATA_LOADER = new DataLoader();
    private final UserActionsLogger USER_ACTIONS_LOGGER = new UserActionsLogger();
    private final GlobalObjectParser GLOBAL_OBJECT_PARSER = new GlobalObjectParser();

    public void registerNewUser(String username, String password) {
        UserStore userStore = (UserStore) DATA_LOADER.getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile);
        userStore.addObjectsToList(new ArrayList<User>((Collection<? extends User>) new User(username, password)));
        DATA_LOADER.updateFile(ConfigurationDataUsage.pathToUserStoreJsonFile, userStore);
    }

    private UserRights getUserStatusByUserName(String username) throws Throwable {
        return new UserManager().getUserByUsername(username).getUserRights();
    }

    public String getUsersLogs(String username) {
        return USER_ACTIONS_LOGGER.getUserLogsByUsername(username);
    }

    public User getUserByUsername(String username) throws Throwable {
        List<User> userList = (List<User>) DATA_LOADER.getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile).getList();
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
            if (bookmark.getName().equals(bookmarkName) && bookmark.getBookTitle().equals(bookTitle)) {
                return bookmark;
            }
        }
        return emptyBookmark;
    }

    public void blockUser(String username) {
        UserStore userStore = (UserStore) DATA_LOADER.getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile);
        for (User user : userStore.getList()) {
            if (user.getUsername().equals(username)) {
                user.setUserStatus(UserStatus.LOCKED);
                break;
            }
        }
        DATA_LOADER.updateFile(ConfigurationDataUsage.pathToUserStoreJsonFile, userStore);
    }

    public void unlockUser(String username) {
        UserStore userStore = (UserStore) DATA_LOADER.getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile);
        for (User user : userStore.getList()) {
            if (user.getUsername().equals(username)) {
                user.setUserStatus(UserStatus.UNLOCKED);
                break;
            }
        }
        DATA_LOADER.updateFile(ConfigurationDataUsage.pathToUserStoreJsonFile, userStore);
    }
}
