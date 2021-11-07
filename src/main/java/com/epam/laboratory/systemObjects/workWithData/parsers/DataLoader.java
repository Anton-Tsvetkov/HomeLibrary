package com.epam.laboratory.systemObjects.workWithData.parsers;

import com.epam.laboratory.systemObjects.workWithData.ConfigurationDataUsage;
import com.epam.laboratory.workObjects.globalObjects.GlobalObject;
import com.epam.laboratory.workObjects.globalObjects.Library;
import com.epam.laboratory.workObjects.globalObjects.UserStore;
import com.epam.laboratory.workObjects.library.Bookmark;
import com.epam.laboratory.workObjects.user.User;

import java.util.List;

public class DataLoader {

    private final JSONParser JSON_PARSER = new JSONParser();


    public GlobalObject getDataFromFile(String pathToFile) {
        if (pathToFile.toLowerCase().contains("json")) {
            return JSON_PARSER.parseObjectsFromJson(pathToFile);
        } else if (pathToFile.toLowerCase().contains("csv")) {
            // return data from csv file
            return new Library();
        } else {
            System.out.println("Data not found");
            return new Library();
        }
    }

    public void addNewBookmarks(User user, Bookmark bookmark) {
        UserStore userStore = (UserStore) getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile);
        for (User user1 : userStore.getList()) {
            if (user.getUsername().equals(user1.getUsername())) {
                List<Bookmark> bookmarkList = user1.getBookmarkList();
                bookmarkList.add(bookmark);
                user1.setBookmarkList(bookmarkList);
                break;
            }
        }
        updateFile(ConfigurationDataUsage.pathToUserStoreJsonFile, userStore);
    }

    public void removeBookmark(User user, Bookmark bookmarkForRemove) {
        UserStore userStore = (UserStore) getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile);

        for (User user1 : userStore.getList()) {
            if (user.getUsername().equals(user1.getUsername())) {
                user = user1;
                break;
            }
        }

        List<Bookmark> bookmarkList = user.getBookmarkList();
        Bookmark removingBookmark = new Bookmark();
        for (Bookmark bookmark : bookmarkList) {
            if (bookmark.getName().equals(bookmarkForRemove.getName())) {
                removingBookmark = bookmark;
            }
        }
        bookmarkList.remove(removingBookmark);
        user.setBookmarkList(bookmarkList);
        updateFile(ConfigurationDataUsage.pathToUserStoreJsonFile, userStore);
    }

    public void updateFile(String pathToFile, GlobalObject object) {
        if (pathToFile.toLowerCase().contains("json")) {
            JSON_PARSER.parseObjectsToJson(pathToFile, object);
        } else if (pathToFile.toLowerCase().contains("csv")) {
            // return data from csv file
        } else {
            System.out.println("Data not found");
        }
    }

}
