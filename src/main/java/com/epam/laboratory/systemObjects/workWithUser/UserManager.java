package com.epam.laboratory.systemObjects.workWithUser;

import com.epam.laboratory.exceptions.DataException;
import com.epam.laboratory.exceptions.UserDataException;
import com.epam.laboratory.systemObjects.workWithData.ConfigurationDataUsage;
import com.epam.laboratory.systemObjects.workWithData.parsers.DataLoader;
import com.epam.laboratory.systemObjects.workWithData.parsers.GlobalObjectParser;
import com.epam.laboratory.workObjects.library.Bookmark;
import com.epam.laboratory.workObjects.user.User;
import com.epam.laboratory.workObjects.user.UserRights;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private final DataLoader DATA_LOADER = new DataLoader();
    private final GlobalObjectParser GLOBAL_OBJECT_PARSER = new GlobalObjectParser();

    public void registerNewUser(String username, String password) {
        try {
            List<User> userList = new ArrayList<>();
            userList.add(new User(username, password));
            DATA_LOADER.addNewObjectsInFile(ConfigurationDataUsage.pathToUserStoreJsonFile,
                    GLOBAL_OBJECT_PARSER.parseListAsGlobalObject(userList));
        } catch (DataException e){
            e.printStackTrace();
        }
    }

    private UserRights getUserStatusByUserName(String username) throws Throwable {
        return new UserManager().getUserByUsername(username).getUserRights();
    }

    public User getUserByUsername(String username) throws Throwable {
        List<User> userList = (List<User>) DATA_LOADER.getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile).getList();
        for (User user : userList){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        throw new UserDataException("").fillInStackTrace();

    }

    public Bookmark getBookmarkByName(User user, String bookTitle, String bookmarkName){
        List<Bookmark> bookmarkList = user.getBookmarkList();
        Bookmark emptyBookmark = new Bookmark();
        for (Bookmark bookmark : bookmarkList){
            if(bookmark.getName().equals(bookmarkName) && bookmark.getBookTitle().equals(bookTitle)){
                return bookmark;
            }
        }
        return emptyBookmark;
    }


}
