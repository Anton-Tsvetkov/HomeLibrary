package com.epam.laboratory.util.parsers;

import com.epam.laboratory.util.ConfigurationDataUsage;
import com.epam.laboratory.entities.global.GlobalObject;
import com.epam.laboratory.entities.global.Library;
import com.epam.laboratory.entities.global.UserStore;
import com.epam.laboratory.entities.library.Bookmark;
import com.epam.laboratory.entities.user.User;

import java.io.*;
import java.util.List;

public class DataLoader {

    private final JSONParser jsonParser = new JSONParser();
    private final CSVParser csvParser = new CSVParser();
    private final ConfigurationDataUsage configurationDataUsage = new ConfigurationDataUsage();


    public GlobalObject getDataFromFile(String pathToFile) {
        if (pathToFile.toLowerCase().contains("json")) {
            return jsonParser.parseDataFromJson(pathToFile);
        } else if (pathToFile.toLowerCase().contains("csv")) {
            return csvParser.parseDataFromFile(pathToFile);
        } else {
            System.out.println("Data not found");
            return new Library();
        }
    }

    public void addNewBookmarks(User user, Bookmark bookmark) {
        UserStore userStore = (UserStore) getDataFromFile(configurationDataUsage.getPathToUserStoreJsonFile());
        for (User user1 : userStore.getList()) {
            if (user.getUsername().equals(user1.getUsername())) {
                List<Bookmark> bookmarkList = user1.getBookmarkList();
                bookmarkList.add(bookmark);
                user1.setBookmarkList(bookmarkList);
                break;
            }
        }
        updateFile(configurationDataUsage.getPathToUserStoreJsonFile(), userStore);
    }

    public void removeBookmark(User user, Bookmark bookmarkForRemove) {
        UserStore userStore = (UserStore) getDataFromFile(configurationDataUsage.getPathToUserStoreJsonFile());

        for (User realUser : userStore.getList()) {
            if (user.getUsername().equals(realUser.getUsername())) {
                user = realUser;
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
        updateFile(configurationDataUsage.getPathToUserStoreJsonFile(), userStore);
    }

    public void addDataToFile(String pathToFile, String data) {
        try (FileWriter fileWriter = new FileWriter(pathToFile, true)) {
            fileWriter.write("\n" + data);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDataFromFileAsString(String pathToFile) {
        try (FileReader fileReader = new FileReader(pathToFile)) {
            StringBuilder stringData = new StringBuilder();
            int c;
            while ((c = fileReader.read()) != -1) {
                stringData.append(c);
            }
            return stringData.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "NO DATA";
    }

    public void updateFile(String pathToFile, GlobalObject object) {
        if (pathToFile.toLowerCase().contains("json")) {
            jsonParser.parseDataToJson(pathToFile, object);
        } else if (pathToFile.toLowerCase().contains("csv")) {
            //csvParser.parseDataToCSV(pathToFile, (Library) object);
        } else {
            System.out.println("Data not found");
        }
    }

}
